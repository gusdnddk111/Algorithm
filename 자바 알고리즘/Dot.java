public class Dot {
    private int x;
    private int y;
    private int cluster;

    public Dot(int x, int y){
        this.x = x;
        this.y = y;
        this.cluster = 0;
    }

    public void setCluster(int cluster){
        this.cluster = cluster;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getCluster(){
        return cluster;
    }
}
