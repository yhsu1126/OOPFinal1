package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Map11 extends BasicGameState{
    Animation actor, movingUp, movingDown, movingLeft, movingRight; 
    Image map;
    Image boss;
    boolean quit = false;
    boolean fight = false;
    int duration = 200;
    float actorPositionX = 341;
    float actorPositionY = -89;
    float bossPositionX = 1100;
    float bossPositionY = 240;
    float shiftX = actorPositionX + 400 - 341;
    float shiftY = actorPositionY + 240 + 89;
    Block[] collision;
    public Map11(int state) {
	
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
	map = new Image("res/graphic/map/scene3_FinalFight.png");
	boss = new Image("res/graphic/people/boss/boss_left2.png");
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
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
	map.draw(actorPositionX, actorPositionY);
	actor.draw(shiftX, shiftY);
	//g.drawString("actor X: " + actorPositionX + "\nactor Y: " + actorPositionY, 400, 20);
    boss.draw(bossPositionX,bossPositionY);
    if(fight == true){
    		g.setColor(Color.red);
    		g.fillRect(300, 100, 100, 50);
    		g.setColor(Color.black);
    		g.drawString("BOSS", 330, 115);
    }
	
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
	
	
	if(actorPositionX > -325 ){
	    actorPositionX -= delta * .1f; 
	    bossPositionX -= delta * .1f; 
	}
	else{
		actor.stop();
		fight = true;
	}
	
	
	
    }
    
    public int getID(){
	return 11;
    }
}

