package SracherMultithreaded;

import java.util.Objects;


//Class for easier file saving (DTO)
public class UrlDeep {
	
	String url;
	
	int depth;

	public UrlDeep(String url, int depth) {
		super();
		this.url = url;
		this.depth = depth;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlDeep other = (UrlDeep) obj;
		return Objects.equals(url, other.url);
	}

	
	
	
}
