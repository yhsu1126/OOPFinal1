package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Slime extends Character{
    public Slime(int i) throws SlickException
    {
	super(0,0);
	level=i;
	this.sethp(10+5*level);
	maxhp=hp;
	this.setagility(10+level);
	this.setmp(10+5*level);
	this.setaddmp(1);
	maxmp=mp;
	nation=2;
	this.setpatk(10+level);
	this.setmatk(10+level);
	dead=0;
	this.exp=3*i;
	action.add(new Basicphysic());
	portrait=new Mygraphic(new Image("res/people/scene1_enemy/Slime.png"));
	name="Slime";
    }
}
