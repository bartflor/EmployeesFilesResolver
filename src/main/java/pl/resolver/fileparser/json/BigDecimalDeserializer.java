package pl.resolver.fileparser.json;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class BigDecimalDeserializer implements JsonDeserializer<BigDecimal> {
	public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return new BigDecimal(json.getAsJsonPrimitive().getAsString().replaceAll(",", "."));
	}
}
