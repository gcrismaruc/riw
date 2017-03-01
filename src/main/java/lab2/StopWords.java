package lab2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gheorghe on 3/1/2017.
 */
public class StopWords {

    public static List<String> stopWords;

    public StopWords(){
        this.stopWords = new ArrayList<>();
    }

    public StopWords(List<String> stopWords){
        this.stopWords = new ArrayList<>(stopWords);
    }

    public List<String> getStopWords() {
        return stopWords;
    }
}
