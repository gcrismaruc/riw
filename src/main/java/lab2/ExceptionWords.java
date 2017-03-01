package lab2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gheorghe on 3/1/2017.
 */
public class ExceptionWords {

    public static List<String> exceptionWords;

    public ExceptionWords(){
        this.exceptionWords = new ArrayList<>();
    }

    public ExceptionWords(List<String> exceptionWords){
        this.exceptionWords = new ArrayList<>(exceptionWords);
    }

    public List<String> getExceptionWords() {
        return exceptionWords;
    }

}
