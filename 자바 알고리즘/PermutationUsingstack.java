import java.util.Stack;

class PermutaionUsingStack {
    static int n = 9;
    static int cnt = 0;

    public static void main(String[] args) {

        Stack<Integer> targetSt = new Stack<Integer>();
        Stack<Integer> sourceSt = new Stack<Integer>();

        for(int i = 1 ; i<=n ; i++){
            sourceSt.push(i);
        }

        recur(0, targetSt, sourceSt);
        System.out.println("총 경우의 수: " + cnt);
    }

    public static void recur(int idx, Stack<Integer> targetSt, Stack<Integer> sourceSt){
        if (idx == n){
            cnt++;
            return;
        }

        for(int j = 0;j<n-idx;j++) {
            Stack<Integer> rememberSourceSt = (Stack<Integer>)sourceSt.clone();
            Stack<Integer> rememberTargetSt = (Stack<Integer>)targetSt.clone();
            Stack<Integer> tempSt = new Stack<Integer>();

            for(int k=0;k<j;k++) {
                tempSt.push(rememberSourceSt.pop());
            }

            rememberTargetSt.push(rememberSourceSt.pop());

            while(!rememberSourceSt.isEmpty()){
                tempSt.push(rememberSourceSt.pop());
            }

            recur(idx+1, (Stack<Integer>)rememberTargetSt.clone(), (Stack<Integer>)tempSt.clone());
        }


    }
}