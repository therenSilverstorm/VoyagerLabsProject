package Searcher;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Starter {
	public static void main(String[] args) throws Exception {
		// Pass the values through config.properties file in
		// src/main/resources/config.properties
		InputStream input = new FileInputStream("src/main/resources/config.properties");
		Properties props = new Properties();
		props.load(input);

		// Getting props
		String url = props.getProperty("url");
		int linksLimit = Integer.parseInt(props.getProperty("linksLimit"));
		int depth = Integer.parseInt(props.getProperty("depth"));
		boolean uniqueness = Boolean.parseBoolean(props.getProperty("uniqueness"));

		// Calling the method
		Search rec = new Search();
		rec.getUrls(url, linksLimit, depth, uniqueness);

	}
}