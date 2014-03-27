// This file is part of Zombiepolis
// Zombiepolis is based on MicropolisJ 
// Copyright (C) 2014 Brier Hylen Field, Max Marcuse, Sophie Arana, Ahlam Ismail, Carsten Keller, Nina Gerber, Anthea Wiederspohn  for Zombiepolis
// Portions Copyright (C) 2013 Jason Long for MicropolisJ
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// Zombiepolis is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

/**
 * Enumeration of the various kinds of sprites that may appear in the city.
 */
public enum SpriteKind
{
	TRA(1,5),
	COP(2,8),
	AIR(3,11),
	SHI(4,8),
	GOD(5,16),
	TOR(6,3),
	EXP(7,6),
	BUS(8,4),
	ZOM(9,32),
	HUN(10,8);

	public final int objectId;
	public final int numFrames;

	private SpriteKind(int objectId, int numFrames)
	{
		this.objectId = objectId;
		this.numFrames = numFrames;
	}
}
