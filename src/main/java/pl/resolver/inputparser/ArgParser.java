package pl.resolver.inputparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgParser {
	private static final String[] NO_FILES = {};
	final Pattern WHITESPACE_INPUT_REGEX = Pattern.compile("[\\s]+");
	private String[] args;
	
	public ArgParser(String[] args) {
		this.args = args;
	}
	
	public String[] getInput() {
		if(args == null || args.length==0) {
			return NO_FILES;
		} else {
			List<String> files = new ArrayList<>();
			for(String arg : args) {
				Matcher whitespaceMatcher = WHITESPACE_INPUT_REGEX.matcher(arg);
				if(whitespaceMatcher.matches())
					return NO_FILES;
				files.add(arg);
			}
		return files.stream().toArray(String[]::new);	
		}	
	}

}
