package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Tree implements Comparable<Tree>{
	long ht;
	long incr;
	long minIter;
	public Tree(long _ht, long _incr, long L) {
		incr = _incr;
		long diff=L-_ht;
		if(diff<=0){
			minIter=0;
			ht=_ht;
		}
		else{
			minIter=diff/_incr;
			if(diff%_incr!=0){
				minIter++;
			}
			ht=_ht+(long)minIter*(long) _incr;
		}
	}
	@Override
	public int compareTo(Tree o) {
		if(this.minIter<o.minIter) return -1;
		else if(this.minIter==o.minIter) return 0;
		return 1;
	}
}
class ForestGathering {
	public static void main(String[] args) {
		BufferedReader cnsl = null;
		try {
			cnsl = new BufferedReader(new InputStreamReader(System.in));
			String ip[] = cnsl.readLine().split(" ");
			int N = Integer.parseInt(ip[0]);//N<=10^5
			long W = Long.parseLong(ip[1]), L = Long.parseLong(ip[2]);//W,L<=10^18
			//N:No of trees; W:Amount of Wood Required; L:Min Allowed Tree Height
			List<Tree> tree = new ArrayList<Tree>();
			Tree treeN = null;
			for (int loop1 = 0; loop1 < N; loop1++) {
				ip = cnsl.readLine().split(" ");
				treeN = new Tree(Long.parseLong(ip[0]),Long.parseLong(ip[1]), L);
				tree.add(treeN);
			}
			Collections.sort(tree);
			long minIters=0L;
			int treeIndex=0;
			long soFarBase=0L;
			long soFarRate=0L;
			long moreIters=0L;
			while (true) {
				minIters=tree.get(treeIndex).minIter;
				soFarBase=(long)soFarBase+(long)tree.get(treeIndex).ht;
				soFarRate=(long)soFarRate+(long)tree.get(treeIndex).incr;
				//Overflow
				if(soFarBase<=0){
					System.out.println(minIters);
					return;
				}
				while(treeIndex+1<tree.size()&&tree.get(treeIndex+1).minIter==minIters){
					treeIndex++;
					soFarBase=(long)soFarBase+(long)tree.get(treeIndex).ht;
					//Overflow
					if(soFarBase<=0){
						System.out.println(minIters);
						return;
					}
					soFarRate=(long)soFarRate+(long)tree.get(treeIndex).incr;					
				}
				if(soFarBase>=W||soFarBase<=0)//<=0 is for Handling Overflown fields
				{
					System.out.println(minIters);
					return;
				}
				long numer=(W-soFarBase);
				//if(numer<=0)System.exit(-1);
				long denom=soFarRate;
				moreIters=(long)((double)numer/(double)denom);
				if((long)numer%(long)denom!=0)moreIters++;
				
				//if(moreIters<=0)System.exit(-1);:Problematic Line
				
				
				if(treeIndex>=tree.size()-1){
					System.out.println((long)(minIters+moreIters));
					return;
				}
				treeIndex++;
				Tree nextTree=tree.get(treeIndex);
				long nextTimeSlot=nextTree.minIter;
				//if(minIters+moreIters==nextTimeSlot && L> 10000)System.exit(-1);
				if(minIters+moreIters<=nextTimeSlot){
					System.out.println((long)(minIters+moreIters));
//					if(soFarBase+soFarRate*moreIters<W){
//						System.exit(-1);
//					}
//					if(nextTimeSlot<minIters+moreIters)System.exit(-1);
					//if(soFarBase+soFarRate*(moreIters-1)>=W)System.exit(-1);
					//if(soFarBase+soFarRate*(moreIters-1)>=W)System.exit(-1);
					return;
				}
				else{
					soFarBase+=soFarRate*(nextTimeSlot-minIters);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}