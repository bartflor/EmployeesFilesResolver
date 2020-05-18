package pl.resolver.inputparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class InputFilesCollectorTest {
	InputFilesCollector inputFilesCollector = new InputFilesCollector();

	@Test
	public void shouldReturnProperCsvReader() throws IOException {
		//given
		String[] file =new String[] { "src/test/resources/e.csv" };
		
		//when
		List<FileReader> readersList = inputFilesCollector.getFiles(file);
		
		//than
		assertTrue(readersList.size() == 1, "Should add 1 json reader");
		assertEquals(readersList.get(0).getFileName(), "e.csv", "Should return test file source name");
		assertTrue(readersEquality(readersList.get(0).getReader(),Files.newBufferedReader(Paths.get(file[0]))));
		assertEquals(readersList.get(0).getType(), SupportedTypes.CSV, "Should return csv type reader");
	}

	@Test
	public void shouldReturnProperJsonReader() throws IOException  {
		//given
		String[] file = new String[] { "src/test/resources/e.json" };
		
		//when
		List<FileReader> readersList = inputFilesCollector.getFiles(file);
		
		//than
		assertTrue(readersList.size() == 1, "Should add 1 json reader");
		assertEquals(readersList.get(0).getFileName(), "e.json", "Should return test file source name");
		assertTrue(readersEquality(readersList.get(0).getReader(), Files.newBufferedReader(Paths.get(file[0]))),
					"Obtained Reader should be equal to prepared one.");
		assertEquals(readersList.get(0).getType(), SupportedTypes.JSON, "Should return json type reader");
	}

	@Test
	public void shouldAddReaders() {
		//given
		String[] files = new String[] { "src/test/resources/e.json", "src/test/resources/e.csv" };
		
		//when
		List<FileReader> readersList = inputFilesCollector.getFiles(files);
		
		//then
		assertTrue(readersList.size() == 2, "Should add 2 readers, as 2 correct file paths was given.");
	}
	@ParameterizedTest
	@MethodSource("invalidFilePathStringArrayProvider")
	public void shouldGiveEmptyReadersListWithWrongInputFiles(String[] files) {
		//given: non existing file path
		//when
		List<FileReader> readersList = inputFilesCollector.getFiles(files);
		
		//then
		assertTrue(readersList.isEmpty(), "Should return true. Result readerList should be empty when invalid file path was given.");

	}
	@NullAndEmptySource
	@ParameterizedTest
	public void shouldGiveEptyReadersListWithEmptyInputFiles(String[] files) {
		//given null and empty files array
		//when
		List<FileReader> readersList = inputFilesCollector.getFiles(files);
		
		//then
		assertEquals(0, readersList.size(), "Should return empty list");
		assertEquals(Collections.EMPTY_LIST, readersList,"Should return empty list");
		
	}
	

	static Stream<Arguments> invalidFilePathStringArrayProvider() {
		return Stream.of(
				Arguments.of((Object) new String[] { "NoExisting.csv" }),
				Arguments.of((Object) new String[] { "src/test/resources/NotSupportedType.xml",
													 "src/test/resources/NotSupportedType.txt" })
				);
	}

	static boolean readersEquality(Reader input1, Reader input2) throws IOException {
		if (!(input1 instanceof BufferedReader)) {
			input1 = new BufferedReader(input1);
		}
		if (!(input2 instanceof BufferedReader)) {
			input2 = new BufferedReader(input2);
		}

		int ch = input1.read();
		while (-1 != ch) {
			int ch2 = input2.read();
			if (ch != ch2) {
				return false;
			}
			ch = input1.read();
		}

		int ch2 = input2.read();
		return (ch2 == -1);
	}

}
