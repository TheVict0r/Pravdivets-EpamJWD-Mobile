package by.epamjwd.mobile.temporary;

import by.epamjwd.mobile.util.HashGenerator;



public class TestingMD5 {

	public static void main(String[] args) {
		String source = "Витя";
		
		HashGenerator hashGenerator = new HashGenerator();
		
		System.out.println(hashGenerator.generateHash(source));
	}

}
