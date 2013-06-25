package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Map4 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    Image actorFace, headFace;
    boolean quit = false;
    boolean talk = false;
	int talkState = 0;
    int duration = 200;
    float actorPositionX = 319;
    float actorPositionY = -82;
    float shiftX = actorPositionX + 400 - 319;
    float shiftY = actorPositionY + 240 + 82;
    Block[] collision;
    Map3 key;
    public Map4(int state, Map3 mkey) {
    		key = mkey;
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	map = new Image("res/graphic/map/scene1_VillegeHeadHome.png");
	actorFace = new Image("res/graphic/people/actor/actor_face.png");
	headFace = new Image("res/graphic/people/villegeHead/villegeHead_face.png");
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
	collision = new Block[5];
	collision[0] = new Block(412, 271, 1000, 197);
	collision[1] = new Block(412, 271, 81, 271);
	collision[2] = new Block(412, -176, 73, 1000);
	collision[3] = new Block(412, -364, 1000, 1000);
	collision[4] = new Block(-342, 269, 1000, 1000);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
	map.draw(actorPositionX, actorPositionY);
	actor.draw(shiftX, shiftY);
	//g.drawString("actor X: " + actorPositionX + "\nactor Y: " + actorPositionY, 400, 20);
    
	if(talk == true){
		if(talkState == 0){
			actorFace.draw(10, 380);
			g.setColor(Color.white);
			g.fillRect(150, 380, 500, 100);
			g.setColor(Color.black);
			g.drawString("this is a sentence.", 160, 390);
		}
		else if(talkState == 1){
			headFace.draw(700, 380);
			g.setColor(Color.white);
			g.fillRect(150, 380, 500, 100);
			g.setColor(Color.black);
			g.drawString("this is anothor sentence.", 160, 390);
		}
		else if(talkState == 2){
			actorFace.draw(10, 380);
			g.setColor(Color.white);
			g.fillRect(150, 380, 500, 100);
			g.setColor(Color.black);
			g.drawString("this is a sentence.", 160, 390);
		}
		else if(talkState == 3){
			headFace.draw(700, 380);
			g.setColor(Color.white);
			g.fillRect(150, 380, 500, 100);
			g.setColor(Color.black);
			g.drawString("this is anothor sentence.", 160, 390);
		}
		else if(talkState == 4){
			talkState = 0;
			key.eventOPENR202 = true;
			talk = false;
		}
	}
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
	Input input = gc.getInput();
	float previousactorPositionX = actorPositionX;
	float previousactorPositionY = actorPositionY;
	
	if(talk == false && input.isKeyPressed(Input.KEY_ENTER)){
		if(actorPositionY < -95 && actorPositionY > -115 && actorPositionX < -85 && actorPositionX > -95){
			talk = true;
		}
	}
	else if(talk == true && input.isKeyPressed(Input.KEY_ENTER)){
		talkState++;
	}
	
	if(input.isKeyDown(Input.KEY_UP)) {
	    actor = movingUp;
	    actor.start();
	    
		actorPositionY += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    
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
	
	if(actorPositionX > 403 && actorPositionY < 15 && actorPositionY > -160) {
	    actor = movingRight;
	    actorPositionX = 319;
	    sbg.enterState(3);
	}
    }
    
    public int getID(){
	return 4;
    }
}
