import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO 
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i=1;i<=t;i++) {

			int a = sc.nextInt();
			String[] robots = new String[a];

			for (int j=0;j<a;j++) {
				robots[j] = sc.next();
			}

			boolean[] used = new boolean[a];

			String ans = "";

			for(int l=0;;l++) {
				ArrayList<Integer> pRobots = new ArrayList<>();
				ArrayList<Integer> rRobots = new ArrayList<>();
				ArrayList<Integer> sRobots = new ArrayList<>();
				for (int j=0;j<a;j++) {
					if (!used[j]) {
						if (robots[j].charAt(l%robots[j].length())=='P') {
							pRobots.add(j);
						} else if (robots[j].charAt(l%robots[j].length())=='R') {
							rRobots.add(j);
						} else if (robots[j].charAt(l%robots[j].length())=='S') {
							sRobots.add(j);
						}
					}
				}
				if(pRobots.isEmpty() && rRobots.isEmpty() && sRobots.isEmpty()) {
					break;
				} else if (pRobots.isEmpty() && rRobots.isEmpty() && !sRobots.isEmpty()) {
					ans += "R";
					break;
				} else if (pRobots.isEmpty() && !rRobots.isEmpty() && sRobots.isEmpty()) {
					ans += "P";
					break;
				} else if (pRobots.isEmpty() && !rRobots.isEmpty() && !sRobots.isEmpty()) {
					ans += "R";
					for (Integer intg : sRobots) {
						used[intg] = true;
					}
				} else if (!pRobots.isEmpty() && rRobots.isEmpty() && sRobots.isEmpty()) {
					ans += "S";
					break;
				} else if (!pRobots.isEmpty() && rRobots.isEmpty() && !sRobots.isEmpty()) {
					ans += "S";
					for (Integer intg : pRobots) {
						used[intg] = true;
					}
				} else if (!pRobots.isEmpty() && !rRobots.isEmpty() && sRobots.isEmpty()) {
					ans += "P";
					for (Integer intg : rRobots) {
						used[intg] = true;
					}
				} else if (!pRobots.isEmpty() && !rRobots.isEmpty() && !sRobots.isEmpty()) {
					ans = "IMPOSSIBLE";
					break;
				}

			}
			System.out.println("Case #"+i+": "+ans);
		}
	}

}
