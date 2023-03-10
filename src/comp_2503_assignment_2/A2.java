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
			
		Node<Avenger> object1 = mostPopularList.getHead();
		if (object1 == null || object1.getNext() == null) {
	        return; // list is empty or has only one element, nothing to sort
	    }
		
		// Most popular list sorting.
	    boolean swapped = true;
	    Node<Avenger> lastSorted = null;
	    while (swapped) {
	        swapped = false;
	        Node<Avenger> popular = mostPopularList.getHead();
	        Node<Avenger> previous = null;
	        while (popular != lastSorted && popular.getNext() != null) {
	            if (popular.getData().getFrequency() < popular.getNext().getData().getFrequency()) {
	                // swap current node with next node
	                Node<Avenger> temp = popular.getNext();
	                popular.setNext(temp.getNext());
	                temp.setNext(popular);
	                if (previous == null) {
	                    mostPopularList.setHead(temp);
	                } else {
	                    previous.setNext(temp);
	                }
	                previous = temp;
	                swapped = true;
	            } else {
	                previous = popular;
	                popular = popular.getNext();
	            }
	        }
	        lastSorted = previous;
	    }
	    mostPopularList.setTail(lastSorted);
	    
	    // Least popular list sorting.
	    swapped = true;
	    lastSorted = null;
	    while (swapped) {
	        swapped = false;
	        Node<Avenger> current = leastPopularList.getHead();
	        Node<Avenger> previous = null;
	        while (current != lastSorted && current.getNext() != null) {
	            if (current.getData().getFrequency() > current.getNext().getData().getFrequency()) {
	                // swap current node with next node
	                Node<Avenger> temp = current.getNext();
	                current.setNext(temp.getNext());
	                temp.setNext(current);
	                if (previous == null) {
	                    leastPopularList.setHead(temp);
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
	        lastSorted = previous;
	    }
	    leastPopularList.setTail(lastSorted);
	    
	    //Alphabetical list sorting.
	    swapped = true;
	    lastSorted = null;
	    while (swapped) {
	        swapped = false;
	        Node<Avenger> alpha = alphabticalList.getHead();
	        Node<Avenger> previous = null;
	        while (alpha != lastSorted && alpha.getNext() != null) {
	            if (alpha.getData().getHeroAlias().compareTo(alpha.getNext().getData().getHeroAlias()) > 0) {
	                // swap current node with next node
	                Node<Avenger> temp = alpha.getNext();
	                alpha.setNext(temp.getNext());
	                temp.setNext(alpha);
	                if (alpha == alphabticalList.getHead()) {
	                    alphabticalList.setHead(temp);
	                } else {
	                    previous.setNext(temp);
	                }
	                previous = temp;
	                swapped = true;
	            } else {
	                previous = alpha;
	                alpha = alpha.getNext();
	            }
	        }
	        lastSorted = previous;
	    }
	    alphabticalList.setTail(lastSorted);
	}
	
	// Reads the input one word at a time
	private void readInput() throws FileNotFoundException {
		
		File file = new File(input.nextLine()); //ask for the file path as input
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
            String cleanedWord = cleanWord(sc.next()); //clean the words with the word cleaner
            if (!cleanedWord.isEmpty() || !cleanedWord.equals("")) { //make sure the word isn't empty
	            for (int i = 0; i < avengerRoster.length; i++) {
	                for (int j = 0; j < avengerRoster[i].length; j++) {
	                    if (cleanedWord.equals(avengerRoster[i][j])) { //compare each word to all the possible avengers
	                    	Avenger currentAvenger; 
	                		currentAvenger = new Avenger(avengerRoster[i][0], avengerRoster[i][1], 1);
	                		addToLists(currentAvenger); //once the avenger has been detected, it is sent to be added to the SLLs
	                    }
	                }
	            }
	        	totalwordcount++;
            }
        }
		sc.close();
	}
	
	//Method to add the Avengers as they are found in the text.
	private void addToLists(Avenger currentAvenger) {
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
		if (!found) { //adding the Avengers to all the lists, to be sorted later
		    mentionList.nodeToAdd(currentAvenger);
		    alphabticalList.nodeToAdd(currentAvenger);
		    mostPopularList.nodeToAdd(currentAvenger);
		    leastPopularList.nodeToAdd(currentAvenger);
		}
	}
	
	private String cleanWord(String word) {
		if (word != null) { // check to make sure word is not null, in case of error
            word = word.trim(); // trim off blank spaces
            if (!word.equals("")) { // check for blank word 
            
	            //remove apostrophe
	            int index = word.indexOf("'"); 
	            if (index > 0) 
	            	word = word.substring(0,index);
	            // Remove special characters and convert to lower case
	            word = word.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            }
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
		
		int count = 0; //count used to make sure the popularity lists don't go over 4

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output
		Node<Avenger> mention = mentionList.getHead();
	    while (mention != null) {
	        System.out.println(mention.getData().toString());
	        mention = mention.getNext();
	    }

		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		Node<Avenger> popular = mostPopularList.getHead();
	    while (popular != null && count < 4) {
	        System.out.println(popular.getData().toString());
	        popular = popular.getNext();
	        count++;
	    }
	    count = 0;
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		Node<Avenger> unPopular = leastPopularList.getHead();
	    while (unPopular != null && count < 4) {
	        System.out.println(unPopular.getData().toString());
	        unPopular = unPopular.getNext();
	        count++;
	    }
	    System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of Avengers in alphabetical order
		Node<Avenger> alphabetical = alphabticalList.getHead();
	    while (alphabetical != null) {
	        System.out.println(alphabetical.getData().toString());
	        alphabetical = alphabetical.getNext();
	    }
		System.out.println();
	}
	
}
