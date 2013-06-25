package game;

public class ShadowSlash extends Myskill {
    public ShadowSlash()
    {
	cost=50;
	special=0;
	pattern=2; // pattern 1 for single hit 2 for all
	name="Shadow Slash";
	description="Shadow made sword slash\nfrom everywhere.";
	type=1;
	state=0;
	limit=0;
	maxmulti=10000;
	minmulti=1000;
    }
    
}
