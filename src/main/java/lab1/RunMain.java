package lab1;

import lab2.ExceptionWords;
import lab2.FileLoader;
import lab2.Indexer;
import lab2.StopWords;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RunMain {

	public static void main(String [] args) throws IOException {
		
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
		List<File> files = fileLoader.getFiles("working_dir");

        String data = new String(Files.readAllBytes(FileLoader.getResourceFile("stopWords").toPath()), StandardCharsets.UTF_8);

		TextParser textParser = new TextParser();
        textParser.getWords(data);
        StopWords.stopWords = new ArrayList<String>(textParser.getAparitii().keySet());

        ExceptionWords.exceptionWords = new ArrayList<>();
        Indexer indexer = new Indexer();
        for(File file : files){
            HTMLDocument page;
    		page = new HTMLDocument();
            page.setDocumentFromFile(file);
//            textParser.getWords();
            Map<String, Integer> map = textParser.getParsedWords(page.getText());
            indexer.addAparitii(map);

        }

        System.out.println(indexer.getAparitii());
    }
}
