import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Pancakes {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		final int t = in.nextInt();
		
		for(int t1=0; t1<t; t1++){
			final int n = in.nextInt();
			long[] pancakes = new long[n];
			long sum=0l;
			
			for(int n1=0; n1<n; n1++){
				pancakes[n1] = in.nextInt();
			}
			
			for (int i = 0; i < pancakes.length - 2; i++) {
				for (int j = 0; j < pancakes.length - 2; j++) {
					int l=i;
					int r=pancakes.length - 1 - j;
				
					if (l+2 > r)
						continue;
					sum += solve(pancakes, l, r);
				}
			}
			System.out.format("Case #%d: %s\n",t1+1, sum % 1000000007);
		}
	}

	public static long solve(long[] pancakes, int l, int r) {
		int max = getMax(pancakes, l, r);
		long sum = 0l;

		sum += makeAsc(pancakes, max, l);
		sum += makeDesc(pancakes, max, r);

		return sum;
	}

	public static int getMax(long[] p, int l, int r) {
		int max = l;
		for (int i = l; i <= r; i++) {
			if (p[max] < p[i]) {
				max = i;
			}
		}
		return max;
	}

	public static long makeAsc(long[] v, int max, int l) {
		long sum = 0l;
		long numberOfPancakes = v[l];
		for (int i = l + 1; i < max; i++) {
			if (numberOfPancakes > v[i]) {
				sum += numberOfPancakes - v[i];
			} else {
				numberOfPancakes = v[i];
			}
		}
		return sum;
	}

	public static long makeDesc(long[] v, int max, int r) {
		long sum = 0l;
		long numberOfPancakes = v[r];
		for (int i = r - 1; i >= max; i--) {
			if (numberOfPancakes > v[i]) {
				sum += numberOfPancakes - v[i];
			} else {
				numberOfPancakes = v[i];
			}
		}
		return sum;
	}

}
