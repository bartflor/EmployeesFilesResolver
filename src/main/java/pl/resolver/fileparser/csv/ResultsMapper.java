package pl.resolver.fileparser.csv;

import java.util.Map;

import pl.resolver.fileparser.ObjectDeserializationExeption;

public interface ResultsMapper<E> {

	 E map(Map<String, String> map) throws ObjectDeserializationExeption;
	 
	 char getDelimeter();

}
