package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Map3 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    boolean quit = false;
    int duration = 200;
    float actorPositionX = 51;
    float actorPositionY = -858;
    float shiftX = actorPositionX + 400 - 51;
    float shiftY = actorPositionY + 240 + 858;
    Block[] collision;
    
    //event
    boolean eventOPENR202 = false;
    
    public Map3(int state) {
	
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	map = new Image("res/graphic/map/scene1_villege.png");
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
	
	//collision
	collision = new Block[6];
	collision[0] = new Block(-59, -588, 1000, 1000);
	collision[1] = new Block(-59, -50, 1000, 432);
	collision[2] = new Block(412, 78, 63, 1000);
	collision[3] = new Block(214, 202, 62, 195);
	collision[4] = new Block(214, 269, 2000, 69);
	collision[5] = new Block(39, 214, 224, 154);
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
	
	if(input.isKeyDown(Input.KEY_UP)) {
	    actor = movingUp;
	    actor.start();
	    
		actorPositionY += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    if(actorPositionY > -920)
		actorPositionY -= delta * .1f; 
	    
	}
	
	if(input.isKeyDown(Input.KEY_LEFT)) {
	    actor = movingLeft;
	    actor.start();
	    
		actorPositionX += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_RIGHT)) {
	    actor = movingRight;
	    actor.start();
	    actorPositionX -= delta * .1f; 
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
	    }
	}
	
	if(actorPositionY < -900) {
	    actorPositionY = -858;
	    actor = movingUp;
	    sbg.enterState(2);
	}
	
	if(actorPositionX < 345 && actorPositionX > 227 && actorPositionY > 65) {
	    actorPositionY = 30;
	    actor = movingDown;
	    sbg.enterState(7);
	}
	
	if(actorPositionY < 214 && actorPositionY > -34 && actorPositionX < -910) {
	    actorPositionX = -880;
	    actor = movingLeft;
	    sbg.enterState(4);
	}
	
	if(actorPositionY < -467 && actorPositionY > -572 && actorPositionX < -127) {
	    if(eventOPENR202) {
		actorPositionX = -100;
		actor = movingLeft;
		sbg.enterState(5);
	    }
	    else
		actorPositionX = -127;
		
	}
    }
    
    public int getID(){
	return 3;
    }
}
