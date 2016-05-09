package codechef.may;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ChefAndBalls {

	public static void main(String[] args) {
		System.out.println(1);
		System.out.flush();
		System.out.println(3+" "+1+" "+2+" "+2);
		System.out.flush();
		System.out.println(3+" "+3+" "+3+" "+4);
		System.out.flush();
		BufferedReader cnsl=null;
		cnsl= new BufferedReader(new InputStreamReader(System.in));
		String op="";
		try {
			op=cnsl.readLine();
			int output=Integer.parseInt(op);
			if(output==0){
				System.out.println(2+"\n"+"5");
				System.out.flush();
			}
			else if(output==2){
				System.out.println(2+"\n"+"2");
				System.out.flush();
			}
			else if(output==1){
				System.out.println(2+"\n"+"1");
				System.out.flush();
			}
			else if(output==-1){
				System.out.println(2+"\n"+"4");
				System.out.flush();
			}
			else if(output==-2){
				System.out.println(2+"\n"+"3");
				System.out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}