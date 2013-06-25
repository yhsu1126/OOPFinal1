package game;

public class Basicphysic extends Myskill{
    public Basicphysic()
    {
	cost=0;
	special=0;
	pattern=1; // pattern 1 for single hit 2 for all
	name="Basic attack";
	description="A strong blow that hit\n the enemy.";
	type=1;
	state=0;
	limit=0;
	maxmulti=20;
	minmulti=10;
    }
    
}
