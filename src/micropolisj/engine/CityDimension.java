// This file is part of Zombiepolis
// Zombiepolis is based on MicropolisJ
// Copyright (C) 2013-2014 Jason Long for MicropolisJ
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// Zombiepolis is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

/**
 * Encapsulates the width and height of a rectangular section of a Micropolis city.
 */
public class CityDimension
{
	public int width;
	public int height;

	public CityDimension(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	public int hashCode()
	{
		return width*33+height;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof CityDimension) {
			CityDimension rhs = (CityDimension) obj;
			return this.width == rhs.width && this.height == rhs.height;
		}
		else {
			return false;
		}
	}

	@Override
	public String toString()
	{
		return width+"x"+height;
	}
}
