package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MainHero extends Character {

    public MainHero(int i) throws SlickException {
	super(20, 380);
	level=i;
	this.sethp(150+10*level);
	maxhp=hp;
	this.setagility(80+level);
	this.setmp(100+10*level);
	this.setaddmp(10);
	maxmp=mp;
	nation=1;
	this.setpatk(30+level);
	this.setmatk(10+level);
	this.setpdef(10+level);
	this.setmdef(10+level);
	exp=10;
	dead=0;
	portrait=new Mygraphic(new Image("res/people/actor/actor_face.png"));
	name="Guaya";
	action.add(new Basicphysic());
	action.add(new RazorShuriken());
	action.add(new DeathMark());
	action.add(new LivingShadow());
	action.add(new ShadowSlash());
    }
    
}
