package codechef.may;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

class Tree{
	Long ht;
	Long incr;
	public Tree(Long _ht,Long _incr){
		ht=_ht;
		incr=_incr;
	}
}
class ForestGathering {

/*	public static void main(String[] args) {
		BufferedReader cnsl =null;
		try {
			cnsl= new BufferedReader(new InputStreamReader(System.in));
			String ip[] = cnsl.readLine().split(" ");
			long N=Long.parseLong(ip[0]),W=Long.parseLong(ip[1]),L=Long.parseLong(ip[2]);
			//N:No of trees;W:Amount of Wood Reqd;L:Min Allowed Tree Height
			List<Tree> tree=new ArrayList<Tree>();
			for(int loop1=0;loop1<N;loop1++){
				ip = cnsl.readLine().split(" ");
				tree.add(new Tree(Long.parseLong(ip[0]),Long.parseLong(ip[1])));
			}
			long months=0;
			while(true){
				Tree ctree=null;
				long reqdSum=0;
				for(int loop1=0;loop1<N;loop1++){
					ctree=tree.get(loop1);
					if(ctree.ht>=L)
					{
						reqdSum+=ctree.ht;
						if(reqdSum>=W)
						{
							System.out.println(months);
							return;
						}
					}
					ctree.ht+=ctree.incr;
				}
				months++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
*/
	public static void main(String[] args) {
		BufferedInputStream cnsl =null;
		
		try {
			cnsl= new BufferedInputStream(System.in);
			int bytesToRead=cnsl.available();
			byte[] data = new byte[(int)bytesToRead];
			cnsl.read(data, 0, bytesToRead);
			String str = new String(data, "UTF-8");
			data=null;
			String ipLines[] = str.split("\n");
			str=null;
			String ip[]=ipLines[0].split(" ");
			long N=Long.parseLong(ip[0]),W=Long.parseLong(ip[1]),L=Long.parseLong(ip[2]);
			//N:No of trees;W:Amount of Wood Reqd;L:Min Allowed Tree Height
			List<Tree> tree=new ArrayList<Tree>();
			for(int loop1=1;loop1<=N;loop1++){
				ip = ipLines[loop1].split(" ");
				tree.add(new Tree(Long.parseLong(ip[0]),Long.parseLong(ip[1])));
			}
			long months=0;
			while(true){
				Tree ctree=null;
				long reqdSum=0;
				for(int loop1=0;loop1<N;loop1++){
					ctree=tree.get(loop1);
					if(ctree.ht>=L)
					{
						reqdSum+=ctree.ht;
						if(reqdSum>=W)
						{
							System.out.println(months);
							return;
						}
					}
					ctree.ht+=ctree.incr;
				}
				months++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
