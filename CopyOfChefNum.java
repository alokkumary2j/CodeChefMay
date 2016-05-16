package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class CopyOfChefNum {

	public static long amazingness(int a[])
	{
		Map<Integer,Boolean> map=new HashMap<Integer, Boolean>();
	    int ans = 0;
	    for ( int i = 0; i < a.length; i++ ) {
	         int val = 0;
	         for ( int j = i; j < a.length; j++ ) {
	            val ^= a[j];  
	            if (!map.containsKey(val)) {
	                 ans= (ans+val)%1000000007;
	                 map.put(val, true);
	            }
	         }
	   }
	   return ans;
	}
	public static void main(String[] args) {
		BufferedReader cnsl =null;
		String ansStr=null;
		try {
			cnsl= new BufferedReader(new InputStreamReader(System.in));
			//cnsl = System.console();
			int testCases = Integer.parseInt(cnsl.readLine());
			for (int loop1 = 0; loop1 < testCases; loop1++) {
				long ans=0;
				String activitiesStr = cnsl.readLine();
				String acts[] = activitiesStr.split(" ");
				int L= Integer.parseInt(acts[0]),R=Integer.parseInt(acts[1]);
				long currAns;
				for(int loop2=L;loop2<=R;loop2++){
					String str=loop2+"";
					int a[]=new int[str.length()];
					for(int dig=0;dig<str.length();dig++)a[dig]=str.charAt(dig)-'0';
					currAns=amazingness(a);
					ans=(ans+currAns)%1000000007;
					System.out.println(loop2+" : "+currAns);
				}
				if(ansStr==null)ansStr=ans+"";
				else ansStr=ansStr+"\n"+ans;
			}
			System.out.println(ansStr);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
