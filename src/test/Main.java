package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	int a;
	String b;

	public static void main(String[] args) {
			int [][] t = {{10,5,8},{2,1,7},{12,3,6}};
			int tmp = 0;
			for(int i=0;i<t.length;i++){
				boolean change ;
				do {
					change = false;
					for(int j=0;j<t.length-1;j++){
						
						if(t[i][j]<t[i][j+1]){
							change = true;
							tmp = t[i][j];
							t[i][j] = t[i][j+1];
							t[i][j+1]= tmp;
						}
					}	
				}while(change);
				
			}
			
			for(int i=0;i<t.length;i++){
				for(int j=0;j<t.length;j++){
					System.out.print(t[i][j]+"\t");
				}
				System.out.print("\n");
			}	
	}
}
