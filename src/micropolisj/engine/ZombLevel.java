package micropolisj.engine;

public class ZombLevel {

	public static final int MIN_LEVEL = 0;
	public static final int MAX_LEVEL = 2;

	public static boolean isValid(int lev)
	{
		return lev >= MIN_LEVEL && lev <= MAX_LEVEL;
	}

	//prevent this class from being instantiated
	private ZombLevel() {}
}
