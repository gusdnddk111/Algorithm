//백준 알고리즘 4949번 문제
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while(!str.equals(".")){
            if(main.findPerfectParentheses(str)) System.out.println("yes");
            else System.out.println("no");

            str = sc.nextLine();
        }
    }

    public boolean findPerfectParentheses(String str){
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<str.length();i++){
            //여는 괄호가 나온다면 스택에 Push
            if(str.charAt(i)=='[' || str.charAt(i)=='('){
                stack.push(str.charAt(i));
            }
            //닫는 괄호가 나온다면 스택의 pop과 비교
            else if(str.charAt(i)==']'){
                if(stack.isEmpty() || stack.pop() != '['){
                    return false;
                }
            }else if(str.charAt(i)==')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
            }
        }

        //작업을 다 수행하고 스택이 깔끔하게 비어있다면 True 아니면 False
        if(stack.isEmpty()) return true;
        else return false;

    }
}
