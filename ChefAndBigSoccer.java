package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class ChefAndBigSoccer {
	static int N, M, S;// N:No of Dogs;M:No of pass strengths;S: Starting Dog
	static void findAns(int passNo, int passW[], int currentDog, int arr[][][]) {
		if (passNo >= M)
			return;
		if (currentDog < 1 || currentDog > N)
			return;

		if (arr[passNo][currentDog][0] != -1) {
			return;
		}
		if (passNo == M - 1) {
			int wt = currentDog + passW[passNo];
			if (wt <= N)
				arr[passNo][currentDog][wt] = 1;
			wt = currentDog - passW[passNo];
			if (wt >= 1)
				arr[passNo][currentDog][wt] = 1;
			arr[passNo][currentDog][0] = 1;
			return;
		}
		int passWt = passW[passNo];
		int nextdog1=currentDog + passWt;
		int nextdog2=currentDog - passWt;
		findAns(passNo + 1, passW,nextdog1 , arr);
		findAns(passNo + 1, passW, nextdog2, arr);
		if (nextdog2 >= 1) {
			if (nextdog1 <= N) {
				for (int loop1 = 1; loop1 <= N; loop1++)
				{
					arr[passNo][currentDog][loop1]+= arr[passNo + 1][nextdog1][loop1]+ 
							arr[passNo+1][nextdog2][loop1];
				}
			} else {
				for (int loop1 = 1; loop1 <= N; loop1++){
					arr[passNo][currentDog][loop1] += arr[passNo+1][nextdog2][loop1];
				}
			}
		} else {
			if (currentDog + passWt <= N) {
				for (int loop1 = 1; loop1 <= N; loop1++){
					arr[passNo][currentDog][loop1] += arr[passNo + 1][nextdog1][loop1];
				}
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
				N = Integer.parseInt(metadatas[0]);
				M = Integer.parseInt(metadatas[1]);
				S = Integer.parseInt(metadatas[2]);
				String passStrengthStr = cnsl.readLine();
				String passStrengthsToks[] = passStrengthStr.split(" ");
				int passW[] = new int[passStrengthsToks.length];
				for (int strId = 0; strId < passStrengthsToks.length; strId++)
				{
					passW[strId] = Integer.parseInt(passStrengthsToks[strId]);
				}
				int arr[][][] = new int[M][N + 1][N + 1];// 1st Entry for Pair(passNo,currentDog) is indicator
				for (int loop1 = 0; loop1 < M; loop1++) {
					for (int loop2 = 0; loop2 <= N; loop2++) {
						arr[loop1][loop2][0] = -1;
					}
				}

				findAns(0, passW, S, arr);
				String ansStr = "" + arr[0][S][1];
				for (int loop1 = 2; loop1 <= N; loop1++) {
					ansStr += " " + arr[0][S][loop1];
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
