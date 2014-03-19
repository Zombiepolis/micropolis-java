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
		this.count = 1500;
		this.frame = 5;
	}

	@Override
	public void moveImpl()
	{
		if (this.count > 0) {
			this.count--;
		}

		if (this.count == 0) {

			// attract copter to monster and tornado so it blows up more often
			if (city.hasSprite(SpriteKind.GOD)) {

				MonsterSprite monster = (MonsterSprite) city.getSprite(SpriteKind.GOD);
				this.destX = monster.x;
				this.destY = monster.y;

			}
			else if (city.hasSprite(SpriteKind.TOR)) {

				TornadoSprite tornado = (TornadoSprite) city.getSprite(SpriteKind.TOR);
				this.destX = tornado.x;
				this.destY = tornado.y;

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
	}
}
