package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameA extends StateBasedGame{

	public static final String gamename = "OOP Final Project";
	public static final int menu = 0;
	public static final int map1 = 1;
	public static final int map2 = 2;
	public static final int map3 = 3;
	public static final int map4 = 4;
	public static final int map5 = 5;
	public static final int map6 = 6;
	public static final int map7 = 7;
	public static final int map8 = 8;
	public static final int map9 = 9;
	public static final int map10 = 10;
	public static final int map11 = 11;
	public static final int play = 12;
	
	public GameA() throws SlickException{
		super(gamename);
		Record boy=new Record(1),girl=new Record(1);
		Play tmp=new Play(play);
		this.addState(new Menu(menu));
		this.addState(new Map1(map1,tmp,boy,girl));
		this.addState(tmp);
		this.addState(new Map2(map2));
		Map3 mkey = new Map3(map3);
		this.addState(mkey);
		this.addState(new Map4(map4,mkey));
		this.addState(new Map5(map5));
		this.addState(new Map6(map6));
		this.addState(new Map7(map7));
		this.addState(new Map8(map8,tmp,boy,girl));
		this.addState(new Map9(map9));
		this.addState(new Map10(map10,tmp,boy,girl));
		this.addState(new Map11(map11));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(map1).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(map2).init(gc, this);
		this.getState(map3).init(gc, this);
		this.getState(map4).init(gc, this);
		this.getState(map5).init(gc, this);
		this.getState(map6).init(gc, this);
		this.getState(map7).init(gc, this);
		this.getState(map8).init(gc, this);
		this.getState(map9).init(gc, this);
		this.getState(map10).init(gc, this);
		this.getState(map11).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new GameA());
			appgc.setDisplayMode(800, 480, false);
			appgc.start();
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}

}
