package lab1;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HTMLDocument {

	private Document document;
	private String currentUrl;

	private List<String> links;

	public HTMLDocument(){
		currentUrl = "";
		links = new ArrayList<String>();
	}
	
	public HTMLDocument(String url) {
		try{
			document = Jsoup.connect(url).get();
			currentUrl = url;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUrl(String url) throws IOException {
		document = Jsoup.connect(url).get();
		currentUrl = url;
	}

	public void setDocumentFromFile(File url) throws IOException {
		document = Jsoup.parse(url, "UTF-8");
	}
	public void getTitle() throws IOException {
		System.out.println(document.title());
	}
	
	public String getMetaContent(String attributeName, String attributeValue) {
        
		String content = ""; 
		try{
			content = document.select("meta[" + attributeName + "=" + attributeValue + "]").first().attr("content");  
		} catch(Exception e) {
			content = "No value for " + attributeName + "=" + attributeValue;
		}
        
        return content;
	}
	
	public void getAllLinks() throws MalformedURLException{
		Elements linksTo = document.select("a");

		for(Element link : linksTo) {
			if(!link.attr("href").contains("#")) {
				links.add(link.attr("href"));
			}
		}
	}

	public void printAllLinks() throws MalformedURLException {
		getAllLinks();
		for(String link : links){
			System.out.println(link);
		}
	}
	
	public String getText(){
		return document.getElementsByTag("html").text();
	}
}
