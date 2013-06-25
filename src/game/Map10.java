package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.ArrayList;
import java.util.Random;

public class Map10 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    boolean quit = false;
    int duration = 200;
    float actorPositionX = 36;
    float actorPositionY = 108;
    float shiftX = actorPositionX + 400 - 36;
    float shiftY = actorPositionY + 240 - 108;
    Block[] collision;
    float walkDistance = 0, maxDistance;
    Random ran;
    Record boy,girl;
    Play battle;
    //transfer
    Block transfer;
    public Map10(int state,Play tmp,Record b,Record g) {
	super();
	this.battle=tmp;
	boy=b;
	girl=g;
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
    	ran = new Random();
    	maxDistance = ran.nextInt(400) + 200;
    	map = new Image("res/graphic/map/scene3_maze.png");
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
	actor = movingRight;
	
	//collision
	/*collision = new Block[];
	collision[] = new Block();*/
	
	//transfer
	transfer = new Block(257, 158, 127, 114);
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
		    j=this.ran.nextInt(5);
		    if(j%5==0)
		    {
			a.add(new FireSpirit(20));
		    }
		    else if(j%5==1)
		    {
			a.add(new Skeleton(20));
		    }
		    else if(j%5==2)
		    {
			a.add(new Scorpion(20));
		    }
		    else if(j%5==3)
		    {
			a.add(new Scorpion(20));
		    }
		    else if(j%5==4)
		    {
			a.add(new Scorpion(20));
		    }
		}
		this.battle.startBattle(new Image("res/fightScene/scene3.png"), a, this.getID());
		sbg.enterState(12);
	}
	
	if(input.isKeyDown(Input.KEY_UP)) {
	    actor = movingUp;
	    actor.start();
	    if(actorPositionY < 262){
		actorPositionY += delta * .1f;
	    walkDistance += delta * .1f;
	    }
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    if(actorPositionY > -40){
		actorPositionY -= delta * .1f; 
	    walkDistance += delta * .1f;
	    }
	    
	}
	
	if(input.isKeyDown(Input.KEY_LEFT)) {
	    actor = movingLeft;
	    actor.start();
	    if(actorPositionX < 400){
		actorPositionX += delta * .1f;
	    walkDistance += delta * .1f;
	    }
	}
	
	if(input.isKeyDown(Input.KEY_RIGHT)) {
	    actor = movingRight;
	    actor.start();
	    actorPositionX -= delta * .1f; 
	    walkDistance += delta * .1f;
	}
	
	if( !input.isKeyDown(Input.KEY_UP) &&
			!input.isKeyDown(Input.KEY_DOWN) &&
			!input.isKeyDown(Input.KEY_LEFT) &&
			!input.isKeyDown(Input.KEY_RIGHT) ){
		actor.stop();
		actor.setCurrentFrame(1);
	}
	
	/*for(int i = 0; i < collision.length; i++) {
	    if(collision[i].isHit(actorPositionX - 13, actorPositionY - 15)) {
		actorPositionX = previousactorPositionX;
	    	actorPositionY = previousactorPositionY;
	    }
	}*/
	
	if(transfer.isHit(actorPositionX, actorPositionY)) {
	    actorPositionX = 36;
	    actorPositionY = 108;
	    actor = movingRight;
	    sbg.enterState(9);
	}
	
	if(actorPositionX < -1646)
	    sbg.enterState(11);
    }
    
    public int getID(){
	return 10;
    }
}
