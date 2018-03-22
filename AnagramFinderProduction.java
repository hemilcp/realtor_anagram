
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * @author hemil parmar
 * This application
 */
public class AnagramFinderProduction {
	
//	private static int[] mapping = {5,71,79,19,2,83,31,43,11,53,37,23,41,3,13,73,101,17,29,7,59,47,61,97,89,67,103};

	public static void main(String[] args){
		String filePath = args[0];

		InputStream input = null;

		try{
			input = new FileInputStream(new File(filePath));		
		}
		catch(Exception e){
			e.printStackTrace();		
		}		

		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");
		
		
		Map<String, List<String>> map = new HashMap<>();
				
		long startTime = new Date().getTime(), endTime=0;

		try(Scanner scanner = new Scanner(input)){
			while(scanner.hasNext()){
			
				String word = scanner.nextLine();
				String sortedWord = sortWord(word);
				List<String> list = map.get(sortedWord);
				if(list == null)
					map.put(sortedWord, list = new ArrayList<String>());
				
				list.add(word);
		
			}	
			endTime = new Date().getTime();
			
			System.out.println("Dictionary loaded in "+(endTime-startTime)+" ms");
			input.close();
			scanner.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	
		
		
		Scanner scanner = new Scanner(System.in);
		String queryWord;
		System.out.print("AnagramFinder> ");

		while(!(queryWord = scanner.nextLine()).equals("exit")){
		
			startTime = new Date().getTime();
			List<String> list = map.get(sortWord(queryWord));
			
			endTime = new Date().getTime();
			if(list != null){
				System.out.println(list.size()+" anagrams found for "+queryWord+" in "+(endTime - startTime)+" ms");
				for(int i = 0;i < list.size()-1; i++){
					System.out.print(list.get(i) + ", ");
				}
				System.out.print(list.get(list.size()-1) + "\n");
			}
			else 
				System.out.println("No anagrams found for :"+queryWord);
		
			System.out.println();
			System.out.print("AnagramFinder> ");

		}
		scanner.close();
		
	}
	
	private static String sortWord(String nextLine) {
		
		char[] charArray = nextLine.toCharArray();
		Arrays.sort(charArray);
				
		return new String(charArray);
	}
	
//	private static int findHash(String word){
//		int result = 1;
//		for(char c : word.toCharArray()){
//			if(c == '-') result *= mapping[26];
//			else if(Character.isUpperCase(c)) result *= mapping[c - 'A'];
//			else result *= mapping[c - 'a'];
//		}
//		return result;
//	}


}

