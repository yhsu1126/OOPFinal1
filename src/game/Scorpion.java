package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Scorpion extends Character{
    public Scorpion(int i) throws SlickException {
   	super(0, 0);
   	level=i;
   	this.sethp(80+10*level);
   	maxhp=hp;
   	this.setagility(50+level);
   	this.setmp(100+10*level);
   	maxmp=mp;
   	nation=2;
   	exp=3*level;
   	this.setpatk(30+level);
   	this.setmatk(10+level);
   	this.setpdef(5+level);
   	this.setmdef(5+level);
   	dead=0;
   	portrait=new Mygraphic(new Image("res/people/scene2_enemy/Scorpion.png"));
   	action.add(new Basicphysic());
   	name="HybridMystic";
       }
}
