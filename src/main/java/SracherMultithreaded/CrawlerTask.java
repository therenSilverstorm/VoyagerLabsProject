package SracherMultithreaded;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerTask implements Runnable {

	static int limit;
	String url;
	int depth;
	ResultList res;

	public CrawlerTask(String url, int depth, ResultList res) {
		super();
		this.url = url;
		this.depth = depth;
		this.res = res;
	}

	
	//Running all threads
	@Override
	public void run() {
		if (res.addurl(url, depth) && depth != 0) {

			try {
				Document document = fetch(url);
				if (document != null) {
					List<Element> linksOnPage = document.select("a[href]").subList(0, limit);
					Elements el = new Elements(linksOnPage);
					List<Thread> threads = new ArrayList<>();

					for (Element element : el) {
						String absoluteLink = element.absUrl("href");
						CrawlerTask task = new CrawlerTask(absoluteLink, depth - 1, res);
						threads.add(new Thread(task));

						// executor.execute(task);
					}

					for (Thread thr : threads) {
						thr.start();
					}
					for (Thread thr : threads) {
						thr.join();
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

//Fetching
	private static Document fetch(String url) throws Exception {
		Connection connection = Jsoup.connect(url);
		Document document = connection.ignoreContentType(true).ignoreHttpErrors(true).get();
		return document;
	}

	public static void setLimit(int limit) {
		CrawlerTask.limit = limit;
	}

}
