package codechef.may;

import java.util.HashMap;
import java.util.Map;

public class UnderstandDataSize {

	static Long pow(Long n){
		if(n==1)return n;
		return 10*pow(n-1);
	}
	static int gcd(int u, int v)
	{
		int shift;
		/* GCD(0,v) == v; GCD(u,0) == u, GCD(0,0) == 0 */
		if (u == 0) return v;
		if (v == 0) return u;
		 
	  /* Let shift := lg K, where K is the greatest power of 2
	        dividing both u and v. */
		  for (shift = 0; ((u | v) & 1) == 0; ++shift) {
		         u >>= 1;
		         v >>= 1;
		  }
		 
		  while ((u & 1) == 0)
		    u >>= 1;
		 
		  /* From here on, u is always odd. */
		  do {
		       /* remove all factors of 2 in v -- they are not common */
		       /*   note: v is not zero, so while will terminate */
		       while ((v & 1) == 0)  /* Loop X */
		           v >>= 1;

		       /* Now u and v are both odd. Swap if necessary so u <= v,
		          then set v = v - u (which is even). For bignums, the
		          swapping is just pointer movement, and the subtraction
		          can be done in-place. */
		       if (u > v) {
		         int t = v; v = u; u = t;}  // Swap u and v.
		       v = v - u;                       // Here v >= u.
		     } while (v != 0);

		  /* restore common factors of 2 */
		  return u << shift;
	}

	public static void testData(long _ht, long _incr, long L) {
		long ht;
		long minIter;
		long diff=L-_ht;
		if(diff<=0){
			minIter=0;
			ht=_ht;
			System.out.println("ALL OK "+ht);
		}
		else{
			minIter=diff/_incr;
			if(diff%_incr!=0){
				minIter++;
			}
			//minIter 		: 6923076923076923
			//diff    		: 90000000000000000
			//_ht	  		: 10000000000000000
			//L		  		: 100000000000000000   
			//minIter*_incr : 89999999999999999
			//_incr:13
			long mulAns=minIter*_incr;
			System.out.println(mulAns);
			ht=_ht+(long)minIter*(long) _incr;
			System.out.println("Updtaed Ht:"+ ht);
			if(ht<L)System.exit(-1);			
		}
	}
	static void testData2(long W,long soFarBase,long soFarRate){
		if(soFarBase>=W){
			System.out.println("Have Fun ....");
			return;
		}
		long moreIters=(long)(W-soFarBase)/(long)soFarRate;
		System.out.println("Iters " +moreIters);
		if(moreIters<0){
			System.exit(-1);
		}
	}
	public static void main(String[] args) {
		//testData(pow(17L),13,pow(18L));
		testData2(pow(17L),6,pow(18L));
		System.out.println((long)Math.ceil((double)(pow(18L)-1)/68676));

		System.out.println(pow(9L));
		System.out.println(pow(18L)+pow(18L));
		System.out.println(pow(18L)*6);
		int a=(int) Math.ceil(-42/20);
		
		int b=1000000008*2;
		System.out.println(b);
		
		Map<Integer,Integer> maps=new HashMap<Integer,Integer>();
		maps.put(3, 20);
		System.out.println(maps.get(3)+5);
		System.out.println(maps.get(45));
		int sz=1000000007*2;
		System.out.println(sz);
		int ans=gcd(1481,112);
		System.out.println("GCD : "+ans);
		int primes[]=new int[50];
		primes[0]=2;
		int primec=1;
		boolean shouldAdd=true;
		for(int loop1=3;loop1<100;loop1++){
			shouldAdd=true;
			for(int loop2=0;loop2<primec;loop2++){
				if(loop1%primes[loop2]==0){
					shouldAdd=false;
					break;
				}
			}
			if(shouldAdd){
				primes[primec]=loop1;
				primec++;
			}
		}
		System.out.println("Primes :"+primec);
//		for(int loop1=0;loop1<primec;loop1++){
//			System.out.println(primes[loop1]);
//		}
		long df=100000000000012L+78;
		long df1=20;
		df1+=df;
		System.out.println("<><><>FACT<><><><>");
		long fact[]=new long[50];
		fact[0]=1;
		for(int loop1=1;loop1<50;loop1++){
			fact[loop1]=(long)(fact[loop1-1]%1000000007)*(long)((loop1+1)%1000000007);
		}
		System.out.print("{");
		for(int loop1=0;loop1<50;loop1++){
			System.out.print((long)(fact[loop1]%1000000007)+"L,");
		}
		System.out.print("}");
		//System.out.println(df1);
		//System.out.println((long)Math.ceil((double)df/(double)5563));
		//System.out.println((long)Math.ceil((double)57657356753656357L-df/(double)876868668));
		System.out.println("\n\n\n\n\n\n\n\n\n");
	}

}
