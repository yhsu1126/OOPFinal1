package game;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.newdawn.slick.Graphics;

public class Character implements GameObject {
    protected int hp,maxhp,mp,maxmp,agility,nation,patk,matk,pdef,mdef,dead,level,agbuf,addmp,x,y,exp;
    protected int[] stateaccu=new int[32];
    public ArrayList<Myskill> action=new ArrayList<Myskill>();
    String subscription,name;
    Mygraphic portrait;
    //private Mycoordinate pos;
    public Character(int x,int y)
    {
	this.x=x;
	this.y=y;
    }
    public int getnation()
    {
	return this.nation;
    }
    public void sethp(int a)
    {
	this.hp=a;
    }
    public int gethp()
    {
	return this.hp;
    }
    public void setdead(int a)
    {
	this.dead=a;
    }
    public int getdead()
    {
	return this.dead;
    }
    public void setmp(int a)
    {
	this.mp=a;
    }
    public int getmp()
    {
	return this.mp;
    }
    public void setmaxmp(int a)
    {
	this.maxmp=a;
    }
    public int getmaxmp()
    {
	return this.maxmp;
    }
    public void setagility(int a)
    {
	this.agility=a;
    }
    public int getagility()
    {
	return this.agility;
    }
    public String getname()
    {
	return this.name;
    }
    public Mygraphic getportrait()
    {
	return this.portrait;
    }
    public int getpatk()
    {
	return this.patk;
    }
    public int getmatk()
    {
	return this.matk;
    }
    public int getmaxhp()
    {
	return this.maxhp;
    }
    public int getlvl()
    {
	return this.level;
    }
    public void setlvl(int a)
    {
	this.level=a;
    }
    public void setx(int a)
    {
	this.x=a;
    }
    public int getx()
    {
	return this.x;
    }
    public void sety(int a)
    {
	this.y=a;
    }
    public int gety()
    {
	return this.y;
    }
    public void setaddmp(int a)
    {
	this.addmp=a;
    }
    public int getaddmp()
    {
	return this.addmp;
    }
    public void setpatk(int a)
    {
	this.patk=a;
    }
    public void setmatk(int a)
    {
	this.matk=a;
    }
    public void setpdef(int a)
    {
	this.pdef=a;
    }
    public void setmdef(int a)
    {
	this.mdef=a;
    }
    public int getpdef()
    {
	return this.pdef;
    }
    public int getmdef()
    {
	return this.mdef;
    }
    public void setname(String a)
    {
	this.name=a;
    }
    
    public int getwidth()
    {
	return this.portrait.getWidth();
    }
    
    public int getheight()
    {
	return this.portrait.getHeight();
    }
    @Override
    public void render(Graphics g) {
	portrait.renderAtLocation(g, x, y);
    }
    public boolean hittest(int x,int y)
    {
	if(x>=this.x && x<=this.x+portrait.getWidth() && y<=480-this.y && y>= 480-this.y-portrait.getHeight())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }
    
    public void levelup()
    {
	level++;
	this.sethp(150+10*level);
	maxhp=hp;
	this.setagility(80+level);
	this.setmp(100+10*level);
	maxmp=mp;
	this.setpatk(30+level);
	this.setmatk(10+level);
	this.setpdef(5+level);
	this.setmdef(5+level);
	this.exp=10*level;
    }
}
