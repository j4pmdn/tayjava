package loop;

public class Ex01For {
	//	số lần lặp cố định sử dụng vòng lặp for
	
	
	public static void main(String[] args) {
		
//		for (int i = 10; i > 0; i--) { // i++ = i + 1
//            System.out.println("So: " + i);
//        }
		
		
		// lặp chồng lặp (++)
		for (int i = 1; i <= 10; i++) {
			for(int j = 1; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	
		// Lặp chồng lặp (--)
        for (int i = 1; i <= 10; i++) {
            for (int j = 10; j >= i; j--) {
                System.out.print("* ");
            }
            System.out.println();
        }
	}
	
}
