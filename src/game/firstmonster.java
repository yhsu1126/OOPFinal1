package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class firstmonster extends Character{
    public firstmonster(int i) throws SlickException {
	super(0, 0);
	level=i;
	this.sethp(50+10*level);
	maxhp=hp;
	this.setagility(60+level);
	this.setmp(100+10*level);
	maxmp=mp;
	nation=2;
	exp=3*level;
	this.setpatk(30+level);
	this.setmatk(10+level);
	this.setpdef(5+level);
	this.setmdef(5+level);
	dead=0;
	portrait=new Mygraphic(new Image("res/monster1.png"));
	action.add(new Basicphysic());
	name="HybridMystic";
    }
}
