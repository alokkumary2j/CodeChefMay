package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
class Prime{
	int primes[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
	long facts[]={1L,2L,6L,24L,120L,720L,5040L,40320L,362880L,3628800L,39916800L,479001600L,227020758L,178290591L,674358851L,789741546L,425606191L,660911389L,557316307L,146326063L,72847302L,602640637L,860734560L,657629300L,440732388L,459042011L,394134213L,35757887L,36978716L,109361473L,390205642L,486580460L,57155068L,943272305L,14530444L,523095984L,354551275L,472948359L,444985875L,799434881L,776829897L,626855450L,954784168L,10503098L,472639410L,741412713L,846397273L,627068824L,726372166L,318608048L};
	int freq[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
	int multiFreqLastIndex=0;
	int primec=0;
	public Prime(int m){
		int c=0,val;
		for(int loop1=0;loop1<freq.length;loop1++)freq[loop1]=0;
		for(int loop1=0;primes[loop1]<=m;loop1++){
			c=0;
			val=primes[loop1];
			while(val<=m){
				c++;
				val*=primes[loop1];
			}
			freq[loop1]=c;
			primec++;
			if(c>1)multiFreqLastIndex=loop1;
		}
	}
}
class SerejaGCD {
	static long findFreqPerms(Prime prime,List<Integer>inc,int n,int currentIndex,int lastIndex){
		long ans=1,ansL;
		if(inc.size()==n){
			for(int index:inc){
				ans*=prime.freq[index];
			}
			return ans;
		}
		if(currentIndex>lastIndex || inc.size()>n)return 0;
		inc.add(currentIndex);
		ansL=findFreqPerms(prime, inc, n, currentIndex+1, lastIndex);
		inc.remove(inc.size()-1);
		return ansL+findFreqPerms(prime, inc, n, currentIndex+1, lastIndex);
	}
	static long freqTermsComb(Prime prime,int n){
		if(n==0)return 1L;
		if(prime.multiFreqLastIndex+1<n) return 0L;
		List<Integer>inc=new ArrayList<Integer>();
		return findFreqPerms(prime, inc, n, 0, prime.multiFreqLastIndex);
	}
	static long recurse(int m, int n, Prime prime) {
		//Case 1:  n>#Primes (Not Possible)
		if(n>prime.primec)System.exit(-1);
		long ans,ansL,ansR,finalAns=0L;
		int unifrequentPrimes=prime.primec-prime.multiFreqLastIndex-1,uniFreqItemC;
		for(int multiFeqItemC=0;multiFeqItemC<=n;multiFeqItemC++){
			ansL=freqTermsComb(prime, multiFeqItemC);
			uniFreqItemC=n-multiFeqItemC;
			//ansR=factRatio(unifrequentPrimes, unifrequentPrimes-multiFeqItemC);//npr=n!/(n-r)!
			//ncr Approach
			if(unifrequentPrimes>=uniFreqItemC){
				if(uniFreqItemC==0||uniFreqItemC==unifrequentPrimes){
					ansR=1;
				}
				else{
					//nCr=unifrequentPrimes_C_uniFreqItemC
					ansR=(prime.facts[unifrequentPrimes-1]/prime.facts[uniFreqItemC-1])/
							prime.facts[unifrequentPrimes-uniFreqItemC-1];
				}
			}
			else{
				ansR=0;
			}
			ans=ansL*ansR;
			finalAns+=ans;
		}
		return finalAns;
	}

	static long factRatio(long num, long denom) {
		long ans = 1L;
		for (long loop1 = denom + 1; loop1 <= num; loop1++) {
			ans *= loop1;
		}
		return ans;
	}

	static long f(int m, int n, Prime prime) {
		long answers = 0;
		long recurseResult;
		long combResult;
		int min1Reqd=n-prime.primec>0?n-prime.primec:0;
		for (int loop1 = min1Reqd; loop1 < n; loop1++) {
			if(loop1==n-1){
				recurseResult=m-1;
			}
			else{
				recurseResult = recurse(m, n - loop1,prime);// Loop1 Number of 1's being used				
			}
			System.out.println("RecureResult "+loop1+" 1's: "+recurseResult);
			if (recurseResult == 0)
				continue;
			combResult = factRatio(n, loop1);// n!/loop1!
			//combResult=combResult/prime.facts[n-loop1-1]; : Commmented for npr case
			recurseResult = recurseResult * combResult;
			answers += recurseResult;

		}
		answers += 1;// All 1's
		return answers;
	}

	public static void main(String[] args) {
		BufferedReader cnsl = null;
		String outputStr = "";
		try {
			cnsl = new BufferedReader(new InputStreamReader(System.in));
			int testCases = Integer.parseInt(cnsl.readLine());
			int N, M;
			long ans;
			for (int testCaseId = 0; testCaseId < testCases; testCaseId++) {
				String metadataStr = cnsl.readLine();
				String metadatas[] = metadataStr.split(" ");
				N = Integer.parseInt(metadatas[0]);// Array Size:N
				M = Integer.parseInt(metadatas[1]);// Max Size:M
				Prime prime=new Prime(M);
				ans = f(M, N, prime);
				if (testCaseId == 0)
					outputStr = "" + ans;
				else {
					outputStr = outputStr + "\n" + ans;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(outputStr);
	}
}
