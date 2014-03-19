package micropolisj.engine;

public class HunterSprite extends Sprite {

	int count;
	int destX;
	int destY;
	int origX;
	int origY;

	static int [] CDx = { 0,  0,  3,  5,  3,  0, -3, -5, -3 };
	static int [] CDy = { 0, -5, -3,  0,  3,  5,  3,  0, -3 };
	static final int SOUND_FREQ = 200;

	public HunterSprite(Micropolis engine, int xpos, int ypos)
	{
		super(engine, SpriteKind.HUN);
		this.x = xpos * 16 + 8;
		this.y = ypos * 16 + 8;
		this.width = 32;
		this.height = 32;
		this.offx = -16;
		this.offy = -16;

		this.destX = city.PRNG.nextInt(city.getWidth()) * 16 + 8;
		this.destY = city.PRNG.nextInt(city.getHeight()) * 16 + 8;

		this.origX = x;
		this.origY = y;
		this.count = 500; //war 1500
		this.frame = 5;
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

				ZombieSprite zombie = (ZombieSprite) city.getSprite(SpriteKind.ZOM);
				this.destX = zombie.x;
				this.destY = zombie.y;

			}
			else {
				this.destX = origX;
				this.destY = origY;
			}

			if (getDis(x, y, origX, origY) < 30) {
				// made it back to airport, go ahead and land.
				this.frame = 0;
				return;
			}
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
				s.explodeSprite();
			}
		}
		
	}
}
