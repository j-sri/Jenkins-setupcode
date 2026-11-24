package Playwright_Java.Java_playwright;

public class jsonParsing {

	public static void main(String[] args) {
		String s = "my name is Srividya upelli";
		
		for(int i=s.length()-1;i>=0;i--) {
			char c= s.charAt(i);
			if(c==' ') {
				System.out.print("*");
			}
			else 
				if(Character.isLowerCase(c)&&c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {
				
				System.out.print(String.valueOf(s.charAt(i)).toUpperCase());
			}
			else {
				System.out.print(c);
			}
			
		}
		
	}
	
}
