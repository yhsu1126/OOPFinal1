package game;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Map1 extends BasicGameState{
    
	Random ran;
	float walkDistance = 0, maxDistance;
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    Play battle;
    boolean quit = false;
    int duration = 200;
    float actorPositionX = 131;
    float actorPositionY = -912;
    float shiftX = actorPositionX + 400 - 131;
    float shiftY = actorPositionY + 240 + 912;
    Record boy,girl;
    Block[] collision;
    public Map1(int state,Play tmp,Record b,Record g) {
	super();
	this.battle=tmp;
	boy=b;
	girl=g;
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	map = new Image("res/graphic/map/scene1_forest.png");
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
	actor = movingUp;
	ran = new Random();
	maxDistance = ran.nextInt(400) + 200;
	
	//collision
	collision = new Block[4];
	collision[0] = new Block(275, 187, 68, 67);
	collision[1] = new Block(144, 68, 68, 67);
	collision[2] = new Block(14, 126, 68, 67);
	collision[3] = new Block(84, -68, 68, 67);
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
		amount=this.ran.nextInt(3)+1;
		ArrayList<Character> a=new ArrayList<Character>(); 
		a.add(new MainHero(boy.getlvl()));
		for(i=0;i<amount;i++)
		{
		    j=this.ran.nextInt(3);
		    if(j%3==0)
		    {
			a.add(new Slime(1));
		    }
		    else if(j%3==1)
		    {
			a.add(new Zombie(1));
		    }
		    else if(j%3==2)
		    {
			a.add(new Willowisp(1));
		    }
		}
		this.battle.startBattle(new Image("res/fightScene/scene1_forest.png"), a, this.getID());
		sbg.enterState(12); // enter battle
	}
	
	if(input.isKeyDown(Input.KEY_UP)) {
	    actor = movingUp;
	    actor.start();
	    if(actorPositionY < 238.4){
		actorPositionY += delta * .1f; 
		walkDistance += delta * .1f;
	    }
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    if(actorPositionY > -965.7){
		actorPositionY -= delta * .1f; 
		walkDistance += delta * .1f;
	    }
	    
	}
	
	if(input.isKeyDown(Input.KEY_LEFT)) {
	    actor = movingLeft;
	    actor.start();
	    if(actorPositionX < 336.81){
		actorPositionX += delta * .1f; 
		walkDistance += delta * .1f;
	    }
	}
	
	if(input.isKeyDown(Input.KEY_RIGHT)) {
	    actor = movingRight;
	    actor.start();
	    if(actorPositionX > -82.39){
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
	
	// enter Map2 FuBell
	if(actorPositionY > 208) {
	    actorPositionY = 190;
	    actor = movingDown;
	    sbg.enterState(2);
	}
	if(battle.getexp()>0 && battle.getreturn()==this.getID())
	{
	    this.boy.setexp(this.boy.exp-battle.getexp());
	    if(this.boy.exp<0)
	    {
		this.boy.levelup();
	    }
	    battle.exp=0;
	}
    }
    
    public int getID(){
	return 1;
    }
}