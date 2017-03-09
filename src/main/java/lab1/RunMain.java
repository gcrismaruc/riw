package lab1;

import lab2.Indexer;

import java.io.IOException;

public class RunMain {

	public static void main(String [] args) throws IOException {
        Indexer indexer = new Indexer();
        indexer.doDirectIndex("working_dir");
		indexer.readDirectIndexedFiles();
		indexer.putInvertedIndexToFile();
    }
}
