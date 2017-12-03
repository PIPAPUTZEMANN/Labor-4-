
public class RationalNumber_Test {
	public static void main(String[] args) {
		RationalNumber test1 = new RationalNumber(15,0);
		System.out.println(test1);
		
		RationalNumber test2 = new RationalNumber("6/12");
		System.out.println(test2);
	
		RationalNumber test3 = new RationalNumber("6/1b2");
		System.out.println(test3);
	
		RationalNumber test4 = new RationalNumber("-6/12");
		System.out.println("t4 " + test4);
	
		RationalNumber test5 = new RationalNumber(6,-12);
		System.out.println("t5 " + test5);
		
		RationalNumber test6 = new RationalNumber(-666,-13);
		System.out.println("t6 " + test6);
		
		test1 = test4.sub(test2);
		System.out.println(test1);
	}
}
