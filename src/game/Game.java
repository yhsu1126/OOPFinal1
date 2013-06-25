package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
   
   public static final String gamename = "please input";
   public static final int menu = 0;
   public static final int play = 1;
   public Game(String gamename){
      super(gamename);
      Play tmp=new Play(play);
      this.addState(new Menu(menu));
      this.addState(tmp);
   }
   
   public void initStatesList(GameContainer gc) throws SlickException{
      this.getState(menu).init(gc, this);
      this.getState(play).init(gc, this);
      this.enterState(menu);
   }
   
   public static void main(String[] args) {
      AppGameContainer appgc;
      try{
         appgc = new AppGameContainer(new Game(gamename));
         appgc.setDisplayMode(800, 480, false);
         appgc.start();
      }catch(SlickException e){
         e.printStackTrace();
      }
   }

}