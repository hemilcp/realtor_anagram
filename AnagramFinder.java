import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
	@author Hemil Parmar
	This is the brute-force solution to find anagrams for a word from the provided dictionary file.
	The program takes inputs: first argument is the path to the dictionary file. Second argument is the word for which anagram is to be found.
*/


public class AnagramFinder {

	public static void main(String[] args){
		
		String filePath = args[0];
	//	InputStream input = AnagramFinder.class.getResourceAsStream(filePath);
	
		InputStream input = null;

		try{
			input = new FileInputStream(new File(filePath));
		}
		catch(Exception e){
				e.printStackTrace();
		}	
		long startTime = new Date().getTime();
		
//		Set<String> dict = new HashSet<>();
		
		Map<String, List<String>> map = new HashMap<>();
		
		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");
		
		//Read the dictionary file till it has a word
		try(Scanner scanner = new Scanner(input)){
				while(scanner.hasNext()){
//					dict.add(scanner.nextLine());

				//For each word from dictionary, sort it and use it as a Hash (or key) to add the word in the corresponding list as value in the map.
				// If there exist a word already, add the word in that list. Otherwise create a list and add the new word.
					String word = scanner.nextLine();
					String sortedWord = sortWord(word);
					List<String> list = map.get(sortedWord);
					if(list == null)
						map.put(sortedWord, list = new ArrayList<String>());
					
					list.add(word);
				}
		}

// Now the dictionary has been loaded and the map contains all the possible records of anagrams.
		
long endTime = new Date().getTime();
		
		System.out.println("\nExecution finished in "+(endTime - startTime)+" ms");
		
		// Read the second argument, that is the word for which anagram is to be found and see if the hash for that word matches any key in Map.
		List<String> list = map.get(sortWord(args[1]));
		
		//If list is not empty, then there exists atleast one word (it could be itself as well)
		if(list != null){
			System.out.println(list.size()+" anagrams found for "+args[1]);
			
			// Print all the words from the list
			for(int i = 0;i < list.size()-1; i++){
				System.out.print(list.get(i) + ", ");
			}
			System.out.print(list.get(list.size()-1) + "\n");
		}
		else 
			System.out.println("No anagrams found for :"+args[1]);
		
		
	}

	// This method takes in a parameter String word, which would be sorted alphabetically and returned as a String.
	private static String sortWord(String nextLine) {
		
		char[] charArray = nextLine.toCharArray();
		Arrays.sort(charArray);
				
		return new String(charArray);
	}
	
}

