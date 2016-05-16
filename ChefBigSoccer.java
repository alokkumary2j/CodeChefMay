package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DogMap{
	Map<Integer,Integer> map;
	boolean isProcessed;
}
class ChefBigSoccer {
	static int N, M, S;// N:No of Dogs;M:No of pass strengths;S: Starting Dog
	static void findAns(int passNo, int passW[], int startDog, List<List<DogMap>> dp) {
		if (passNo >= M)
			return;
		if (startDog < 1 || startDog > N)
			return;

		if (dp.get(passNo).get(startDog).isProcessed ==true) {
			return;
		}
		int passWt = passW[passNo];
		int nextdog1=startDog + passWt;
		int nextdog2=startDog - passWt;
		DogMap dogMap=dp.get(passNo).get(startDog);
		dogMap.map=new HashMap<Integer, Integer>();
		Map<Integer,Integer> countMap=dogMap.map;
		if (passNo == M - 1) {
			if (nextdog1 <= N )
				countMap.put(nextdog1,1);
			if (nextdog2 >= 1 )
				countMap.put(nextdog2,1);
			dogMap.isProcessed=true;
			return;
		}
		findAns(passNo + 1, passW,nextdog1, dp);
		findAns(passNo + 1, passW,nextdog2, dp);
		Map<Integer,Integer> nextDog1Map=null;
		if(nextdog1 <= N)nextDog1Map=dp.get(passNo+1).get(nextdog1).map;
		Map<Integer,Integer> nextDog2Map=null;
		if (nextdog2 >= 1)nextDog2Map=dp.get(passNo+1).get(nextdog2).map;
		if (nextdog2 >= 1) {
			if (nextdog1 <= N) {
				Set<Integer> nextDog1keys=new HashSet<Integer>(nextDog1Map.keySet());
				Set<Integer> nextDog2keys=new HashSet<Integer>(nextDog2Map.keySet());
				for(int loop1 = 1; loop1 <= N; loop1++)
				{
					if(countMap.get(loop1)!=null){
						if(nextDog1keys.contains(loop1))
						{
							countMap.put(loop1,(countMap.get(loop1)+nextDog1Map.get(loop1))%1000000007);							
						}
						if(nextDog2keys.contains(loop1))
						{
							countMap.put(loop1,(countMap.get(loop1)+nextDog2Map.get(loop1))%1000000007);							
						}
					}
					else{
						if(nextDog1keys.contains(loop1))
						{
							countMap.put(loop1,nextDog1Map.get(loop1));							
						}
						if(nextDog2keys.contains(loop1))
						{
							if(countMap.get(loop1)!=null)
								countMap.put(loop1,(countMap.get(loop1)+nextDog2Map.get(loop1))%1000000007);
							else 
								countMap.put(loop1,nextDog2Map.get(loop1));
						}						
					}
				}
			} else {
				Set<Integer> nextDog2keys=nextDog2Map.keySet();
				for(int loop1:nextDog2keys){
					if(countMap.get(loop1)!=null)
						countMap.put(loop1,(countMap.get(loop1)+nextDog2Map.get(loop1))%1000000007);
					else
						countMap.put(loop1,nextDog2Map.get(loop1));
				}
			}
		} else {
			if (nextdog1 <= N) {
				Set<Integer> nextDog1keys=nextDog1Map.keySet();
				for(int loop1:nextDog1keys){
					if(countMap.get(loop1)!=null)
						countMap.put(loop1,(countMap.get(loop1)+nextDog1Map.get(loop1))%1000000007);
					else
						countMap.put(loop1, nextDog1Map.get(loop1));
				}
			}
		}
		dogMap.isProcessed=true;
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
				
				
				List<List<DogMap>> dp=new ArrayList<List<DogMap>>();
				for(int loop1=0;loop1<M;loop1++){
					dp.add(new ArrayList<DogMap>());
					for(int loop2=0;loop2<=N;loop2++){
						dp.get(loop1).add(new DogMap());
					}
				}
				//int dp[][][] = new int[M][N + 1][N + 1];//1st Entry for Pair(passNo,currentDog) is indicator
				//O(MN)

				findAns(0, passStrength, S, dp);
				
				//String ansStr = "" + (dp[0][S][1]%1000000007);
				Map<Integer,Integer> map=dp.get(0).get(S).map;
				Set<Integer> finalKeys=new HashSet<Integer>(map.keySet());
				String ansStr= "";
				if(finalKeys.contains(1))
				{
					ansStr+=(map.get(1)%1000000007);
				}
				else{
					ansStr+=0;
				}
				for (int loop1 = 2; loop1 <= N; loop1++) {
					if(finalKeys.contains(loop1))
					{
						ansStr += " " + (map.get(loop1)%1000000007);						
					}
					else{
						ansStr += " " + 0;
					}
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
