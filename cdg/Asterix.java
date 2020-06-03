import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Asterix {

	public static final char BLANK = ' ';
	public static final char ASTERIX = '*';

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		
		for (int t1 = 0; t1 < t; t1++) {
			int n = in.nextInt();
			String[] in2 = new String[n];

			for (int n1 = 0; n1 < n; n1++) {
				in2[n1] = in.next();
			}

			String result = solve(in2);
			System.out.format("Case #%d: %s\n", t1 + 1, result);
		}
		in.close();
	}

	public static String solve(String[] in) {
		int n = in.length;
		
		String[] prefs = new String[n];
		StringBuilder mids = new StringBuilder();
		String[] sufs = new String[n];
		String maxPref = null;
		String maxSuf = null;
		
		for(int i=0; i<n; i++) {
			int firstPos = in[i].indexOf(ASTERIX);
			int lastPos = in[i].lastIndexOf(ASTERIX);
			
			if(0 < firstPos) 
				prefs[i] = in[i].substring(0,firstPos);
			else
				prefs[i] = "";
			
			if(lastPos+1<in[i].length())
				sufs[i] = in[i].substring(lastPos+1,in[i].length());
			else
				sufs[i] = "";
						
			if(firstPos < lastPos)
				mids.append(in[i].substring(firstPos + 1, lastPos).replace("*", ""));
			
			maxPref = maxPref!=null && (maxPref.length() > prefs[i].length()) ? maxPref : prefs[i] ;
			maxSuf = maxSuf!=null && (maxSuf.length() > sufs[i].length()) ? maxSuf : sufs[i] ;
		}

		for(int i=0; i<n; i++) {
			if(!maxPref.startsWith(prefs[i])) return "*";
			if(!maxSuf.endsWith(sufs[i])) return "*";
		}
		
		return maxPref+mids.toString()+maxSuf;
	}
}
