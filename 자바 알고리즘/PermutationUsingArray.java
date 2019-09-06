class PermutationUsingArray {
    static int n = 9;
    static int cnt = 0;

    public static void main(String[] args) {

        int[] targetArr = new int[n];
        int[] numList = new int[n];

        recur(0, targetArr, numList);
        System.out.println("ÃÑ °æ¿ìÀÇ ¼ö: " + cnt);
    }

    public static void recur(int idx, int[] targetArr, int[] numList){
        if (idx == n){
            cnt++;
            return;
        }

        for(int i = 0;i<numList.length;i++){
            int[] rememberTargetArr = targetArr.clone();
            int[] rememberNumList = numList.clone();

            if(rememberNumList[i] != 1){
                rememberTargetArr[idx] = i+1;
                rememberNumList[i] = 1;

                recur(idx+1, rememberTargetArr.clone(), rememberNumList.clone());
            }
        }


    }
}