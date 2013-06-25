package game;

public class Basicmagic extends Myskill{
    public Basicmagic()
    {
	cost=0;
	special=0;
	pattern=1; // pattern 1 for single hit 2 for all
	name="Tiny bolt";
	description="A tiny bolt that hit\n the enemy.";
	type=2;
	state=0;
	limit=0;
	maxmulti=20;
	minmulti=10;
    }
}
