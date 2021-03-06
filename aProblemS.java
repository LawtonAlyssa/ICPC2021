/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hpu.cs.tnguyen10.icpccontest;

import java.util.*;

public class aProblemS {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        kb.nextLine();
        String t;
        char[][] a = new char[n][k];
        for (int i = 0; i < n; i++) {
            t = kb.next();
            for (int j = 0; j < k; j++) {
                a[i][j] = t.charAt(j);
            }
        }

        boolean m;
        int c = 0;

        for (int j = 0; j < k; j++) {
            m = true;
            for (int i = 0; i < n; i++) {
                if (a[i][j] != a[0][j]) {
                    m = false;
                    break;
                }
            }
            
            if (m == true) c++;
               
        }
        System.out.println(c);
    }
}
