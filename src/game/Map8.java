package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.ArrayList;
import java.util.Random;

public class Map8 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    boolean quit = false;
    int duration = 200;
    float actorPositionX = -202;
    float actorPositionY = -283;
    float shiftX = actorPositionX + 400 + 202;
    float shiftY = actorPositionY + 240 + 283;
    Block[] collision;
    Block transfer;
    Record boy,girl;
    Play battle;
    float walkDistance = 0, maxDistance;
	Random ran;
    
	public Map8(int state,Play tmp,Record b,Record g) {
		super();
		this.battle=tmp;
		boy=b;
		girl=g;
	    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
    	ran = new Random();
    	maxDistance = ran.nextInt(400) + 200;
    	map = new Image("res/graphic/map/scene2_BrokenVillege.png");
	Image [] walkUp = {new Image("res/actor/actor_up1.png"),
		new Image("res/actor/actor_up2.png"),
		new Image("res/actor/actor_up3.png")};
	Image [] walkDown = {new Image("res/actor/actor_down1.png"),
		new Image("res/actor/actor_down2.png"),
		new Image("res/actor/actor_down3.png")};
	Image [] walkLeft = {new Image("res/actor/actor_left1.png"),
		new Image("res/actor/actor_left2.png"),
		new Image("res/actor/actor_left3.png")};
	Image [] walkRight = {new Image("res/actor/actor_right1.png"),
		new Image("res/actor/actor_right2.png"),
		new Image("res/actor/actor_right3.png")};
	movingUp = new Animation(walkUp, duration, true);
	movingUp.setPingPong(true);
	movingDown = new Animation(walkDown, duration, true);
	movingDown.setPingPong(true);
	movingLeft = new Animation(walkLeft, duration, true);
	movingLeft.setPingPong(true);
	movingRight = new Animation(walkRight, duration, true);
	movingRight.setPingPong(true);
	actor = movingDown;
	
	//collision
	collision = new Block[11];
	collision[0] = new Block(32, 140, 142, 156);
	collision[1] = new Block(31, -184, 142, 156);
	collision[2] = new Block(-537, -52, 71, 78);
	collision[3] = new Block(-607, -112, 71, 78);
	collision[4] = new Block(-287, -114, 71, 78);
	collision[5] = new Block(-359, -370, 71, 78);
	collision[6] = new Block(-160, -434, 71, 78);
	collision[7] = new Block(32, -370, 71, 78);
	collision[8] = new Block(28, -49, 71, 78);
	collision[9] = new Block(162, -497, 71, 78);
	collision[10] = new Block(31, -497, 71, 78);
	
	//transfer
	transfer = new Block(-125, -102, 117, 120);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
	map.draw(actorPositionX, actorPositionY);
	actor.draw(shiftX, shiftY);
	//g.drawString("actor X: " + actorPositionX + "\nactor Y: " + actorPositionY, 400, 20);
    
	
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
	Input input = gc.getInput();
	float previousactorPositionX = actorPositionX;
	float previousactorPositionY = actorPositionY;
	
	if(walkDistance > maxDistance){
		walkDistance = 0;
		maxDistance = ran.nextInt(400) + 200;
		int i,amount,j;
		amount=this.ran.nextInt(5)+1;
		ArrayList<Character> a=new ArrayList<Character>();
		a.add(new MainHero(boy.getlvl()));
		a.add(new Mainheroine(girl.getlvl()));
		for(i=0;i<amount;i++)
		{
		    j=this.ran.nextInt(3);
		    if(j%3==0)
		    {
			a.add(new FireSpirit(5));
		    }
		    else if(j%3==1)
		    {
			a.add(new Skeleton(5));
		    }
		    else if(j%3==2)
		    {
			a.add(new Scorpion(5));
		    }
		}
		this.battle.startBattle(new Image("res/fightScene/scene2_BrokenVillege.png"), a, this.getID());
		sbg.enterState(12);
	}
	
	if(input.isKeyDown(Input.KEY_UP)) {
	    actor = movingUp;
	    actor.start();
	    if(actorPositionY < 240){
		actorPositionY += delta * .1f; 
	    walkDistance += delta * .1f;
	    }
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    if(actorPositionY > -695){
		actorPositionY -= delta * .1f; 
	    walkDistance += delta * .1f;
	    }
	    
	}
	
	if(input.isKeyDown(Input.KEY_LEFT)) {
	    actor = movingLeft;
	    actor.start();
	    if(actorPositionX < 399){
		actorPositionX += delta * .1f; 
	    walkDistance += delta * .1f;
	    }
	}
	
	if(input.isKeyDown(Input.KEY_RIGHT)) {
	    actor = movingRight;
	    actor.start();
	    if(actorPositionX > -916 || (actorPositionY < -145 && actorPositionY > -305)){
		actorPositionX -= delta * .1f; 
	    walkDistance += delta * .1f;
	    }
	}
	
	if( !input.isKeyDown(Input.KEY_UP) &&
			!input.isKeyDown(Input.KEY_DOWN) &&
			!input.isKeyDown(Input.KEY_LEFT) &&
			!input.isKeyDown(Input.KEY_RIGHT) ){
		actor.stop();
		actor.setCurrentFrame(1);
	}
	
	for(int i = 0; i < collision.length; i++) {
	    if(collision[i].isHit(actorPositionX - 13, actorPositionY - 15)) {
		actorPositionX = previousactorPositionX;
	    	actorPositionY = previousactorPositionY;
	    	walkDistance -= delta * .1f;
	    }
	}
	
	if(transfer.isHit(actorPositionX, actorPositionY)) {
	    actorPositionX = -202;
	    actorPositionY = -283;
	    actor = movingDown;
	    sbg.enterState(6);
	}
	
	if(actorPositionX < -918 && actorPositionY < -142 && actorPositionY > -302) {
	    actorPositionX = -900;
	    actor = movingLeft;
	    sbg.enterState(9);
	}
	
	if(battle.getexp()>0 && battle.getreturn()==this.getID())
	{
	    this.boy.exp-=battle.getexp();
	    this.girl.exp-=battle.getexp();
	    if(this.boy.exp<0)
	    {
		this.boy.levelup();
	    }
	    if(this.girl.exp<0)
	    {
		this.girl.levelup();
	    }
	    battle.exp=0;
	}
    }
    
    public int getID(){
	return 8;
    }
}
