M
1522215963
tags: String

如果S, T只用一个operation就能变成相等, return true.

#### Edit: 删除，增加，和替换
- 换完之后，理论上换成的String 就应该全等
- for loop, 一旦找到不一样的char, 就判断那三种可能性
- insert/delete 对于2个string来说, 效果是类似的
- O(n)

```
/*
Given two strings S and T, determine if they are both one edit distance apart.

*/

/*
Thoughts:
Insert/Delete/Replace.
Insert/Delete have similar effect regarding the 2 strings.
Loop over s and t. When there is diff, apply one of the operations, compare && return
*/
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || s.equals(t) || Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        int size = s.length() > t.length() ? t.length() : s.length();
        for (int i = 0; i < size; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                // insert/delete 
                if (s.substring(i + 1).equals(t.substring(i)) || s.substring(i).equals(t.substring(i + 1))) {
                    return true;
                }
                // check replace
                return s.substring(i + 1).equals(t.substring(i + 1));
            }
        }
        // size == 0, and length diff == 1
        return true;
    }
}

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