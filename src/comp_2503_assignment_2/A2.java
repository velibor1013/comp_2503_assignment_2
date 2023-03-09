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
		Node<Avenger> object1 = mostPopularList.getHead();
		if (object1 == null || object1.getNext() == null) {
	        return; // list is empty or has only one element, nothing to sort
	    }
		
		// Most popular list sorting.
	    boolean swapped = true;
	    while (swapped) {
	        swapped = false;
	        Node<Avenger> current = mostPopularList.getHead();
	        Node<Avenger> previous = null;
	        while (current.getNext() != null) {
	            if (current.getData().getFrequency() > current.getNext().getData().getFrequency()) {
	                // swap current node with next node
	                Node<Avenger> temp = current.getNext();
	                current.setNext(temp.getNext());
	                temp.setNext(current);
	                if (previous == null) {
	                	mostPopularList.addHead(temp);
	                } else {
	                    previous.setNext(temp);
	                }
	                previous = temp;
	                swapped = true;
	            } else {
	                previous = current;
	                current = current.getNext();
	            }
	        }
	        mostPopularList.addTail(previous); // update tail
	    }
	    
	    // Alphabetical list sorting.
	    swapped = true;
	    while (swapped) {
	        swapped = false;
	        Node<Avenger> current = alphabticalList.getHead();
	        Node<Avenger> previous = null;
	        while (current != null && current.getNext() != null) {
	            if (current.getData().getHeroAlias().compareTo(current.getNext().getData().getHeroAlias()) > 0) {
	                // swap current node with next node
	                Node<Avenger> temp = current.getNext();
	                current.setNext(temp.getNext());
	                temp.setNext(current);
	                if (previous == null) {
	                    alphabticalList.addHead(temp);
	                } else {
	                    previous.setNext(temp);
	                }
	                previous = temp;
	                swapped = true;
	            } else {
	                previous = current;
	                current = current.getNext();
	            }
	        }
	        alphabticalList.addTail(previous); // update tail
	    }
	
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
                    	Avenger currentAvenger; 
                		currentAvenger = new Avenger(avengerRoster[i][0], avengerRoster[i][1], 1);
                		addToMentionList(currentAvenger);                		
                    }
                }
            }
        	totalwordcount++;
        }
		sc.close();
	}
	
	//Method to add the Avengers as they are found in the text.
	private void addToMentionList(Avenger currentAvenger) {
		Node<Avenger> current = mentionList.getHead();
		boolean found = false;
		while (current != null) {
		    if (current.getData().compareTo(currentAvenger) == 0) {
		        current.getData().setFrequency(current.getData().getFrequency() + 1);
		        found = true;
		        break;
		    }
		    current = current.getNext();
		}
		if (!found) {
		    mentionList.nodeToAdd(currentAvenger);
		    alphabticalList.nodeToAdd(currentAvenger);
		    mostPopularList.nodeToAdd(currentAvenger);
		    leastPopularList.nodeToAdd(currentAvenger);
		}
	}
	
	
	private String cleanWord(String word) {
		if (word == null) {
	        throw new IllegalArgumentException("Input word cannot be null");
	    }
	    word = word.trim();
	    if (!word.isEmpty()) {
	        // Remove apostrophes
	        word = word.replaceAll("'", "");
	        
	        // Remove non-alphanumeric characters and convert to lower case
	        word = word.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
	    }
	    return word;
	}
	

	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionList.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		Node<Avenger> mention = mentionList.getHead();
	    while (mention != null) {
	        System.out.println(mention.getData().toString());
	        mention = mention.getNext();
	    }

		System.out.println("****************************");
		
		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		Node<Avenger> popular = mostPopularList.getHead();
	    while (popular != null) {
	        System.out.println(popular.getData().toString());
	        popular = popular.getNext();
	    }
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		Node<Avenger> unPopular = leastPopularList.getHead();
	    while (unPopular != null) {
	        System.out.println(unPopular.getData().toString());
	        unPopular = unPopular.getNext();
	    }
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		Node<Avenger> alphabetical = alphabticalList.getHead();
	    while (alphabetical != null) {
	        System.out.println(alphabetical.getData().toString());
	        alphabetical = alphabetical.getNext();
	    }
		System.out.println();
	}
	
}
