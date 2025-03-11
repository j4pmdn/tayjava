package loop;

import java.util.ArrayList;
import java.util.List;

public class Ex02Foreach {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("H");
        
        // for (data_type variable : array_name){
           
        for (String s : list) {
        	if(s.equals("B"))
            System.out.println(s);
        }

	}
	
}
