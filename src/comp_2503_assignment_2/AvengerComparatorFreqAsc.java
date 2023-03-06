package comp_2503_assignment_2;

import java.util.Comparator;

public class AvengerComparatorFreqAsc implements Comparator <Avenger>{

	@Override
	public int compare(Avenger a1, Avenger a2) {
		int diff = a2.getFrequency() - a1.getFrequency(); //order by frequency
		if (diff == 0) {
			diff = a1.getHeroName().length() - a2.getHeroName().length(); //order by length of name
			if (diff == 0) {
				return a1.getHeroName().compareTo(a2.getHeroName()); //order alphabetically
			}
		}
		return diff;
	}
}
