package game;

public class RazorShuriken extends Myskill{
    public RazorShuriken()
    {
	cost=30;
	special=0;
	pattern=1; // pattern 1 for single hit 2 for all
	name="Razor Shuriken";
	description="throw a bunch of shuriken\nto the enemy.";
	type=1;
	state=0;
	limit=0;
	maxmulti=60;
	minmulti=20;
    }
    
}