// This file is part of Zombiepolis
// Zombiepolis is based on MicropolisJ
// Copyright (C) 2014 Brier Hylen Field, Max Marcuse, Sophie Arana, Ahlam Ismail, Carsten Keller, Nina Gerber, Anthea Wiederspohn for Zombiepolis
//
// Zombiepolis is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

import static micropolisj.engine.TileConstants.*;

/**
 * Implements an explosion.
 * An explosion occurs when certain sprites collide,
 * or when a zone is demolished by fire.
 */
public class ZombieExplosionSprite extends Sprite
{
	public ZombieExplosionSprite(Micropolis engine, int x, int y)
	{
		super(engine, SpriteKind.EXP);
		this.x = x;
		this.y = y;
		this.width = 48;
		this.height = 48;
		this.offx = -24;
		this.offy = -24;
		this.frame = 1;
	}

	@Override
	public void moveImpl()
	{
		if (city.acycle % 2 == 0) {
			if (this.frame == 1) {
				city.makeSound(x/16, y/16, Sound.EXPLOSION_HIGH); 
				//city.sendMessageAt(MicropolisMessage.ZOMBIE_DEATH, x/16, y/16);
			}
			this.frame++;
		}

		if (this.frame > 4) { //war 6
			this.frame = 0;

			return;
		}
	}

	void startFire(int xpos, int ypos)
	{
		if (!city.testBounds(xpos, ypos))
			return;

		int t = city.getTile(xpos, ypos);
		if (!isCombustible(t) && t != DIRT)
			return;
		if (isZoneCenter(t))
			return;
		city.setTile(xpos, ypos, (char)(FIRE + city.PRNG.nextInt(4)));
	}
}
