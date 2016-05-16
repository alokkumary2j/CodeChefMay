package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class ChefMath {
	
	static List<Integer> findFibIndexes(int chefoncci[],int chefc,int reqdSum){
		List<Integer> fibs=new ArrayList<Integer>();
		int lastFibIndex=chefc-1;
		while(reqdSum>0){
			while(chefoncci[lastFibIndex]>reqdSum){
				lastFibIndex--;
			}
			reqdSum-=chefoncci[lastFibIndex];
			fibs.add(lastFibIndex);
		}
		return fibs;
	}
	static int answers(List<Integer> fibIndexes,int fibSumDP[][],int i,int extras){
		if(extras<0) return 0;
		if(extras==0){
			return fibIndexes.size()-i;
			}
		int ans=1,localans=1;
		for(int loop1=extras;loop1>=0;loop1--){
			localans=fibSumDP[fibIndexes.get(i)][loop1+1]*answers(fibIndexes,fibSumDP,i+1,extras-loop1);
			ans+=localans;
		}
		return ans;
	}
	static int countNoOfWays(int sumReqd, int chefonacci[], int fibSumDP[][],int chefIndex, int k){
		for(int loop1=0;loop1<k;loop1++){
			
		}
		return 0;
	}
	static int ways(int sumReqd, int chefoncci[], int fibSumDP[][],int chefIndex, int k) {
		if (sumReqd < 0 || chefIndex >= chefoncci.length)
			return 0;
		if (k == 0) {
			if (sumReqd == 0)
				return 1;
			else
				return 0;
		}
		int ans = 0;
		int val = 0;
		for (int loop1 = 0; loop1 <= k; loop1++) {
			if (val > sumReqd)
				break;
			ans = (ans + ways(sumReqd - val, chefoncci, fibSumDP,chefIndex + 1, k
					- loop1)) % 1000000007;
			val += chefoncci[chefIndex];
		}
		return ans;
	}

	public static int[][] buildFibonnaciDP(int fibc,int K){
		int fibDP[][]=new int[fibc+1][K+1];
		for(int loop1=0;loop1<=fibc;loop1++)fibDP[loop1][1]=1;
		fibDP[1][2]=1;//chefonnaci[1] i.e.2 can be represented as a sum of 2 fibonacci numbers(1+1)
		int ans;
		for(int loop1=2;loop1<=fibc;loop1++){
			for(int loop2=2;loop2<=K;loop2++){
				for(int loop3=loop2-1;loop3>=1;loop3--){
					ans=0;
					ans=fibDP[loop1-1][loop3]+fibDP[loop1-1][loop2-loop3];
					fibDP[loop1][loop2]+=ans;
				}
			}
		}
		return fibDP;
	}
	public static void main(String[] args) {
		BufferedReader cnsl = null;
		String ansStr = null;
		try {
			cnsl = new BufferedReader(new InputStreamReader(System.in));
			int questions = Integer.parseInt(cnsl.readLine());
			for (int loop1 = 0; loop1 < questions; loop1++) {
				String questionsStr = cnsl.readLine();
				String quests[] = questionsStr.split(" ");
				int X = Integer.parseInt(quests[0]);
				int K = Integer.parseInt(quests[1]);
				int chefonnaci[] = new int[44];
				chefonnaci[0] = 1;
				chefonnaci[1] = 2;
				int chefcCount=0;
				if(X==1)chefcCount=1;
				else chefcCount=2;
				for (int loop2 = 2; loop2 <= X; loop2++) {
					chefonnaci[loop2] = chefonnaci[loop2 - 1] + chefonnaci[loop2 - 2];
					chefcCount++;
				}
				int fibonacciSumDP[][]=buildFibonnaciDP(chefcCount, K);
				int ans = 0;
				for (int loop2 = 0; loop2 <= K; loop2++) {
					ans = (ans + ways(X - loop2, chefonnaci,fibonacciSumDP, 1, K - loop2)) % 1000000007;//Handles No of 1s
				}
				if (ansStr == null) {
					ansStr = "" + ans;
				} else {
					ansStr = ansStr + "\n" + ans;
				}
			}
			System.out.println(ansStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
