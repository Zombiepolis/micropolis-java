// This file is part of Zombiepolis
// Zombiepolis is based on MicropolisJ
// Copyright (C) 2014 Brier Hylen Field, Max Marcuse, Sophie Arana, Ahlam Ismail, Carsten Keller, Nina Gerber, Anthea Wiederspohn for Zombiepolis
//
// Zombiepolis is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

public class ZombLevel {

	public static final int MIN_LEVEL = 0;
	public static final int MAX_LEVEL = 2;
	public static final int STD_LEVEL = 1;

	public static boolean isValid(int lev)
	{
		return lev >= MIN_LEVEL && lev <= MAX_LEVEL;
	}

	//prevent this class from being instantiated
	private ZombLevel() {}
}
