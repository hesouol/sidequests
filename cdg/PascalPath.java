import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PascalPath {

	public static final int[][] pascal= new int[501][501];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		int t = in.nextInt();
		
		for (int t1 = 0; t1 < t; t1++) {
			int n = in.nextInt();
			
			System.out.format("Case #%d:\n", t1 + 1);
			getPath(new LinkedList<>(), new Ponto(1,1), n).stream().forEach(System.out::println);
		}
		in.close();
	}

	public static List<Ponto> getPath(List<Ponto> oldPath, Ponto pos, int sum){
		
		List<Ponto> path = new LinkedList<>();
		path.addAll(oldPath);
		
		if(sum==0) return path;
		if(path.size()==500) return null;
		if(path.contains(pos)) return null;
		
		int value = getValue(pos.r, pos.k);
		if(sum<value) return null;
		
		path.add(pos);

		for(Ponto p : getPossible(pos.r, pos.k)) {
			List<Ponto> recPath = getPath(path, p, sum-value);
			if(recPath!=null) return recPath;
		}
		
		return null;
	}
	
	
	public static int getValue(int r, int k) {
		
		if(pascal[r][k] != 0)
			return pascal[r][k];
		
		if(k==1 || k==r) {
			pascal[r][k] = 1;
			return 1;
		}

		pascal[r][k] = getValue(r-1,k-1) + getValue(r-1, k);
		return pascal[r][k];
	}
	
	
	public static List<Ponto> getPossible(int r, int k){

		List<Ponto> possible = new ArrayList<>();

		possible.add(new Ponto(r-1, k-1));
		possible.add(new Ponto(r-1, k));
		possible.add(new Ponto(r, k-1));
		possible.add(new Ponto(r, k+1));
		possible.add(new Ponto(r+1, k+1));
		possible.add(new Ponto(r+1, k));
		
		return possible.stream().filter(p -> p.k > 0 && p.r > 0 && p.k <= p.r).sorted(new ComparadorPonto()).collect(Collectors.toList());
	}
	
	
	//Ordena os pontos colocando os de maior valor na frente.
	public static class ComparadorPonto implements Comparator<Ponto>{

		@Override
		public int compare(Ponto arg0, Ponto arg1) {
			int v1 = getValue(arg0.r, arg0.k);
			int v2 = getValue(arg1.r, arg1.k);
			if(v1<v2) return 1;
			if(v2<v1) return -1;
			return 0;
		}
	}
	
	public static class Ponto{
		public int r;
		public int k;
		
		public Ponto(int r, int k){
			this.r= r;
			this.k= k;
		}

		@Override
		public String toString() {
			return r + " " + k;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Ponto other = (Ponto) obj;
			if (k != other.k)
				return false;
			if (r != other.r)
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + k;
			result = prime * result + r;
			return result;
		}
	}
	
}
