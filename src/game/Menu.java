package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	private Image background;
	private Image start, load, quit;
	private Image startButton, startButtonMO, loadButton, loadButtonMO, quitButton, quitButtonMO;
	
	public Menu(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		background = new Image("res/background/matrix1.png");
		startButton = new Image("res/button/start.png");
		startButtonMO = new Image("res/button/startMO.png");
		loadButton = new Image("res/button/load.png");
		loadButtonMO = new Image("res/button/loadMO.png");
		quitButton = new Image("res/button/quit.png");
		quitButtonMO = new Image("res/button/quitMO.png");
		start = startButton;
		load = loadButton;
		quit = quitButton;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		background.draw(0, 0, gc.getWidth(), gc.getHeight());
		start.draw(50, 100);
		load.draw(50, 200);
		quit.draw(50, 300);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		int xpos = Mouse.getX(), ypos = Mouse.getY();
		
		//start
		if(xpos > 50 && xpos < 174 && ypos > 358 && ypos < 380){
			start = startButtonMO;
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				sbg.enterState(1);
			}
		}
		else{
			start = startButton;
		}
		
		//load
		if(xpos > 50 && xpos < 174 && ypos > 258 && ypos < 280){
			load = loadButtonMO;
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				System.exit(0);
			}
		}
		else{
			load = loadButton;
		}
		
		//quit
		if(xpos > 50 && xpos < 174 && ypos > 158 && ypos < 180){
			quit = quitButtonMO;
			if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
				System.exit(0);
			}
		}
		else{
			quit = quitButton;
		}
	}
	
	public int getID(){
		return 0;
	}
}
