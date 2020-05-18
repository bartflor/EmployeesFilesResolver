package pl.resolver.inputparser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

public class ArgParserTest {

	@ParameterizedTest
	@NullSource
	@EmptySource
	@MethodSource("whitespaceArrayProvider")
	void shouldReturnEmptyAarray(String[] args) {
		//when
		ArgParser argParser = new ArgParser(args);
		
		//then
		assertArrayEquals(new String[] {}, argParser.getInput(), "Should return empty array, when no input given.");
	}

	static Stream<Arguments> whitespaceArrayProvider() {
		return Stream.of(Arguments.of((Object) new String[] { " " }), Arguments.of((Object) new String[] { "   " }),
				Arguments.of((Object) new String[] { "\n" }), Arguments.of((Object) new String[] { "\t" }));
	}

	@ParameterizedTest
	@MethodSource("correctFilePathStringArrayProvider")
	void shouldReturnPaths(String[] args) {
		//when
		ArgParser argParser = new ArgParser(args);
		//then
		assertArrayEquals(args, argParser.getInput(), "Should return given correct file paths.");
	}

	static Stream<Arguments> correctFilePathStringArrayProvider() {
		return Stream.of(Arguments.of((Object) new String[] { "path/to/file/file" }),
				Arguments.of((Object) new String[] { "path/file1", "path/file2", "path/file3", "path/file4" }),
				Arguments.of((Object) new String[] { "path/to/file/file.csv", "path/to/file/file.json" }),
				Arguments.of((Object) new String[] { " pat h/to/f ile/fi le.csv", "path/to/file/fil e .json " }));
	}
}
