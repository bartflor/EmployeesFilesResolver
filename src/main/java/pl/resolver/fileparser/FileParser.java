package pl.resolver.fileparser;

import java.util.List;

public interface FileParser<E> {

	List<E> getObjectsList() throws ObjectDeserializationExeption;

}