理解啥叫Edit。 就是删除，增加，和替换。
换完之后，理论上换成的String 就应该全等。
所以只要if statement进去以后，就结束了，总要出一个叫结局。
```
/*
Given two strings S and T, determine if they are both one edit distance apart.

Hide Company Tags Uber Facebook
Hide Tags String
Hide Similar Problems (H) Edit Distance

*/

/*
	Thoughts:
	One edit distance: delete, insert, and substitude.
	For loop. Whenever differenct, chech 3 cases and return

	Note: null cannot become "" or "a", so whenever s or t is null, return false;
*/

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
        	return false;
        } else if (Math.abs(s.length() - t.length()) > 1 || s.equals(t)) {
        	return false;
        }
        int leng = s.length() > t.length() ? t.length() : s.length();
        for (int i = 0; i < leng; i++) {
        	String ss = s;
        	String tt = t;
        	if (s.charAt(i) != t.charAt(i)) {
        		//Check delete
        		tt = t.substring(i + 1);
        		ss = s.substring(i);
        		if (tt.equals(ss)) {
        			return true;
        		}
        		//Check insert
        		tt = t.substring(i);
        		ss = s.substring(i + 1);
        		if (tt.equals(ss)) {
        			return true;
        		}
        		//check substitude
        		ss = s.substring(0, i) + s.substring(i + 1);
        		tt = t.substring(0, i) + t.substring(i + 1);
        		return ss.equals(tt);
        	}
        }//end for
        return true;
    }
}
```