package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class ChefAndBigSoccer {
	static int N, M, S;// N:No of Dogs;M:No of pass strengths;S: Starting Dog
	static void findAns(int passNo, int passW[], int startDog, int dp[][]) {
		if (passNo >= M)
			return;
		if (startDog < 1 || startDog > N)
			return;

		if (dp[passNo][startDog] != -1) {
			return;
		}
		int passWt = passW[passNo];
		int nextdog1=startDog + passWt;
		int nextdog2=startDog - passWt;
		if (passNo == M - 1) {
			if(dp[passNo][startDog]==-1)dp[passNo][startDog]=0;
			if (nextdog1 == S){
				dp[passNo][startDog]=(dp[passNo][startDog]+1)%1000000007;
			}
			if (nextdog2 == S){
				dp[passNo][startDog]=(dp[passNo][startDog]+1)%1000000007;
			}
			return;
		}
		findAns(passNo + 1, passW,nextdog1, dp);
		findAns(passNo + 1, passW,nextdog2, dp);
		if(dp[passNo][startDog]==-1)dp[passNo][startDog]=0;
		if (nextdog2 >= 1) {
			if (nextdog1 <= N) {
				dp[passNo][startDog]=(dp[passNo][startDog]+dp[passNo + 1][nextdog1])%1000000007;
				dp[passNo][startDog]=(dp[passNo][startDog]+ dp[passNo+1][nextdog2])%1000000007;
			} else {
				dp[passNo][startDog]= (dp[passNo][startDog]+dp[passNo+1][nextdog2])%1000000007;
			}
		} else {
			if (nextdog1 <= N) {
				dp[passNo][startDog]= (dp[passNo][startDog]+dp[passNo + 1][nextdog1])%1000000007;
			}
		}
	}

	public static void main(String[] args) {
		BufferedReader cnsl = null;
		String outputStr="";
		try {
			cnsl = new BufferedReader(new InputStreamReader(System.in));
			int testCases = Integer.parseInt(cnsl.readLine());
			for (int testCaseId = 0; testCaseId < testCases; testCaseId++) {
				String metadataStr = cnsl.readLine();
				String metadatas[] = metadataStr.split(" ");
				N = Integer.parseInt(metadatas[0]);//No of Dogs
				M = Integer.parseInt(metadatas[1]);//No of passes
				S = Integer.parseInt(metadatas[2]);//Starting Dog
				String passStrengthStr = cnsl.readLine();
				String passStrengthsToks[] = passStrengthStr.split(" ");
				int passStrength[] = new int[passStrengthsToks.length];
				for (int strId = 0; strId < passStrengthsToks.length; strId++)
				{
					passStrength[strId] = Integer.parseInt(passStrengthsToks[strId]);
				}
				int dp[][] = new int[M][N + 1];//1st Entry for Pair(passNo,currentDog) is indicator
				//O(MN)
				for (int loop1 = 0; loop1 < M; loop1++) {
					for (int loop2 = 0; loop2 <= N; loop2++) {
						dp[loop1][loop2] = -1;//Indicator Entry for DP Results
					}
				}
				int i=0,j=passStrength.length-1,temp;//Swap PassStrength Array
				while(i<j){
					temp=passStrength[i];
					passStrength[i]=passStrength[j];
					passStrength[j]=temp;
					i++;
					j--;
				}
				
				for(int loop1=1;loop1<=N;loop1++){
					findAns(0,passStrength,loop1,dp);
				}
				String ansStr = "" + (dp[0][1]%1000000007);
				for (int loop1 = 2; loop1 <= N; loop1++) {
					ansStr += " " + (dp[0][loop1]%1000000007);
				}
				if(outputStr=="")outputStr=ansStr;
				else outputStr+="\n"+ansStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(outputStr);
	}
}
