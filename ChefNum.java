package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class IndexNode{
	int xorSofar;
	long ansSofar;
	static Map<Integer,Boolean> globalSet;
	IndexNode indexes[];
	public IndexNode(){
		indexes=new IndexNode[10];
	}
	public void addChildIndex(int index){
		IndexNode indexnode=new IndexNode();
		indexnode.xorSofar=(xorSofar^index)%1000000007;
		indexes[index]=indexnode;
		if(!globalSet.containsKey(indexnode.xorSofar))
		{
			globalSet.put(indexnode.xorSofar, true);
			indexnode.ansSofar=indexnode.xorSofar+ansSofar;
		}
	}
	public void checkAndFreeChildIndex(int currentIndex){
		if(currentIndex>0&&indexes[currentIndex-1]!=null){
			globalSet.remove(indexes[currentIndex-1].xorSofar);
		}
	}
}
class ChefNum {

	public static long amazingness(int a[],IndexNode indexNode)
	{
		int loop1=0;
		int matchedIndex=-1;;
		IndexNode currentIndexTemp=indexNode;
		while(loop1<a.length&&currentIndexTemp.indexes[a[loop1]]!=null){
			currentIndexTemp=currentIndexTemp.indexes[a[loop1]];
			loop1++;
		}
		matchedIndex=loop1-1;
		currentIndexTemp.checkAndFreeChildIndex(loop1-1);
		if(matchedIndex>=0&&matchedIndex==a.length-2){
			long newXorVal=currentIndexTemp.xorSofar^a[a.length-1];
			long newAnsSofar=currentIndexTemp.ansSofar;
			if(!IndexNode.globalSet.containsKey(newXorVal)){
				newAnsSofar=(newAnsSofar+newXorVal)%1000000007;
			}
			IndexNode tempCurrentIndex=indexNode;
			long tempAnsSoFar=0;
			Map<Integer,Boolean> localSet=new HashMap<Integer, Boolean>();
			for(loop1=0;loop1<=matchedIndex;loop1++){
				newXorVal=newXorVal^a[loop1];
				tempCurrentIndex=tempCurrentIndex.indexes[a[loop1]];
				if(!IndexNode.globalSet.containsKey(newXorVal)&&!localSet.containsKey(newXorVal)){
					tempAnsSoFar+=(newAnsSofar-tempCurrentIndex.ansSofar)%1000000007;
					localSet.put((int)newXorVal, true);
				}
			}
			return (newAnsSofar+tempAnsSoFar)%1000000007;
		}
		
		long noncachedAns=0,tempXorSofar,tempAnsSofar=0;
		IndexNode tempStartNode;
		Map<Integer,Boolean> localSet=new HashMap<Integer, Boolean>();
		for(int i=matchedIndex+1;i<a.length;i++){
			currentIndexTemp.addChildIndex(a[i]);//Updates xorSofar,valSofar,Set s entries
			currentIndexTemp=currentIndexTemp.indexes[a[i]];//xor(1,5,7)
			//Till Above point we prepared and cached Data :Below This Line :Only Processing
			tempXorSofar=currentIndexTemp.xorSofar;
			tempStartNode=indexNode;
			for(loop1=0;loop1<i;loop1++)
			{
				tempXorSofar^=a[loop1];
				tempStartNode=tempStartNode.indexes[a[loop1]];
				if(!IndexNode.globalSet.containsKey(tempXorSofar)&&!localSet.containsKey(tempXorSofar)){
					noncachedAns+=(tempAnsSofar+currentIndexTemp.ansSofar-tempStartNode.ansSofar)%1000000007;
					localSet.put((int)tempXorSofar, true);
				}
			}
		}
		return noncachedAns+currentIndexTemp.ansSofar;

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
				IndexNode indexRoot=new IndexNode();
				IndexNode.globalSet=new HashMap<Integer, Boolean>();
				for(int loop2=L;loop2<=R;loop2++){
					String str=loop2+"";
					int a[]=new int[str.length()];
					for(int dig=0;dig<str.length();dig++)a[dig]=str.charAt(dig)-'0';
					ans=(ans+amazingness(a,indexRoot))%1000000007;
					System.out.println(loop2+" : "+ans);
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
