package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Willowisp extends Character{
    public Willowisp(int i) throws SlickException
    {
	super(0,0);
	level=i;
	this.sethp(30+2*level);
	maxhp=hp;
	this.setagility(10+level);
	this.setmp(10+5*level);
	this.setaddmp(1);
	maxmp=mp;
	nation=2;
	this.setpatk(10+level);
	this.setmatk(15+level);
	this.exp=3*i;
	dead=0;
	action.add(new Basicmagic());
	portrait=new Mygraphic(new Image("res/people/scene1_enemy/Willowisp.png"));
	name="Willowisp";
    }
}
