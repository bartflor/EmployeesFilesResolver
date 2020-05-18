package pl.resolver.fileparser.csv;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import pl.resolver.fileparser.FileParser;
import pl.resolver.fileparser.ObjectDeserializationExeption;

public class CSVFileParser<E> implements FileParser<E> {

	private ResultsMapper<E> mapper;
	private Reader reader;

	public CSVFileParser(Reader reader, ResultsMapper<E> mapper) {
		this.reader = reader;
		this.mapper = mapper;
	}
	
	@Override
	public List<E> getObjectsList() throws ObjectDeserializationExeption {

		Iterable<CSVRecord> records;
		List<E> resultsList = new ArrayList<>();
		try {
			records = CSVFormat.DEFAULT.withDelimiter(mapper.getDelimeter())
					.withFirstRecordAsHeader()
					.withIgnoreSurroundingSpaces()
					.parse(reader);

			for (CSVRecord record : records) {
				E entity = mapper.map(record.toMap());
				resultsList.add(entity);
			}
		} catch (IOException| IllegalStateException| IllegalArgumentException e) {
			throw new ObjectDeserializationExeption("Malformed Csv file.");
		}

		return resultsList;
	}

}
