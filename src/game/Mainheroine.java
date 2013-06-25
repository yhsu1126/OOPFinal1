package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Mainheroine extends Character {

    public Mainheroine(int i) throws SlickException {
	super(270, 380);
	level=i;
	this.sethp(70+10*level);
	maxhp=hp;
	this.setagility(50+level);
	this.setmp(200+10*level);
	this.setaddmp(20);
	maxmp=mp;
	nation=1;
	this.setpatk(10+level);
	this.setmatk(30+level);
	this.setpdef(5+level);
	this.setmdef(10+level);
	dead=0;
	exp=10;
	action.add(new Basicmagic());
	portrait=new Mygraphic(new Image("res/people/actress/actress_face.png"));
	name="I";
    }
    
}