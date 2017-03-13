package lab2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.shape.Path;
import javafx.util.Pair;
import lab1.HTMLDocument;
import lab1.TextParser;
import lab4.MyPair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by Gheorghe on 2/23/2017.
 */
public class Indexer {

    private Map<String, Integer> aparitii;
    private Map<String, String> htmlToDirectIndexMap;
    private Map<String, List<Pair<String, Integer>>> invertedIndex;
    private static ObjectMapper objectMapper = new ObjectMapper();


    public Indexer(){
        this.aparitii = new HashMap<>();
        this.htmlToDirectIndexMap = new HashMap<>();
        this.invertedIndex = new HashMap<>();
    }


    public Indexer(Map<String, Integer> aparitii){
        this.aparitii = aparitii;
    }

    public void addAparitii(Map<String, Integer> map){
        Map<String, Integer> combinedMap = Stream.concat(map.entrySet().stream(), aparitii.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)));
        aparitii = new HashMap<>(combinedMap);
    }

//    public void getAparitiiPentruFisier(File file) throws IOException {
//        HTMLDocument doc = new HTMLDocument();
//        doc.setDocumentFromFile(file);
//
//        this.aparitiiDocument.put(file.getAbsolutePath(), TextParser.getParsedWords(doc.getText()));
//    }

    public void putDirectIndexToFile(File file) throws IOException {
        File indexDirectFile = new File(file.getAbsolutePath().replace(".html", ".idc"));
        this.htmlToDirectIndexMap.put(file.getAbsolutePath(), indexDirectFile.getAbsolutePath());

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(indexDirectFile, this.aparitii);
    }

    private void addToInvertedIndex(String pathToDirectIndex){
        for(String word : this.aparitii.keySet()) {

            if (this.invertedIndex.containsKey(word)) {
                this.invertedIndex.get(word).add(new Pair<>(pathToDirectIndex, aparitii.get(word)));
            } else {
                List<Pair<String,Integer>> list = new ArrayList<>();
                list.add(new Pair<>(pathToDirectIndex, aparitii.get(word)));
              this.invertedIndex.put(word, list);
            }
        }
    }
//    public void toFile(String fileName) throws IOException {
//        Properties properties = new Properties();
//        String dirPath = FileLoader.getTargetFile("resources").getPath();
//
//        properties.putAll(this.aparitiiDocument);
//    }

    public Map<String, Integer> getAparitii() {
        return aparitii;
    }
    public void setAparitii(Map<String, Integer> aparitii) {
        this.aparitii = aparitii;
    }


    public void doDirectIndex(String directoryName) throws IOException {
        StopWords.loadStopWords();
        ExceptionWords.loadExceptionWords();
        FileLoader fileLoader = new FileLoader(directoryName);
        TextParser textParser = new TextParser();

        List<File> files = fileLoader.getFiles(directoryName);

        for(File file:files){
            HTMLDocument page1 = new HTMLDocument();
            page1.setDocumentFromFile(file);
            this.setAparitii(textParser.getParsedWords(page1.getText()));
            this.putDirectIndexToFile(file);
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("MappedHTMLToDirectIndex.txt"), this.htmlToDirectIndexMap);
    }

    public void readDirectIndexedFiles() throws IOException {
        this.htmlToDirectIndexMap = objectMapper.readValue(new File("MappedHTMLToDirectIndex.txt"), new TypeReference<HashMap<String,Object>>() {});
    }

    public void putInvertedIndexToFile() throws IOException {
        for(String path : htmlToDirectIndexMap.keySet()){
            this.aparitii = objectMapper.readValue(new File(htmlToDirectIndexMap.get(path)), new TypeReference<HashMap<String,Object>>() {});
            this.addToInvertedIndex(htmlToDirectIndexMap.get(path));
        }

        File invertedFile = new File("InvertedIndex.txt");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(invertedFile, this.invertedIndex);

        Map<String, String> wordToInvertedIndex = new HashMap<>();
        for(Map.Entry entry : invertedIndex.entrySet()){
            wordToInvertedIndex.put(entry.getKey().toString(), invertedFile.getAbsolutePath());
        }

        File invertedMappedIndex = new File("InvertedMappedFile.txt");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(invertedMappedIndex, wordToInvertedIndex);
    }

    public static Map<String, String> getInvertedMappedIndex() throws IOException {
        Map<String, String> invertedMappedIndex = objectMapper.readValue(new File("InvertedMappedFile.txt"), new TypeReference<HashMap<String,Object>>() {});

        return invertedMappedIndex;
    }

    public static Map<String, List<MyPair>> getInvertedIndex() throws IOException {
        Map<String, List<MyPair>> invertedIndex = objectMapper.readValue(new File("InvertedIndex.txt"), new TypeReference<HashMap<String, List<MyPair>>>() {});

        return invertedIndex;
    }


}
