package comp_2503_assignment_2;

public class Avenger implements Comparable <Avenger>{
	
	private String heroName;
	private String heroAlias;
	private int frequency;
	public static boolean sortByFrequency = false;
	
	public Avenger(String heroName, String heroAlias, int frequency) {
		//super();
		this.heroName = heroName;
		this.heroAlias = heroAlias;
		this.frequency = frequency;
	}
	public String getHeroName() {
		return heroName;
	}
	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	public String getHeroAlias() {
		return heroAlias;
	}
	public void setHeroAlias(String heroAlias) {
		this.heroAlias = heroAlias;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public String toString() {
		return heroAlias + " aka " + heroName + " mentioned " + frequency + " time(s)";
	}
	
	@Override
	public int compareTo(Avenger o) {
		if (!sortByFrequency) {
            if (this.heroAlias.compareTo(o.heroAlias) == 0) {
           	 return this.heroAlias.compareTo(o.heroAlias);
            }
           
       }
		return Integer.compare(this.frequency, o.frequency);
	}
	
	public Avenger implements Comparator<Avenger> {
		public int compare (Avenger a, Avenger, b)
		{
			return a.heroName.compareTo(b.heroName);
		}
	}
	
}
