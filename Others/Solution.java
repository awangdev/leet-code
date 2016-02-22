import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//check pallidromic by counting chars in HashMap. O(n). If more than 1 single char, that is no.
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int line = Integer.parseInt(in.nextLine());
        while (in.hasNextLine()) {
            if (checkPalindrome(in.nextLine())) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
    
    public static boolean checkPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        int countSingle = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            countSingle += entry.getValue() % 2;
            if (countSingle > 1) {
                return false;
            }
        }
        return true;
    }
    
    
}