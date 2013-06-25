package game;

public class LivingShadow extends Myskill {
    public LivingShadow()
    {
	cost=30;
	special=0;
	pattern=2; // pattern 1 for single hit 2 for all
	name="Living Shadow";
	description="Use your shadow to\nambush the enemy.";
	type=1;
	state=0;
	limit=0;
	maxmulti=40;
	minmulti=20;
    }

}
