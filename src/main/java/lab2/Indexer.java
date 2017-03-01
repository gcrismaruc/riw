package lab2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Gheorghe on 2/23/2017.
 */
public class Indexer {

    private Map<String, Integer> aparitii;

    public Indexer(){
        this.aparitii = new HashMap<>();
    }

    public void addAparitii(Map<String, Integer> map){
//        aparitii.forEach((k, v) -> map.merge(k, v, (v1, v2) -> v1 + v2));
        Map<String, Integer> combinedMap = Stream.concat(map.entrySet().stream(), aparitii.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)));
        aparitii = new HashMap<>(combinedMap);
    }

    public Map<String, Integer> getAparitii() {
        return aparitii;
    }



}
