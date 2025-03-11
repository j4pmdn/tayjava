package statements;

public class App {
	
	public static void main(String[] args) {
		
		int age = 17;
		
		if(age < 18) {
			System.out.println("do something");
		} else { 
			System.out.println("do nothing");
		}
		
		String expression = "874";
		switch(expression){
		    case "123":
		        System.out.println("Bạn đã nhận được khoản tiền thưởng 100k");
		        break;
		    case "874":
		        System.out.println("Bạn đã nhận được khoản tiền thưởng 200k");
		        break;
		    case "692":
		        System.out.println("Bạn đã nhận được khoản tiền thưởng 500k");
		        break;
		    default:
		        System.out.println("Chúc bạn lần sau may mắn");
		}
		
	}
	
}
