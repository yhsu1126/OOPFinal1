package game;

public class Block {
    float x, y, height, width;
    public Block(float px, float py, float pwidth, float pheight) {
	x = px;
	y = py;
	height = pheight;
	width = pwidth;
    }
    public boolean isHit(float posX, float posY) {
	return (posX >= x - width) && (posX <= x) && (posY >= y - height) && (posY <= y);
    }
}
