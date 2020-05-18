package pl.resolver.inputparser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputFilesCollector {

	public InputFilesCollector() {
	}

	public List<FileReader> getFiles(String... inputFiles) {
		List<FileReader> pathList = new ArrayList<>();
		if(inputFiles==null || inputFiles.length == 0) {
			return Collections.emptyList();
		}
		for (String inputfile : inputFiles) {
			Path filePath = Paths.get(inputfile);
			try {
				filePath = filePath.toRealPath();
				if (Files.isReadable(filePath)) {
					Reader reader = Files.newBufferedReader(filePath);
					String fileName = filePath.getFileName().toString();
					pathList.add(new FileReader(fileName, reader, chceckFileType(filePath)));
				} else {
					System.err.format("Can not open file: %s - not readable.%n", filePath.getFileName());
				}
			} catch (NoSuchFileException e) {
				System.err.format("No such file: %s%n", e.getFile());
			} catch (IOException e) {
				System.err.format("Error reading file: %s%n", e.getLocalizedMessage());
			} catch (FileTypeNotSupportedExeption e) {
				System.err.format(e.getMessage());
			}
		}
		return pathList;
	}

	private SupportedTypes chceckFileType(Path path) throws FileTypeNotSupportedExeption {
		for (SupportedTypes type : SupportedTypes.values()) {
			PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(type.filePathFormat());
			if (pathMatcher.matches(path)) {
				return type;
			}
		}
		throw new FileTypeNotSupportedExeption(path);

	}

}
