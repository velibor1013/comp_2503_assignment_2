package comp_2503_assignment_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class A2 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	private SLL<Avenger> mentionList = new SLL<Avenger>();
	private SLL<Avenger> alphabticalList = new SLL<Avenger>();
	private SLL<Avenger> mostPopularList = new SLL<Avenger>(new AvengerComparatorFreqDesc());
	private SLL<Avenger> leastPopularList = new SLL<Avenger>(new AvengerComparatorFreqAsc());
	
	public static void main(String[] args) throws FileNotFoundException {
		A2 a1 = new A2();
		a1.run();
	}

	public void run() throws FileNotFoundException {
		readInput();
		createdOrderedLists();
		printResults();
	}

	private void createdOrderedLists() {
		// TODO: 
		// Create a mover and traverse through the mentionList.
		// Add each avenger to the other three lists. 
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 * @throws FileNotFoundException 
	 */
	private void readInput() throws FileNotFoundException {
		/*
		In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name then
				- Create a new avenger object with the corresponding alias and last name.
				- if this avenger has already been mentioned, increase the frequency count for the object already in the list.
				- if this avenger has not been mentioned before, add the newly created avenger to the end of the list, remember to set the frequency.
		*/ 
		File file = new File(A2.class.getClassLoader().getResource("input2.txt").getFile());
		Scanner sc = new Scanner(file);
		System.out.println("hello");;
		while (sc.hasNext()) {
            String cleanedWord = cleanWord(sc.next());
			
            System.out.println(cleanedWord);
            for (int i = 0; i < avengerRoster.length; i++) {
                for (int j = 0; j < avengerRoster[i].length; j++) {
                    if (cleanedWord.equals(avengerRoster[i][j])) {
                        Avenger currentAvenger = new Avenger(avengerRoster[i][0], avengerRoster[i][1], 1);
                        int index1 = mentionList.indexOf(currentAvenger);
                        if (index1 == -1) {
                        	mentionList.addTail(currentAvenger);
                        } else {
                        	mentionList.get(index1).setFrequency(mentionList.get(index1).getFrequency() + 1);
                        }
                    }
                }
            }
        	totalwordcount++;
            
        }
		sc.close();
	}
	
	private String cleanWord(String word) {
	    if (word != null) { // check to make sure word is not null, in case of error
	        word = word.trim(); // trim off blank spaces
	        if (!word.isEmpty()) { // check for blank word 
	            // Remove apostrophe
	            int index = word.indexOf("'");
	            if (index > 0) 
	                word = word.substring(0, index);
	            
	            // Remove non-alphabetic and non-digit characters and convert to lower case
	            word = word.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
	        }
	    }
	    return word;
	}
	

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		//System.out.println("Number of Avengers Mentioned: " + ??);
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output

		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		
		System.out.println();
	}
	
}
