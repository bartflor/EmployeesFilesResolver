package pl.resolver.inputparser;

public enum SupportedTypes {
	CSV("glob:**.csv"),
	JSON("glob:**.json");
	
	private final String FILE_PATH_FORMAT;
	
	private SupportedTypes(String filePathFormat) {
		this.FILE_PATH_FORMAT = filePathFormat;
	}

	public String filePathFormat() {
		return FILE_PATH_FORMAT;
	}
	
}
