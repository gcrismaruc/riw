package lab1;

import lab2.FileLoader;

import java.io.IOException;
import java.net.MalformedURLException;

public class RunMain {

	public static void main(String [] args) throws MalformedURLException {
		
//		HTMLDocument page;
//		page = new HTMLDocument();
//		try {
//			page.setUrl("http://stackoverflow.com/questions/12715246/how-to-check-if-a-character-in-a-string-is-a-digit-or-letter");
//			//page.getTitle();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Keywords: " + page.getMetaContent("name", "keywords"));
//		System.out.println("Description:" + page.getMetaContent("name", "description"));
//
//		System.out.println("Robots:" + page.getMetaContent("name", "robots"));
//
//		System.out.println("Text: " + page.getText());
//		System.out.println("All links: ");
//		page.printAllLinks();
//
//		TextParser textParser = new TextParser();
//		textParser.printWords(page.getText());

		FileLoader fileLoader = new FileLoader("working_dir");
		fileLoader.getFiles("/working_dir");
	}
}
