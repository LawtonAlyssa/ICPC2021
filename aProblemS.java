import java.util.*;
public class aProblemS{
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        String t;
        char[][] a = new char[n][k];
        for(int i = 0; i<n; i++){
            t = kb.next();
            for(int j = 0; j<k; j++){
                a[i][j] = t.charAt(j);
            }
        }
        boolean m = true;
        int c=0;
        System.out.println("hi");
        for(int j = 1; j<k; j++){
            System.out.println("hi");
            for(int i = 0; i<n; i++){
                char a0 = a[i][0];
                System.out.println(a[i][j]+" "+a0);
                if(a[i][j]!=a0){
                    m = false;
                    break;
                }
            }
            System.out.println(m);
            if(m)
                c++;
        }

        /*for(int i = 0; i<n; i++){
            char a0 = a[i][0];
            for(int j = 1; j<k; j++){
                if(a[i][j]!=a0){
                    m = false;
                    System.exit(0);
                }
                else
                    a0 = a[i][j];

            }

        }*/

        //System.out.println(a[0][0]==a[1][0]);
        System.out.println("HI");
    }
}