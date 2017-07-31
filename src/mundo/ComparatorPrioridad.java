package mundo;

public class ComparatorPrioridad implements IComparator<LocalAuthority> 
{

	public Double compare(LocalAuthority o1, LocalAuthority o2) {
		return -(o1.darPrioridad()-o2.darPrioridad());
	}

}
