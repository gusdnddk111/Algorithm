import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {

    private static final int SCREEN_SIZE_X = 1000;
    private static final int SCREEN_SIZE_Y = 690;

    private static final int DOT_NUMBER = 150;
    private static final int DOT_RADIUS = 6;

    private static final int CLUSTER_NUM = 3;
    private static final int CLUSTER_1 = 1;
    private static final int CLUSTER_2 = 2;
    private static final int CLUSTER_3 = 3;
    private static final int CLUSTER_DRAW_LINE_LENGTH = 10;
    private static final int CLUSTER_DISTANCE_THRESHHOLD = 250;

    private ArrayList<Dot> randomDotList;
    private ArrayList<Dot> clusterDotList;
    ArrayList<Integer> countClusterKList = new ArrayList<Integer>();

    private int phase;
    private boolean complete;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings(){
        complete = false;
        randomDotList = new ArrayList<Dot>();
        clusterDotList = new ArrayList<Dot>();

        size(SCREEN_SIZE_X, SCREEN_SIZE_Y);

        phase=0;

        System.out.println("...점 위치 세팅 중...");
        for(int i=0;i<DOT_NUMBER;i++){
            int x = (int)((Math.random() * (SCREEN_SIZE_X-305))+15);
            int y = (int)((Math.random() * (SCREEN_SIZE_Y-105))+55);

            Dot dot = new Dot(x,y);
            randomDotList.add(dot);
        }
        System.out.println("...점 위치 세팅 완료...");

        //K개의 클러스터 초기 랜덤 생성
        //가까운 거리에 클러스터가 잡히지 않게 적정한 거리가 유지될때까지 랜덤 생성
        //클러스터간의 최소거리 : CLUSTER_DISTANCE_THRESHHOLD
        System.out.println("...클러스터 위치 세팅 중...");
        for(int i=0;i<CLUSTER_NUM;i++){
            countClusterKList.add(0);

            int x = (int)((Math.random() * (SCREEN_SIZE_X-500))+110);
            int y = (int)((Math.random() * (SCREEN_SIZE_Y-290))+150);

            Dot cluster = new Dot(x,y);
            cluster.setCluster(i+1);
            int j=i;
            while(j>0){
                if(calcDist(cluster,clusterDotList.get(i-j))<CLUSTER_DISTANCE_THRESHHOLD) {
                    x = (int)((Math.random() * (SCREEN_SIZE_X-500))+110);
                    y = (int)((Math.random() * (SCREEN_SIZE_Y-290))+150);

                    cluster.setX(x);
                    cluster.setY(y);
                    j=i;
                    System.out.println("클러스터 위치 조정 : 다른 클러스터와 너무 가까움");
                }else {
                    j--;
                }
            }
            clusterDotList.add(cluster);
            System.out.println("Cluster "+ (i+1) + ": (" + cluster.getX()+", "+cluster.getY() + ")");
        }
        System.out.println("...클러스터 위치 세팅 완료...");

    }

    public void setup(){
        background(255,255,255);
    }

    public void draw(){
        background(255,255,255);

        //n개의 점 그리기
        //Cluster별로 다른색으로 찍음
        //초기에는 Cluster가 존재하지 않아 다 검은색
        for(int i=0;i<DOT_NUMBER;i++){
            strokeWeight(0);
            switch(randomDotList.get(i).getCluster()){
                case CLUSTER_1:
                    fill(255,0,0);
                    break;
                case CLUSTER_2:
                    fill(0,255,0);
                    break;
                case CLUSTER_3:
                    fill(0,0,255);
                    break;
                default:
                    fill(0,0,0);
                    break;
            }

            ellipse(randomDotList.get(i).getX(),randomDotList.get(i).getY(),DOT_RADIUS,DOT_RADIUS);
        }

        //K개의 클러스터 그리기
        //초기 클러스터는 랜덤하게 설정
        //클러스터마다 색을 다르게 그림
        for(int i=0;i<CLUSTER_NUM;i++){
            strokeWeight(2);
            switch(clusterDotList.get(i).getCluster()){
                case CLUSTER_1:
                    stroke(255,0,0);
                    fill(255,0,0);
                    break;
                case CLUSTER_2:
                    stroke(0,255,0);
                    fill(0,255,0);
                    break;
                case CLUSTER_3:
                    stroke(0,0,255);
                    fill(0,0,255);
                    break;
                default:
                    stroke(0,0,0);
                    fill(0,0,0);
                    break;

            }

            line(clusterDotList.get(i).getX()-CLUSTER_DRAW_LINE_LENGTH,clusterDotList.get(i).getY()-CLUSTER_DRAW_LINE_LENGTH,clusterDotList.get(i).getX()+CLUSTER_DRAW_LINE_LENGTH,clusterDotList.get(i).getY()+CLUSTER_DRAW_LINE_LENGTH);
            line(clusterDotList.get(i).getX()-CLUSTER_DRAW_LINE_LENGTH,clusterDotList.get(i).getY()+CLUSTER_DRAW_LINE_LENGTH,clusterDotList.get(i).getX()+CLUSTER_DRAW_LINE_LENGTH,clusterDotList.get(i).getY()-CLUSTER_DRAW_LINE_LENGTH);
            textSize(12);
            text("K"+(i+1),clusterDotList.get(i).getX()-7, clusterDotList.get(i).getY()+25);
        }

        fill(0,0,0);
        textSize(16);

        text("Phase " + phase,10,30);
        if(complete)
            text("(Algorithm Complete)",100,30);
        //info.
        text("Dot Count : ", 730,70);
        text(DOT_NUMBER, 900,70);

        for(int i=0;i<clusterDotList.size();i++){
            text("Cluster " + i + " count : ", 730,110+(i*30));
            text(countClusterKList.get(i),900,110+(i*30));
            text("Cluster "+ i +"'s position : ", 730,120+(clusterDotList.size()*30)+(i*30));
            text("(" + clusterDotList.get(i).getX() + "," + clusterDotList.get(i).getY() + ")",900,120+(clusterDotList.size()*30)+(i*30));
        }

        //사각형 그리기
        //가로길이 : 700
        //세로길이 : 600
        stroke(0,0,0);
        line(10,50,10,650);
        line(10,50,710,50);
        line(710,650,710,50);
        line(710,650,10,650);
    }

    //두 점 사이의 거리를 계산
    private double calcDist(Dot dot1, Dot dot2){
        double distX = (double)(dot1.getX()-dot2.getX());
        double distY = (double)(dot1.getY()-dot2.getY());

        return Math.sqrt(Math.pow(distX,2) + Math.pow(distY,2));
    }

    //클러스터와 점 사이의 거리가 최소가 되는 클러스터가 무엇인지 계산
    private int minDistByCluster(Dot dot){
        double min = 100000;
        int minClusterNum = 0;

        for(int i=0;i<clusterDotList.size();i++){
            if(min>calcDist(dot, clusterDotList.get(i))){
                min = calcDist(dot, clusterDotList.get(i));
                minClusterNum = i+1;
            }
        }

        return minClusterNum;
    }

    public void mousePressed() {
        phase++;
        countClusterKList.clear();
        System.out.println("\n\n...Phase "+ phase + "...");

        ArrayList<Dot> avgClusterKList = new ArrayList<Dot>();
        ArrayList<Dot> sumClusterKList = new ArrayList<Dot>();

        for(int i=0;i<clusterDotList.size();i++){
            sumClusterKList.add(new Dot(0,0));
            countClusterKList.add(0);
        }

        for(Dot dot:randomDotList){
            int minClusterNum = minDistByCluster(dot);
            dot.setCluster(minClusterNum);
            countClusterKList.set(minClusterNum-1, countClusterKList.get(minClusterNum-1)+1);
        }

        for(int i=0;i<countClusterKList.size();i++){
            System.out.println("cluster " + (i+1) +"'s dot count : " + countClusterKList.get(i));
        }

        for(Dot dot:randomDotList){
            sumClusterKList.get(dot.getCluster()-1).setX(sumClusterKList.get(dot.getCluster()-1).getX()+dot.getX());
            sumClusterKList.get(dot.getCluster()-1).setY(sumClusterKList.get(dot.getCluster()-1).getY()+dot.getY());
        }

        for(int i=0;i<sumClusterKList.size();i++){
            avgClusterKList.add(new Dot(sumClusterKList.get(i).getX()/countClusterKList.get(i), sumClusterKList.get(i).getY()/countClusterKList.get(i)));
        }

        int completeCount=0;

        for(int i=0;i<clusterDotList.size();i++){
            System.out.println("cluster " + (i+1) + "좌표 이동 : (" + clusterDotList.get(i).getX() + ", " + clusterDotList.get(i).getY() + ") -> (" + avgClusterKList.get(i).getX() + ", " + avgClusterKList.get(i).getY() + ")");
            if(clusterDotList.get(i).getX() == avgClusterKList.get(i).getX() && clusterDotList.get(i).getY() == avgClusterKList.get(i).getY())
                completeCount++;

            clusterDotList.get(i).setX(avgClusterKList.get(i).getX());
            clusterDotList.get(i).setY(avgClusterKList.get(i).getY());
        }

        if(completeCount==CLUSTER_NUM){
            phase--;
            complete = true;
        }

    }
}
