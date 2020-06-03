import java.util.*;
import java.io.*;
import java.awt.*;

public class Auxilizar{


	public static void main(String[] args){
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int n = in.nextInt(); 
		int m = in.nextInt();
		int min = in.nextInt();
		int max = in.nextInt();
		int[] l = new int[m];
		
		for(int i=0; i<m; i++)
			l[i]=in.nextInt();
		
		System.out.println(getPossible(n,m,min,max,l));

	}

	public static String getPossible(int n, int m, int min, int max, int[] l){
		boolean minok=false;
		boolean maxok=false;
		for(int i=0; i<l.length; i++){
			
			if(min>l[i]) return "Incorrect";
			if(max<l[i]) return "Incorrect";
			
			if(min==l[i]) minok=true;
			if(max==l[i]) maxok=true;
			if(minok && maxok) return "Correct"; 
		}

		int diff = n-m;
		if(!minok) diff--;
		if(!maxok) diff--;
		
		return diff>=0 ? "Correct" : "Incorrect";
	}


}
