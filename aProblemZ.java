import java.util.*;
public class aProblemZ {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        kb.nextLine();
        String d = kb.nextLine();
        int c = 1;
        for(int i=0; i<=n; i++){
            int d1;
            if(c>9){
                d1 = (int)(d.charAt(i)-'0')*10+(int)(d.charAt(i+1)-'0');
                i++;
            }
            else
                d1 = (int)(d.charAt(i)-'0');  
            if(d1!=c){
                System.out.println(c);
                System.exit(0);
            }
            c++; 
        }       
    }
}
