package pl.resolver.fileparser.json;

import java.util.List;

public interface Adapter<E> {
	List<E> getAdapteeList();
}
