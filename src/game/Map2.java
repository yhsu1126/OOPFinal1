package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Map2 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image actress;
    Image map;
    Image actorFace, actressFace;
    Image menuQuit, menuResume, menuEquipment, menuMain;
    boolean quit = false;
    boolean talk = false;
    boolean afterTalk = false;
    int duration = 200;
    int talkState = 0;
    float actorPositionX = 67;
    float actorPositionY = -492;
    float actressPositionX = 400;
    float actressPositionY = -200;
    float shiftX = actorPositionX + 400 - 67;
    float shiftY = actorPositionY + 240 + 492;
    int mouseX, mouseY;
    Block[] collision;
    public Map2(int state) {
	
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	map = new Image("res/graphic/map/scene1_FuBell.png");
	actress = new Image("res/graphic/people/actress/actress_down2.png");
	menuQuit = new Image("res/button/QuitMenu.png");
	menuResume = new Image("res/button/Resume.png");
	menuEquipment = new Image("res/button/Equipment.png");
	menuMain = new Image("res/button/mainMenu.png");
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
	
	actorFace = new Image("res/graphic/people/actor/actor_face.png");
	actressFace = new Image("res/graphic/people/actress/actress_face.png");
	
	//collision
	collision = new Block[11];
	collision[0] = new Block(-36, -433, 1000, 1000);
	collision[1] = new Block(-176, -233, 1000, 1000);
	collision[2] = new Block(-239, 18, 1000, 1000);
	collision[3] = new Block(-178, 144, 1000, 263);
	collision[4] = new Block(-44, 256, 1000, 180);
	collision[5] = new Block(173, -434, -1000, 1000);
	collision[6] = new Block(275, -432, 134, 1000);
	collision[7] = new Block(339, -242, 78, 1000);
	collision[8] = new Block(399, 19, 69, 1000);
	collision[9] = new Block(337, 141, 57, 280);
	collision[10] = new Block(292, 286, 149, 238);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
	map.draw(actorPositionX, actorPositionY);
	actor.draw(shiftX, shiftY);
	if(afterTalk == false){
		actress.draw(actressPositionX, actressPositionY);
	}
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
			actressFace.draw(700, 380);
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
			actressFace.draw(700, 380);
			g.setColor(Color.white);
			g.fillRect(150, 380, 500, 100);
			g.setColor(Color.black);
			g.drawString("this is anothor sentence.", 160, 390);
		}
		else if(talkState == 4){
			talkState = 0;
			talk = false;
			afterTalk = true;
		}
	}
	
	if(quit == true){
		g.drawImage(menuResume, 300, 100);
		g.drawImage(menuEquipment, 300, 150);
		g.drawImage(menuMain, 300, 200);
		g.drawImage(menuQuit, 300, 250);
		if(quit == false){
			g.clear();
		}
	}
	
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
	Input input = gc.getInput();
	float previousactorPositionX = actorPositionX;
	float previousactorPositionY = actorPositionY;
	
	if(talk == false && input.isKeyPressed(Input.KEY_ENTER)){
		if(actorPositionY < -85 && actorPositionY > -100 && actorPositionX > 50 && actorPositionX < 80){
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
		actressPositionY += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_DOWN)) {
	    actor = movingDown;
	    actor.start();
	    
		actorPositionY -= delta * .1f; 
		actressPositionY -= delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_LEFT)) {
	    actor = movingLeft;
	    actor.start();
	    
		actorPositionX += delta * .1f; 
		actressPositionX += delta * .1f; 
	}
	
	if(input.isKeyDown(Input.KEY_RIGHT)) {
	    actor = movingRight;
	    actor.start();
	    
	    actorPositionX -= delta * .1f;
	    actressPositionX -= delta * .1f;
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
	// enter map1
	if(actorPositionY < -590) {
	    actorPositionY = -492;
	    actor = movingUp;
	    sbg.enterState(1);
	}
	//enter map3
	if(actorPositionY > 231) {
	    actorPositionY = 210;
	    actor = movingDown;
	    sbg.enterState(3);
	}
	
	mouseX = Mouse.getX();
	mouseY = Mouse.getY();
	//call menu
			if(input.isKeyDown(Input.KEY_ESCAPE)){
				quit = true;
			}
			if(quit == true){
				if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
					if(mouseX > 300 && mouseX < 500 && mouseY > 330 && mouseY < 380){
						quit = false;
					}
					if(mouseX > 300 && mouseX < 500 && mouseY > 280 && mouseY < 330){
						quit = false;
					}
					if(mouseX > 300 && mouseX < 500 && mouseY > 230 && mouseY < 280){
						quit = false;
						sbg.enterState(0);
					}
					if(mouseX > 300 && mouseX < 500 && mouseY > 180 && mouseY < 230){
						quit = false;
						System.exit(0);
					}
					
				}
			}
	
    }
    
    public int getID(){
	return 2;
    }
}
