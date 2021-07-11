package SearcherRecursion;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Search {

	private List<String> links;

	public Search() {
		links = new ArrayList<String>();
	}

	public void getUrls(String URL, int maxAmount, int depth, boolean flag) throws Exception {
		// If we reached the depth limit, we stop.
		if (((depth >= 0))) {

			// Uniqueness flag == true;
			if (flag) {
				if (!links.contains(URL)) {
					links.add(URL);
					saveFile(URL, depth);
				}

				// Uniqueness flag == false;
			} else {
				links.add(URL);
				saveFile(URL, depth);
			}

			// Fetching
			Document document = fetch(URL);

			// Getting N number of URL's from current page
			List<Element> linksOnPage = document.select("a[href]").subList(0, maxAmount);
			Elements el = new Elements(linksOnPage);

			// Recursive *Depth-first*-like Search
			if (document != null) {
				for (Element element : el) {

					// Getting absolute URL
					String absoluteLink = element.absUrl("href");

					// Recursive Depth-first search
					getUrls(absoluteLink, maxAmount, depth - 1, flag);

				}
			}
		}
	}

	// Saves files to the project directory, grouped by depth.
	private void saveFile(String url, int depth) throws IOException {

		String formatUrl = format(url, depth);
		Response response = Jsoup.connect(url).execute();
		Document doc = response.parse();
		File file = new File(formatUrl + ".html");
		FileUtils.writeStringToFile(file, doc.outerHtml(), StandardCharsets.UTF_8);

	}

	// Fetching and getting HTML document
	private static Document fetch(String url) throws Exception {
		Connection connection = Jsoup.connect(url);
		Document document = connection.ignoreContentType(true).ignoreHttpErrors(true).get();
		return document;
	}

	// File format
	private String format(String url, int depth) {
		String d = String.valueOf(depth);
		String fileName = url.replace("https:/", d).replace(".", "_");
		return fileName;
	}

	// Getter
	public List<String> getLinks() {
		return links;
	}

}
