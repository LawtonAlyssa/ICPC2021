package edu.hpu.cs.tnguyen10.icpccontest;

import java.util.*;

public class tProblemR {

    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        
        int n = kb.nextInt();
        int[] k = new int[n];
        int result = 0;
        
        for (int i=0; i<n; i++)
        {
            k[i] = kb.nextInt();
            result += k[i];
        }
        
        Arrays.sort(k);
        
        for (int i = 0; i<n; i++)
        {
            System.out.println(k[i]);
        }
        
        
    }
    
}
