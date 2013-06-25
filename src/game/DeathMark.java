package game;

public class DeathMark extends Myskill{
    public DeathMark()
    {
	cost=35;
	special=0;
	pattern=1; // pattern 1 for single hit 2 for all
	name="Death Mark";
	description="High chance of Critical";
	type=1;
	state=0;
	limit=0;
	maxmulti=10000;
	minmulti=8000;
    }
    
}