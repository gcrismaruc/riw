package lab1;

import lab2.ExceptionWords;
import lab2.StopWords;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TextParser {
	private Map<String, Integer> aparitii;


	public TextParser(){
		aparitii = new HashMap<String, Integer>();
	}

	public Map<String, Integer> getAparitii() {
		return aparitii;
	}

	public static Map<String, Integer> getParsedWords(String text) {

		StringBuilder word = new StringBuilder();
		Map<String, Integer> words = new HashMap<>();

		for(int i = 0; i < text.length(); i++) {
			if(!Character.isLetter(text.charAt(i)) && text.charAt(i) != '\''){
				if(words.containsKey(word.toString())) {
					int count = words.get(word.toString());
					words.put(word.toString(), count + 1);
				}else {
					if (!word.toString().equals("")) {
						if(!StopWords.stopWords.contains(word)) {
							if(ExceptionWords.exceptionWords.contains(word)) {
								words.put(word.toString(), 1);
							}else{
								words.put(getCanonicalForm(word.toString()), 1);
							}
						}
					}
				}
				word = word.delete(0, word.length());
			}else {
				word.append(text.charAt(i));
			}
		}

		return words;
	}

	private static String getCanonicalForm(String word){
		return word;
	}

	public void getWords (String text) {

		StringBuilder word = new StringBuilder();

		for(int i = 0; i < text.length(); i++) {

			if(!Character.isLetter(text.charAt(i)) && text.charAt(i) != '\''){
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
