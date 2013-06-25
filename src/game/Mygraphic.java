package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Mygraphic {
    private Image img;
	private int width;
	private int height;
	public Mygraphic(Image image) {
		img = image;
		width = img.getWidth();
		height = img.getHeight();
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void renderAtLocation(Graphics g,int x,int y) 
	{
		g.drawImage(img, x, y);
	}
}
