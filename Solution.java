import java.util.*;
public class Solution {
	    public static ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> rst = new ArrayList<String>();
        if (digits == null || digits.length() == 0) {
            return rst;
        }
        ArrayList<String[]> map = new ArrayList<String[]>();
        map.add(new String[]{});
        map.add(new String[]{"a","b","c"});
        map.add(new String[]{"d","e","f"});
        map.add(new String[]{"g","h","i"});
        map.add(new String[]{"j","k","l"});
        map.add(new String[]{"m","n","o"});
        map.add(new String[]{"p","q","r","s"});
        map.add(new String[]{"t","u","v"});
        map.add(new String[]{"w","x","y","z"});




    }

    public static void main(String[] args){
    	System.out.println("START");
    	letterCombinations("2");
    	System.out.println("END ");
    }
}










