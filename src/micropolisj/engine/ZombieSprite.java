// This file is part of MicropolisJ.
// Copyright (C) 2013 Jason Long
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// MicropolisJ is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.engine;

import static micropolisj.engine.TileConstants.*;

import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Implements a zombie (one of the Micropolis disasters).
 */
public class ZombieSprite extends Sprite
{
	int count;
	int soundCount;
	int destX;
	int destY;
	int origX;
	int origY;
	int step;
	double slowFactor;
	boolean flag; //true if the zombie wants to return home
	
	//GODZILLA FRAMES
	//   1...3 : northeast
	//   4...6 : southeast
	//   7...9 : southwest
	//  10..12 : northwest
	//      13 : north
	//      14 : east
	//      15 : south
	//      16 : west

	// movement deltas
	static int [] Gx = { 2, 2, -2, -2, 0 };
	static int [] Gy = { -2, 2, 2, -2, 0 };

	static int [] ND1 = {  0, 1, 2, 3 };
	static int [] ND2 = {  1, 2, 3, 0 };
	static int [] nn1 = {  2, 5, 8, 11 };
	static int [] nn2 = { 11, 2, 5,  8 };

	public ZombieSprite(Micropolis engine, int xpos, int ypos)
	{
		super(engine, SpriteKind.ZOM);
		this.x = xpos * 16 + 8;
		this.y = ypos * 16 + 8;
		this.width = 48;
		this.height = 48;
		this.offx = -24;
		this.offy = -24;
		this.slowFactor = 1;

		this.origX = x;
		this.origY = y;

		this.frame = xpos > city.getWidth() / 2 ?
			(ypos > city.getHeight() / 2 ? 10 : 7) :
			(ypos > city.getHeight() / 2 ? 1 : 4);

		this.count = 1000;
		
		CityLocation p;
		
		p = new CityLocation((int)(Math.random()*(engine.map[0].length - 19))+10,(int)(Math.random()*(engine.map.length - 9))+5);
			
		this.destX = p.x * 16 + 8;
		this.destY = p.y * 16 + 8;
		this.flag = false;
		this.step = 1;
	}

	@Override
	public void moveImpl()
	{
		if (this.frame == 0) {
			return;
		}
		int tempframe=frame;
		if(frame > 16) tempframe-=16;

		if (soundCount > 0) {
			soundCount--;
		}

		int d = (tempframe - 1) / 3;   // basic direction
		int z = (tempframe - 1) % 3;   // step index (only valid for d<4)

		if (d < 4) { //turn n s e w
			assert step == -1 || step == 1;
			if (z == 2) step = -1;
			if (z == 0) step = 1;
			z += step;

			int c = getDir(x, y, destX, destY);
			c = (c - 1) / 2;   //convert to one of four basic headings
			assert c >= 0 && c < 4;

			if ((c != d) && city.PRNG.nextInt(11) == 0) {
				// randomly determine direction to turn
				if (city.PRNG.nextInt(2) == 0) {
					z = ND1[d];
				}
				else {
					z = ND2[d];
				}
				d = 4;  //transition heading

				if (soundCount == 0) {
					//city.makeSound(x/16, y/16, Sound.MONSTER);
					soundCount = 50 + city.PRNG.nextInt(101);
				}
			}
		}
		else {
			assert tempframe >= 13 && tempframe <= 16;

			int z2 = (tempframe - 13) % 4;

			if (city.PRNG.nextInt(4) == 0) {
				int newFrame;
				if (city.PRNG.nextInt(2) == 0) {
					newFrame = nn1[z2];
				} else {
					newFrame = nn2[z2];
				}
				d = (newFrame-1) / 3;
				z = (newFrame-1) % 3;

				assert d < 4;
			}
			else {
				d = 4;
			}
		}

		if(this.count % slowFactor == 0) {
			tempframe = ((d * 3) + z) + 1;

			assert tempframe >= 1 && tempframe <= 16;

			this.x += Gx[d];
			this.y += Gy[d];
		}

		if (this.count > 0) {
			this.count--;
		}
		// jedesmal wenn ein zombie 250 ticks ueberlebt, wird der counter wieder um eins erhoeht, aber nicht hoeher als 20
		if(this.count % 250 == 0 && city.zombie_cat_counter < 20) city.zombie_cat_counter++; 

		int c = getChar(x, y);
		//if(c >= RIVER && c <= LASTRIVEDGE) {
		//if(c >= 2 && c <= 6 || c == 18 || c == 17 || c == 14 || c == 13) {
		if(c >= 2 && c <= 6 || c == 18 || c == 17 || c == 14 || c == 13 || c == 10 || c == 9) {
			// zombie langsamer machen
			slowFactor=3;
			frame=tempframe+16;
		}
		else {
			// zombie normalschnell machen
			slowFactor=1;
			frame=tempframe;
			//JOptionPane.showMessageDialog(null,"zombie ist NICHT im wasser");
		}
		if (c == -1) {
			//zombie killen und neu spawnen
			kill();
			city.makeZombieAt(city.zombiespawn_x,city.zombiespawn_y);
		}

		for (Sprite s : city.allSprites())
		{
			if (checkSpriteCollision(s) &&
				(s.kind == SpriteKind.SHI ||
				 s.kind == SpriteKind.TRA ||
				 s.kind == SpriteKind.GOD)
				) {
				s.explodeSprite();
			}
		}
		
		zombieDestroy(x / 16, y / 16);
	}
	
	public void kill() {
		this.frame=0;
		city.zombieCount--;
		city.sprites.remove(city.getCertainSprite(SpriteKind.ZOM, x, y));
		if(this.count > 850) {
			city.zombie_cat_counter--;
			if(city.zombie_cat_counter <= 0) {
				city.zombie_cat_counter=10;
				city.sendMessageAt(MicropolisMessage.ZOMBIE_REVENGE_ATTACK, x,y);
				city.makeSound(0, 0, Sound.MONSTER);
				for(int a=0;a<10;a++) city.makeZombie();
			}
		}
		System.gc();
	}
}
