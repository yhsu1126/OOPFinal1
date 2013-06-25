package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
    
    Image Background,bottom,dialogue,sheet,tooltip;
    public String mousepos="Mouse at ( 0, 0 )";
    protected LinkedList <Object>gameObjHelpers = new LinkedList <Object>();
    protected LinkedList <Object>deadObj = new LinkedList <Object>();
    private ArrayList<Character> BattleList;
    Random randomnumber=new Random();
    int ally,enemy,allyhp,enemyhp,progress,result=0,focusbutton=0,index=0,sidemenuselect=0,sidemenufocus=0,sheetfocus=0,sheetselect=0,sheetsize,skillcost,remain,focustarget,chat,returnstate,exp;
    int targetx[]={290,290,290,505,505,505}; int targety[]={260,285,310,260,285,310};
    int[] targetkey=new int[6];
    String tooltipdes,dialoguedis;
    boolean quit=false,showdialogue=false,mousepressing=false;
    Character tmp;
    public Play(int state)
    {
	super();
    }
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
	bottom=new Image("res/frame/BottomFrame.png");
	sheet=new Image("res/frame/InvFrame.png");
	dialogue=new Image("res/frame/Dialogue.png");
	tooltip=new Image("res/frame/tooltip.png");
	this.exp=0;
    }
    
    public void startBattle(Image back,ArrayList<Character> list,int returnstate)
    {
	this.setBackground(back);
	this.SetBattleList(list);
	this.SetPos();
	this.orderlist();
	this.returnstate=returnstate;
	progress=0;
	index=0;
	sidemenuselect=0;
	sheetsize=0;
	focustarget=100;
	chat=0;
	exp=0;
	result=0;
	tmp=null;
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
	int i;
	Background.draw(0,0);
	bottom.draw(0,340);
	for(Character test : BattleList)
	{
	    if(test.getnation()==1)
	    {
		test.render(g);
		g.setColor(Color.black);
		g.drawRect(test.getx(), test.gety(), test.getwidth(), test.getheight());
		g.drawString("Lv: "+test.getlvl(), test.getx()+test.getwidth()+10, test.gety());
		g.drawString("HP: "+test.gethp()+"/"+test.getmaxhp(),test.getx()+test.getwidth()+10,test.gety()+20);
		g.drawString("MP: "+test.getmp()+"/"+test.getmaxmp(),test.getx()+test.getwidth()+10,test.gety()+40);
	    }
	    else
	    {
		if(test.dead==0)
		{
		    test.render(g);
		}
	    }
	    g.setColor(Color.white);
	}
	if(focustarget<6 && BattleList.get(targetkey[focustarget]).dead==0)
	{
	    	g.setColor(Color.red);
	    	g.drawRect(BattleList.get(targetkey[focustarget]).getx(), BattleList.get(targetkey[focustarget]).gety(), BattleList.get(targetkey[focustarget]).portrait.getWidth(), BattleList.get(targetkey[focustarget]).portrait.getHeight());
	    	g.setColor(Color.black);
	}
	g.setColor(new Color(128,128,128));
	switch(this.sidemenuselect)
	{
        	case 1:
        	    g.fillRect(500, 375, 100, 30);
        	    break;
        	case 2:
        	    g.fillRect(500, 405, 100, 30);
        	    break;
	}
	g.setColor(Color.black);
	g.drawString("Skill",520,380);
	g.drawString("Item",520,410);
	g.drawString("Flee",520,440);
	g.setColor(Color.black);
	switch(this.sidemenufocus)
	{
		case 1:
		    g.drawRect(500, 375, 100, 30);
		    break;
		case 2:
		    g.drawRect(500, 405, 100, 30);
		    break;
		case 3:
		    g.drawRect(500, 435, 100, 30);
		    break;
	}
	if(this.sidemenuselect>0)
	{
	    sheet.draw(10,10);
	    g.drawString("Right click\nto close", 185, 480-155);
	    if(sidemenuselect==1)
	    {
		int h=30;
		g.setColor(Color.black);
		for(Myskill a : tmp.action)
		{
		   if(a.limit<=tmp.getlvl())
		   {
		    g.drawString(a.name, 80, h);
		    h+=25;
		   }
		}
	    }
	    if(sheetfocus>=0 && sheetfocus<sheetsize)
	    {
		this.tooltip.draw((float)Mouse.getX(),480-(float)Mouse.getY());
		g.drawRect(20, 30+sheetfocus*25, 255, 20);
		g.drawString("Cost :"+this.skillcost, Mouse.getX()+60, 480-Mouse.getY()+65);
		g.drawString(tooltipdes,Mouse.getX()+60,480-Mouse.getY()+90);
	    }
	}
	if(this.showdialogue==true)
	{
	    dialogue.draw(0,480-275);
	    g.drawString(this.dialoguedis, 55, 480-190);
	    if(progress==3)
	    {
		if(tmp.action.get(this.sheetselect).type!=3)
		{
		    i=0;
		    for(Character a:BattleList)
		    {
			if(a.getnation()==2 && a.dead==0)
			{
			    g.drawString(a.getname()+" ("+a.getlvl()+")", this.targetx[i], this.targety[i]);
			    if(i==this.focustarget)
			    {
				g.drawRect(this.targetx[i]-5, this.targety[i]-2, 215, 20);
			    }
			    i++;
			}
		    }
		}
		else
		{
		    i=0;
		    for(Character a:BattleList)
		    {
			if(a.getnation()==1)
			{
			    g.drawString(a.getname()+" ("+a.getlvl()+")", this.targetx[i], this.targety[i]);
			    if(i==this.focustarget)
			    {
				g.drawRect(this.targetx[i]-5, this.targety[i]-2, 215, 20);
			    }
			    i++;
			}
		    }
		}
	    }
	}
	g.setColor(Color.white);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
    {
	if(result==0)
	{
	    // the game is running
	    //progress => 0 fetch new character 1=> Ally 2=> AI 3=> waiting for choosing skill's target 4=>
	    if(progress==0)
	    {
        	    if(index>=BattleList.size())
        	    {
        		index=0;
        	    }
        	    while(BattleList.get(index).dead!=0)
        	    {
        		index++;
        		if(index>=BattleList.size())
        		{
        		    index=0;
        		}
        	    }
        	    tmp=BattleList.get(index);
        	    if(tmp.getmp()+tmp.getaddmp()>tmp.getmaxmp())
        	    {
        		tmp.setmp(tmp.getmaxmp());
        	    }
        	    else
        	    {
        		tmp.setmp(tmp.getmp()+tmp.getaddmp());
        	    }
        	    index++;
        	    progress=1;
	    }
	    if(tmp.getnation()==2)
	    {
		progress=2;
	    }
	}
	else if(result==1)
	{
	    this.exp=0;
	    for(Character a : BattleList)
	    {
		if(a.nation==2)
		{
		    exp+=a.exp;
		}
	    }
	    //System.out.printf("Finish counting I have %d exp waiting\n",this.exp);
	    sbg.enterState(this.returnstate);
	}
	else if(result==-1)
	{
	    sbg.enterState(0);
	}
	else if(result==-2)
	{
	    sbg.enterState(this.returnstate);
	    exp=0;
	}
	int mouseX=Mouse.getX(),mouseY=Mouse.getY();
	this.sidemenufocus=0;
	this.sheetfocus=100;
	this.focustarget=100;
	/*if(Mouse.isButtonDown(0))
	{
	    mousepressing=true;
	}
	else
	{
	    mousepressing=false;
	}*/
	//mousepos="Mouse at ( "+mouseX+", "+mouseY+" )";
	if(progress==1)
	{
	    if(mouseX>510 && mouseX<600 && mouseY<100 && mouseY>90)
	    {
		this.sidemenufocus=1;
		if(Mouse.isButtonDown(0))
		{
		    this.sidemenuselect=1;
		    this.sheetsize=0;
		    for(Myskill a: tmp.action)
		    {
			if(a.limit<=tmp.getlvl())
			{
			    this.sheetsize++;
			}
		    }
		}
	    }
	    if(mouseX>510 && mouseX<600 && mouseY<70 && mouseY>60)
	    {
		this.sidemenufocus=2;
		if(Mouse.isButtonDown(0))
		{
		    this.sidemenuselect=2;
		}
	    }
	    if(mouseX>510 && mouseX<600 && mouseY<40 && mouseY>30)
	    {
		if(Mouse.isButtonDown(0))
		{
		    this.result=-2;
		}
	    }
	    if(sidemenuselect>0)
		{
		    if(mouseX>20 && mouseX<275 && mouseY>120 && mouseY<450)
		    {
			this.sheetfocus=(450-mouseY)/25;
			if(this.sheetfocus<this.sheetsize)
			{
			    if(progress==1)
			    {
			    this.skillcost=tmp.action.get(sheetfocus).cost;
			    this.tooltipdes=tmp.action.get(sheetfocus).description;
			    }
			}
			if(Mouse.isButtonDown(0))
			{
			    if(this.sheetfocus<this.sheetsize)
			    {
	        		    if(tmp.action.get(sheetfocus).pattern==1)
	        		    {
	                		    this.sheetselect=sheetfocus;
	                		    this.sheetfocus=20;
	                		    this.progress=3;
	                		    this.sidemenuselect=0;
	                		    this.showdialogue=true;
	                		    this.dialoguedis="Please select your target";
	                		    if(tmp.action.get(this.sheetselect).type!=3)
	                		    {
	                			    int i=0,j=0;
	                			    for(Character a:BattleList)
	                			    {
	                				if(a.getnation()==2 && a.dead==0)
	                				{
	                				    this.targetkey[i]=j;
	                				    i++;
	                				}
	                				j++;
	                			    }
	                		    }
	                		    else
	                		    {
	                			int i=0,j=0;
	                			for(Character a:BattleList)
	                			{
	                			    if(a.getnation()==1)
	                			    {
	                				this.targetkey[i]=j;
	                				i++;
	                			    }
	                			    j++;
	                			}
	                		    }
	        		    }
	        		    else
	        		    {
	        			progress=4;
	        			focustarget=0;
	        			targetkey[0]=0;
	        		    }
			    }
			}
		    }
		}
	}
	if(progress==2)
	{
	    int choose=tmp.action.size()-1;
	    while(tmp.action.get(choose).cost>tmp.getmp())
	    {
		choose--;
	    }
	    int minhp=2147383647,i=0;
	    for(Character a : BattleList)
	    {
		if(a.nation!=2)
		{
		    if(minhp>a.gethp() && a.dead!=1)
		    {
			minhp=a.gethp();
			this.focustarget=i;
		    }
		}
		i++;
	    }
	    useskill(tmp.action.get(choose),focustarget,tmp);
	    progress=0;
	}
	if(Mouse.isButtonDown(1))
	{
	    if(progress==1)
	    {
		this.sidemenuselect=0;
	    }
	    else if(progress==3)
	    {
		this.sidemenuselect=1;
		this.showdialogue=false;
	    }
	}
	if(progress==3 && sidemenuselect>0 && Mouse.isButtonDown(1)==false)
	{
	    progress=1;
	}
	if(progress==3)
	{
	   if(mouseX>290 && mouseX<720 && mouseY>140 && mouseY<260)
	   {
	       this.focustarget=0;
	       if(mouseX>505)
	       {
		   focustarget+=3;
	       }
	       if(mouseY<220 && mouseY>=195)
	       {
		   focustarget+=0;
	       }
	       else if(mouseY<195 && mouseY>=170)
	       {
		   focustarget+=1;
	       }
	       else
	       {
		   focustarget+=2;
	       }
	       if(Mouse.isButtonDown(0))
	       {
		   this.showdialogue=false;
		   progress=4;
	       }
	   }
	}
	if(progress==4)
	{
	    useskill(tmp.action.get(this.sheetselect),targetkey[focustarget],tmp);
	    progress=0;
	}
    }

    @Override
    public int getID() {
	return 12;
    }
    
    public int getexp()
    {
	return this.exp;
    }
    public void SetBattleList(ArrayList<Character> a)
    {
	this.BattleList=a;
    }
    
    public void clear()
    {
	this.gameObjHelpers.clear();
	this.deadObj.clear();
	result=0;
    }
    
    public void setBackground(Image a)
    {
	this.Background=a;
    }
    
    public void orderlist()
    {
	Collections.sort(BattleList,new petOrder());
    }
    
    public int getreturn()
    {
	return this.returnstate;
    }
    
    public void SetPos()
    {
	ally=0;
	enemy=0;
	allyhp=0;
	enemyhp=0;
	for(Character a: BattleList)
	{
	    if(a.getnation()==1)
	    {
		ally++;
		allyhp+=a.getmaxhp();
	    }
	    else
	    {
		enemy++;
		enemyhp+=a.getmaxhp();
	    }
	}
	int i;
	switch(enemy)
	{
	case 1:
	int[] xmiddle={400};
	i=0;
	for(Character a:BattleList)
	{
	    if(a.getnation()==2)
	    {
		a.setx(xmiddle[i]-a.getwidth()/2);
		a.sety(480-130-a.getheight());
		i++;
	    }
	}
	break;
	case 2:
	int[] xmiddle2={300,500};
	i=0;
	for(Character a:BattleList)
	{
	    if(a.getnation()==2)
	    {
		a.setx(xmiddle2[i]-a.getwidth()/2);
		a.sety(480-130-a.getheight());
		i++;
	    }
	}
	break;
	case 3:
	    int[] xmiddle3={400,200,600};
	    i=0;
	    for(Character a:BattleList)
	    {
		if(a.getnation()==2)
		{
		    a.setx(xmiddle3[i]-a.getwidth()/2);
		    a.sety(480-130-a.getheight());
		    i++;
		}
	    }
	    break;
	case 4:
	    int[] xmiddle4={300,500,100,700};
	    i=0;
	    for(Character a:BattleList)
	    {
		if(a.getnation()==2)
		{
		    a.setx(xmiddle4[i]-a.getwidth()/2);
		    a.sety(480-130-a.getheight());
		    i++;
		}
	    }
	    break;
	case 5:
	    int[] xmiddle5={400,250,550,100,700};
	    i=0;
	    for(Character a:BattleList)
	    {
		if(a.getnation()==2)
		{
		    a.setx(xmiddle5[i]-a.getwidth()/2);
		    a.sety(480-130-a.getheight());
		    i++;
		}
	    }
	    break;
	default:
	break;
	}
    }
    public void useskill(Myskill a, int index, Character t)
    {
	t.setmp(t.getmp()-a.cost);
	int damage=0;
	int multi=this.randomnumber.nextInt(a.maxmulti-a.minmulti)+a.minmulti;
	if(a.pattern==1)
	{
	    if(a.type==1)
	    {
		damage=t.getpatk()*(multi)/100;
	    }
	    else if(a.type==2)
	    {
		damage=t.getmatk()*(multi)/100;
	    }
	    else
	    {
		damage=t.getmatk()*multi/100;
	    }
	    if(a.type!=3)
	    {
		BattleList.get(index).sethp(BattleList.get(index).gethp()-damage);
		if(BattleList.get(index).gethp()<0)
		{
		    if(BattleList.get(index).getnation()==2)
		    {
			enemyhp-=BattleList.get(index).getmaxhp();
		    }
		    else
		    {
			allyhp-=BattleList.get(index).getmaxhp();
		    }
		    BattleList.get(index).dead=1;
		}
	    }
	    else
	    {
		if(BattleList.get(index).gethp()+damage>BattleList.get(index).getmaxhp())
		{
		    BattleList.get(index).sethp(BattleList.get(index).getmaxhp());
		}
		else
		{
		    BattleList.get(index).sethp(BattleList.get(index).gethp()+damage);
		}
		if(a.special==1)
		{
		    BattleList.get(index).sethp(BattleList.get(index).getmaxhp()/2);
		    BattleList.get(index).dead=0;
		}
	    }
	}
	else
	{
	   for(Character p:BattleList)
	   {
	       if(a.type==1)
	       {
		   damage=t.getpatk()*(multi)/100;
	       }
	       else if(a.type==2)
	       {
		   damage=t.getmatk()*(multi)/100;
	       }
	       else
	       {
		   damage=t.getmatk()*multi/100;
	       }
	       if(a.type!=3)
	       {
		   p.sethp(p.gethp()-damage);
		   if(p.gethp()<0)
		   {
		       if(p.getnation()==2)
		       {
			   enemyhp-=p.getmaxhp();
		       }
		       else
		       {
			   allyhp-=p.getmaxhp();
		       }
		       p.dead=1;
		   }
	       }
	       else
	       {
		   if(p.gethp()+damage>p.getmaxhp())
		   {
		       p.sethp(p.getmaxhp());
		   }
		   else
		   {
		       p.sethp(p.gethp()+damage);
		   }
	       }
	   }
	}
	
	if(allyhp<=0)
	{
	    result=-1;
	}
	else if(enemyhp<=0)
	{
	    result=1;
	}
    }
}

class petOrder implements Comparator<Object>
{
    public int compare(Object a, Object b)
    {
	  if(a instanceof Character)
	  {
	      if(b instanceof Character)
	      {
		  return ((Character) b).getagility()-((Character) a).getagility();
	      }
	      else
	      {
		  return 0;
	      }
	  }
	  else
	  {
	      return 0;
	  }
    }
}
