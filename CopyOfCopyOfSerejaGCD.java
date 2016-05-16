package codechef.may;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
class Primes{
	int primes[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
	boolean exclude[]=new boolean[primes.length];
	int freq[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
	int multiFreqLastIndex=0;
	int primec=0;
	public Primes(int m){
		int c=0,val;
		for(int loop1=0;loop1<freq.length;loop1++){
			freq[loop1]=0;
			exclude[loop1]=false;
		}
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
class CopyOfCopyOfSerejaGCD {
	static int gcd(int u, int v) {
		int shift;
		if (u == 0)
			return v;
		if (v == 0)
			return u;
		for (shift = 0; ((u | v) & 1) == 0; ++shift) {
			u >>= 1;
			v >>= 1;
		}
		while ((u & 1) == 0)
			u >>= 1;
		do {
			while ((v & 1) == 0)
				v >>= 1;
			if (u > v) {
				int t = v;
				v = u;
				u = t;
			} // Swap u and v.
			v = v - u; // Here v >= u.
		} while (v != 0);
		return u << shift;
	}

	static int recurse(int m, int n, int i,Primes primes,int gcd[][]) {
		//dp[i][n]
		if (i > m)
			return 0;
		if (n > m - i + 1)
			return 0;
		if (n == 1) {
			int answers = 0;
			boolean shouldIncl = true;
			for (int loop1 = i; loop1 <= m; loop1++) {
				shouldIncl = true;
				for (int loop2 = 0; loop2 < primes.primec; loop2++) {
					int primeNo=primes.primes[loop2];
					if (primes.exclude[loop2]==true&&gcd[loop1][primeNo] != 1) {
						shouldIncl = false;
						break;
					}
				}
				if (shouldIncl)
					answers++;
			}
			return answers;
		}
		int answers = 0;
		boolean shouldAdd = true;
		for (int loop1 = 0; loop1 < excl.size(); loop1++) {
			if (gcd[i][excl.get(loop1)] != 1) {
				shouldAdd = false;
				break;
			}
		}
		if (shouldAdd) {
			int pos = excl.size();
			excl.add(i);
			answers = recurse(m, n - 1, i + 1, excl,gcd);
			excl.remove(pos);
		}
		answers += recurse(m, n, i + 1, excl,gcd);
		return answers;
	}

	static int factRatio(int num, int denom) {
		int ans = 1;
		for (int loop1 = denom + 1; loop1 <= num; loop1++) {
			ans *= loop1;
		}
		return ans;
	}

	static int f(int m, int n, int gcd[][]) {
		int answers = 0;
		int recurseResult;
		int combResult;
		Primes primes=new Primes(m);
		int min1Reqd=n-primes.primec>0?n-primes.primec:0;
		for (int loop1 = min1Reqd; loop1 < n; loop1++) {
			recurseResult = recurse(m, n - loop1, 2, primes,gcd);// Loop1 Number of 1's being used
//			System.out.println("RecureResult "+loop1+" 1's: "+recurseResult);
			if (recurseResult == 0)
				continue;
			combResult = factRatio(n, loop1);// n!/loop1!
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
			int ans;
			for (int testCaseId = 0; testCaseId < testCases; testCaseId++) {
				String metadataStr = cnsl.readLine();
				String metadatas[] = metadataStr.split(" ");
				N = Integer.parseInt(metadatas[0]);// Array Size:N
				M = Integer.parseInt(metadatas[1]);// Max Size:M
				int gcd[][]=new int[M+1][M+1];
				for(int loop1=1;loop1<=M;loop1++){
					for(int loop2=1;loop2<=M;loop2++){
						gcd[loop1][loop2]=gcd(loop1, loop2);
					}
				}
				List<Integer> excl = new ArrayList<Integer>();
				ans = f(M, N, gcd);
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
