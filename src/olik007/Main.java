package olik007;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Main {

	static List<String> readNewLine (String line){
		String[] parts = line.split("[^a-zA-Z]+");
		ArrayList <String> wordList = new ArrayList<String>();
		for (String string : parts) {
			wordList.add(string);
			
		}
	return wordList; 
	} 
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
	    return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}
	
	public static void main(String[] args) throws IOException {
		Map <String, Integer> uniqueWords = new TreeMap<String, Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader("src/lyrics.txt"));
		
		try {
		    String line = br.readLine();

		    while (line != null) {
		       
		        for (String string : readNewLine(line)) {
					if (uniqueWords.containsKey(string.toLowerCase())) {
						int i = uniqueWords.get(string.toLowerCase());
						uniqueWords.replace(string.toLowerCase(), ++i);
					}else if (!string.equals("")){
						uniqueWords.put(string.toLowerCase(), 1);
					}
				}
		    	line = br.readLine();
		    	
		    } 
		    
		    int j = 0;
		    int count = Integer.parseInt(args[0]);
		    sortByValue(uniqueWords);
		    Iterator<Map.Entry<String, Integer>> mapIterator = sortByValue(uniqueWords).entrySet().iterator();
		    while (mapIterator.hasNext() && j<=count) {
		        Map.Entry<String, Integer> entry = mapIterator.next();
		        System.out.println(entry.getKey() + "=" + entry.getValue());
		        j++;
		    }   
		}catch (Exception e) {
			   System.out.println(e.getMessage());
		} finally {
			
			br.close();
		}

	}
}


