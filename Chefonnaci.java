package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Chefonnaci {
	static int ways(int sumReqd, int chefoncci[], int chefIndex, int k) {
		if (sumReqd < 0 || chefIndex >= chefoncci.length)
			return 0;
		if (k == 0) {
			if (sumReqd == 0)
				return 1;
			else
				return 0;
		}
		int ans = 0;
		int val=0;
		for (int loop1 = 0; loop1 <= k; loop1++) {
			if(val>1000000007) break;
			ans= (ans+ways(sumReqd -val, chefoncci,chefIndex + 1, k - loop1))%1000000007;
			val+=chefoncci[chefIndex];
		}
		return ans;
	}

	public static void main(String[] args) {
		int chefonnaci[] = new int[44];
		chefonnaci[0] = 1;
		chefonnaci[1] = 2;
		for (int loop1 = 2; loop1 < 44; loop1++) {
			chefonnaci[loop1] = chefonnaci[loop1 - 1] + chefonnaci[loop1 - 2];
		}

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
				int ans = 0;
				for (int loop2 = 0; loop2 <= K; loop2++) {
					ans= (ans+ways(X -loop2, chefonnaci, 1, K - loop2))%1000000007;//Caters for 0 to K 1s
				}
				if(ansStr==null){
					ansStr=""+ans;
				}
				else{
					ansStr=ansStr+"\n"+ans;
				}
			}
			System.out.println(ansStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
