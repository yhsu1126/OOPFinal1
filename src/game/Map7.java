package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Map7 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    boolean quit = false;
    int duration = 200;
    float actorPositionX = -119;
    float actorPositionY = -438;
    float shiftX = actorPositionX + 400 + 119;
    float shiftY = actorPositionY + 240 + 438;
    Block[] collision;
    public Map7(int state) {
	
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	map = new Image("res/graphic/map/scene1_WeaponShop.png");
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
	/*collision = new Block[];
	collision[] = new Block();*/
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
	    if(actorPositionY < -186)
		actorPositionY += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    if(actorPositionY > -491 || (actorPositionX < 13 && actorPositionX > -210))
		actorPositionY -= delta * .1f; 
	    
	}
	
	if(input.isKeyDown(Input.KEY_LEFT)) {
	    actor = movingLeft;
	    actor.start();
	    if(actorPositionX < 335)
		actorPositionX += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_RIGHT)) {
	    actor = movingRight;
	    actor.start();
	    if(actorPositionX > -602)
		actorPositionX -= delta * .1f; 
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
	
	if(actorPositionX < 18 && actorPositionX > -216 && actorPositionY < -500) {
	    actorPositionX = -119;
	    actorPositionY = -438;
	    actor = movingUp;
	    sbg.enterState(3);
	}
	    
    }
    
    public int getID(){
	return 7;
    }
}
