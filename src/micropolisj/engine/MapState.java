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
 * Lists the various map overlay options that are available.
 */
public enum MapState
{
	ALL,                //ALMAP
	RESIDENTIAL,        //REMAP
	COMMERCIAL,         //COMAP
	INDUSTRIAL,         //INMAP
	TRANSPORT,          //RDMAP
	POPDEN_OVERLAY,     //PDMAP
	GROWTHRATE_OVERLAY, //RGMAP
	LANDVALUE_OVERLAY,  //LVMAP
	CRIME_OVERLAY,      //CRMAP
	POLLUTE_OVERLAY,    //PLMAP
	TRAFFIC_OVERLAY,    //TDMAP
	POWER_OVERLAY,      //PRMAP
	FIRE_OVERLAY,       //FIMAP
	POLICE_OVERLAY,     //POMAP
	HUNTER_OVERLAY;		//HUMAP
}
