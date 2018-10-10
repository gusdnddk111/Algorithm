public class Main {

    public static void main(String[] args) {

        Main main = new Main();

        long start = System.currentTimeMillis();
        main.findPrimeNumber(50000);
        long end = System.currentTimeMillis();
        System.out.println( "실행 시/간 : " + ( end - start )/1000.0 );
    }

    public void findPrimeNumber(int n){

        int[] numList = new int[n];
        numList[0] = 1;
        numList[1] = 1;

        for (int i=2;i<Math.sqrt(n);i++){
            if(numList[i] != 1) {
                for (int j = i+i; j < n; j+=i) {
                    numList[j] = 1;
                }
            }
        }

        System.out.print("[");
        for(int i=0;i<n;i++){
            if(numList[i] != 1){
                System.out.print(i + ", ");
            }
        }
        System.out.println("]");
    }

}
