package micropolisj.engine;

import java.util.ArrayList;

public class HunterSprite extends Sprite {

	int count;
	int destX;
	int destY;
	int origX;
	int origY;

	int radius = 500;
	
	static int [] CDx = { 0,  0,  3,  5,  3,  0, -3, -5, -3 };
	static int [] CDy = { 0, -5, -3,  0,  3,  5,  3,  0, -3 };
	static final int SOUND_FREQ = 200;

	public HunterSprite(Micropolis engine, int xpos, int ypos)
	{
		super(engine, SpriteKind.HUN);
		this.x = xpos * 16 + 8; //dies ändern
		this.y = ypos * 16 + 8;
		this.width = 32;
		this.height = 32;
		this.offx = -16;
		this.offy = -16;

		this.destX = city.PRNG.nextInt(city.getWidth()) * 16 + 8;
		this.destY = city.PRNG.nextInt(city.getHeight()) * 16 + 8;

		this.origX = x;
		this.origY = y;
		this.count = 20; //war 1500
		this.frame = 5;
	}

	public int getRadius(){
		return radius;
	}
	
	public int getOrigX(){
		return this.origX;
	}
	
	public int getOrigY(){
		return this.origY;
	}
	
	@Override
	public void moveImpl()
	{
		if (this.count > 0) {
			this.count--;
		}

		if (this.count == 0) {

			// attract hunter to zombies
			if (city.hasSprite(SpriteKind.ZOM)) {

	            //ZombieSprite zombie = (ZombieSprite) city.getSprite(SpriteKind.ZOM);
	            ArrayList<Sprite> zombie_sprites = city.getAllSprites(SpriteKind.ZOM);
	            
	            int min_dist=1000000;
	            for(int i=0;i<zombie_sprites.size();i++) {
	            	ZombieSprite zombie=(ZombieSprite)zombie_sprites.get(i);
	            	if(getDis(zombie.x,zombie.y,x,y) < min_dist && getDis(origX, origY, zombie.x, zombie.y) < radius) {
	            		min_dist=getDis(zombie.x,zombie.y,x,y);
	            		this.destX = zombie.x;
	    	            this.destY = zombie.y;
	            	}
	            }
	            if(min_dist > 100000) {
	            	this.destX = origX;
	                this.destY = origY;
	            }
			}
	            
	            else {
	                this.destX = origX;
	                this.destY = origY;
	            }

	        }
			else {
				this.destX = origX;
				this.destY = origY;
			}
			
		

		int z = this.frame;
		if (city.acycle % 3 == 0) {
			int d = getDir(x, y, destX, destY);
			z = turnTo(z, d);
			this.frame = z;
		}
		x += CDx[z];
		y += CDy[z];
		
		for (Sprite s : city.allSprites())
		{
			if (checkSpriteCollision(s) &&
				(s.kind == SpriteKind.ZOM)
				) {
				s.frame = 0;
				city.sprites.add(new ZombieExplosionSprite(city, s.x, s.y));
			}
		}
		
		
		
	}
}
