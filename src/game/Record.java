package game;

public class Record {
    int level,exp;
    public Record(int i)
    {
	level=i;
	exp=i*10;
    }
    public void setexp(int a)
    {
	this.exp=a;
    }
    public void levelup()
    {
	level++;
	exp=level*10;
    }
    public int getlvl()
    {
	return this.level;
    }
}
