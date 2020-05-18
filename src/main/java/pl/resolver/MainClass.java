package pl.resolver;

public class MainClass {

	public static void main(String[] args) {
		FileResolver fileResolver = new FileResolver(args); 
		fileResolver.run();
	}

}
