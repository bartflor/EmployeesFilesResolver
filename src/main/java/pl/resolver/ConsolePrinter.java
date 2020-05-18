package pl.resolver;

import java.util.Map;

public class ConsolePrinter {
	public static void printOutput(String name, Map<String, Double> map) {
		System.out.println(name + ":");
		if (map != null) {
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
		}
	}
}
