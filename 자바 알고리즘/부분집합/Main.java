import java.util.*;

public class Main {

    int[] list = {0,1,2,3};
    ArrayList<HashSet<Integer>> powerSetList = new ArrayList<HashSet<Integer>>();

    public static void main(String[] args) {
        Main main = new Main();

        //공집합 추가
        main.powerSetList.add(new HashSet<Integer>());

        main.addElement(main.list);

        System.out.println("size: " + main.powerSetList.size());
        System.out.print("[");
        for(HashSet<Integer> set : main.powerSetList){
            System.out.print(set);
        }
        System.out.print("]");

    }

    public void addElement(int[] list){

        int count = powerSetList.size();

        for(int i=0;i<list.length;i++){
            for(int j=0;j<count;j++){
                //원소들을 하나씩 추가하여 부분집합을 누적
                //[]
                //[], [0] -> 0 추가
                //[], [0], [1], [0,1] -> 1 추가
                //[], [0], [1], [0,1], [2], [0,2], [1,2], [0,1,2] -> 2 추가
                // ...
                HashSet<Integer> tempSet = (HashSet<Integer>)powerSetList.get(j).clone();
                tempSet.add(list[i]);
                powerSetList.add(tempSet);
            }
        }
    }

}