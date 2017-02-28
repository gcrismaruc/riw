package lab1;

import java.util.HashMap;
import java.util.Map;

public class TextParser {
	private Map<String, Integer> aparitii;
	
	
	TextParser(){
		aparitii = new HashMap<String, Integer>();
	}

	private void getWords(String text) {

		StringBuilder word = new StringBuilder();

		for(int i = 0; i < text.length(); i++) {

			if(!Character.isLetter(text.charAt(i))){
				if(aparitii.containsKey(word.toString())) {
					int count = aparitii.get(word.toString());
					aparitii.put(word.toString(), count + 1);
				}else {
					if (!word.toString().equals("")) {
						aparitii.put(word.toString(), 1);
					}
				}
				word = word.delete(0, word.length());
			}else {
				word.append(text.charAt(i));
			}
		}
	}

	public void printWords(String text) {
		getWords(text);

		for(Map.Entry entry : aparitii.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
}
