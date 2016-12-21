package lab.view;



public class UrlInfo {
	

	private String shorturl;
	private String longurl;
	
	// clicks:
	private int clicks = 0;
	
	public UrlInfo(String lurl,String surl,int c) {
		this.longurl = lurl;
		this.shorturl=surl;
		this.clicks=c;
	}

	public UrlInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public String getLongurl() {
		return longurl;
	}

	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	
	
	
	
}
