package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Laddu {
	public static void main(String[] args) {
		//Console cnsl = null;
		BufferedReader cnsl =null;
		String ansStr=null;
		try {
			cnsl= new BufferedReader(new InputStreamReader(System.in));
			//cnsl = System.console();
			int testCases = Integer.parseInt(cnsl.readLine());
			for (int loop1 = 0; loop1 < testCases; loop1++) {
				String activitiesStr = cnsl.readLine();
				String acts[] = activitiesStr.split(" ");
				int activities = Integer.parseInt(acts[0]);
				int minRedeem = 400;
				if (acts[1].equals("INDIAN"))
					minRedeem = 200;
				int totLadoos = 0;
				for (int loop2 = 0; loop2 < activities; loop2++) {
					String act = cnsl.readLine();
					String actToks[] = act.split(" ");
					if (actToks[0].equals("TOP_CONTRIBUTOR")) {
						totLadoos += 300;
					} else if (actToks[0].equals("CONTEST_HOSTED")) {
						totLadoos+=50;
					}
					else if(actToks[0].equals("CONTEST_WON")){
						totLadoos+=(300+Math.max(0, 20-Integer.parseInt(actToks[1])));
					}
					else{
						totLadoos+=Integer.parseInt(actToks[1]);
					}
				}
				int ans=totLadoos/minRedeem;
				if(ansStr==null)ansStr=ans+"";
				else ansStr=ansStr+"\n"+ans;
			}
			System.out.println(ansStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}