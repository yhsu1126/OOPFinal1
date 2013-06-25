package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Map9 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    boolean quit = false;
    int duration = 200;
    float actorPositionX = -86;
    float actorPositionY = -396;
    float shiftX = actorPositionX + 400 + 86;
    float shiftY = actorPositionY + 240 + 396;
    Block[] collision;
    //transfer
    Block transfer;
    
    public Map9(int state) {
	
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	map = new Image("res/graphic/map/scene2_coFunderHome.png");
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
	collision = new Block[5];
	collision[0] = new Block(-231, -46, 148, 164);
	collision[1] = new Block(-233, 77, 77, 144);
	collision[2] = new Block(-393, 210, 138, 158);
	collision[3] = new Block(-12, 200, 141, 150);
	collision[4] = new Block(215, 79, 77, 156);
	
	//transfer
	transfer = new Block(3, -31, 128, 126);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
	map.draw(actorPositionX, actorPositionY);
	actor.draw(shiftX, shiftY);
	g.drawString("actor X: " + actorPositionX + "\nactor Y: " + actorPositionY, 400, 20);
    
	
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
	Input input = gc.getInput();
	float previousactorPositionX = actorPositionX;
	float previousactorPositionY = actorPositionY;
	
	if(input.isKeyDown(Input.KEY_UP)) {
	    actor = movingUp;
	    actor.start();
	    if(actorPositionY < 134)
		actorPositionY += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    if(actorPositionY > -430 || (actorPositionX < 15 && actorPositionX > -209))
		actorPositionY -= delta * .1f; 
	    
	}
	
	if(input.isKeyDown(Input.KEY_LEFT)) {
	    actor = movingLeft;
	    actor.start();
	    if(actorPositionX < 338)
		actorPositionX += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_RIGHT)) {
	    actor = movingRight;
	    actor.start();
	    if(actorPositionX > -534)
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
	
	if(actorPositionX < 19 && actorPositionX > -207 && actorPositionY < -445) {
	    actorPositionX = -86;
	    actorPositionY = -396;
	    actor = movingUp;
	    sbg.enterState(8);
	}
	
	if(transfer.isHit(actorPositionX, actorPositionY)) {
	    actorPositionX = -66;
	    actorPositionY = -217;
	    actor = movingDown;
	    sbg.enterState(10);
	}
    }
    
    public int getID(){
	return 9;
    }
}
