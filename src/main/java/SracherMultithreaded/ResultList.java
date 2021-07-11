package SracherMultithreaded;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//Class for inner logic and URL'S saving
public class ResultList implements Iterable<UrlDeep> {

	
	List<UrlDeep> urls = new ArrayList<>();
	boolean uniqueness;

	public ResultList(boolean uniqueness) {
		this.uniqueness = uniqueness;
	}

	public synchronized boolean addurl(String url, int depth) {
		UrlDeep urlDeep = new UrlDeep(url, depth);
		if (uniqueness) {
	
			if (!urls.contains(url)) {
				urls.add(urlDeep);
				return true;
			}
			return false;
		} else {
			urls.add(urlDeep);
			return true;
		}
		
	}

	@Override
	public Iterator<UrlDeep> iterator() {
		return urls.iterator();
	}

}
