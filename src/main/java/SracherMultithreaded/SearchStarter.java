package SracherMultithreaded;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class SearchStarter {

	public static void main(String[] args) throws IOException, InterruptedException {
		InputStream input = new FileInputStream("src/main/resources/config.properties");
		Properties props = new Properties();
		props.load(input);

		// Getting props
		String url = props.getProperty("url");
		int linksLimit = Integer.parseInt(props.getProperty("linksLimit"));
		int depth = Integer.parseInt(props.getProperty("depth"));
		boolean uniqueness = Boolean.parseBoolean(props.getProperty("uniqueness"));


		CrawlerTask.setLimit(linksLimit);

		ResultList res = new ResultList(uniqueness);

		CrawlerTask task = new CrawlerTask(url, depth, res);

		Thread thread = new Thread(task);

		thread.start();
		thread.join();

		for (UrlDeep urlLocal : res) {
			
			saveFile(urlLocal);
			
		}

		// 0/www_ynetnews_com.html

	}

	private static void saveFile(UrlDeep urlLocal) throws IOException {
		String fileName = format(urlLocal.getUrl());
		System.out.println(fileName);
		File dir = new File("" + urlLocal.getDepth());
		if (!dir.exists()) {
			dir.mkdir();
		}
		PrintWriter writer = new PrintWriter(new FileWriter("" + urlLocal.getDepth() + File.separator + fileName));
		System.out.println(urlLocal.getUrl());
		writer.println(urlLocal.getUrl());
		writer.close();
		
	}

	private static String format(String url) {
		String fileName = url.replace("https://", "").replace(".", "_").replace("/", "_").concat(".html");
		
		return fileName;
	}

}
