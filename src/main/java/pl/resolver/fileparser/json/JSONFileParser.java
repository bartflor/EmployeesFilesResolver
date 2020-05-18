package pl.resolver.fileparser.json;

import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import pl.resolver.fileparser.FileParser;
import pl.resolver.fileparser.ObjectDeserializationExeption;

public class JSONFileParser<E> implements FileParser<E> {
	private Reader reader;
	private Gson gson;
	private Adapter<E> adapter;

	public JSONFileParser(Reader reader, Adapter<E> adapter) {
		this.adapter = adapter;
		this.reader = reader;
		GsonBuilder gsonb = new GsonBuilder();
		gsonb.registerTypeAdapter(BigDecimal.class, new BigDecimalDeserializer());
		this.gson = gsonb.create();

	}

	public List<E> getObjectsList() throws ObjectDeserializationExeption {
		try {
		JsonReader jreade = gson.newJsonReader(reader);
		adapter = gson.fromJson(jreade, adapter.getClass());
		return adapter.getAdapteeList();
		}
		catch (JsonSyntaxException | JsonIOException ex) {
			throw new ObjectDeserializationExeption("Malformed JSON element.");
		}
	}

}
