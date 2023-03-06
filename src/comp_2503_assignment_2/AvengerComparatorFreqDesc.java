package comp_2503_assignment_2;

import java.util.Comparator;

public class AvengerComparatorFreqDesc implements Comparator <Avenger>{

	@Override
	public int compare(Avenger a1, Avenger a2) {
		int diff = a2.getFrequency() - a1.getFrequency();
		if (diff == 0) {
			return a1.getHeroAlias().compareTo(a2.getHeroAlias());
		}
		return diff;
	}
}
