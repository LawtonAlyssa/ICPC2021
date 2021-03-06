import java.util.*;
public class aProblemX {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String s = kb.nextLine();
        int s0 = 1;
        int t = 0;
        for(int i=0;i<s.length();i++){
            int s1 = s.charAt(i)-'0';
            t+=(Math.abs(s1-s0));
            if(Math.abs(s1-s0)==0)
                t++;
            s0=s1;
        }
        System.out.println(t);
    }
    
}
