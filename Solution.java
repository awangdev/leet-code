import java.util.*;
public class Solution {
    public static String reverseWords(String s) {
        if (s == null || s.length() == 0 || s.indexOf(" ") == -1) {
            return s;
        }
        s = s.trim();//no head && tail " "
        if (s.length() == 0) {
            return s;
        }

        StringBuffer sb = new StringBuffer();
        for (String str : s.split(" ")) {
            if (str.trim().length() != 0) {
                sb.append(str + " ");
            }
        }

        reverse(sb, 0, sb.length() - 1);
        int start = 1;
        int end = start + sb.substring(start).indexOf(" ");
        System.out.println(sb.toString());
        //Process all words separate by space
        while (end != -1 && start < end) {
            reverse(sb, start, end - 1);
            start = end + 1;
            end = start + (sb.substring(start)).indexOf(" ");
        }

        return sb.toString().trim();
    }

    //Reverse the contents of the string buffer
    public static void reverse(StringBuffer sb, int start, int end) {
        for (int i = start, j = end; i < j; i++,j--) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
    }



    public static void main(String[] args){
    	System.out.println("START");
    	
        String rst = reverseWords("How are you?");
    	System.out.println("END " + rst);
    }
}










