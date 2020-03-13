E
tags: Two Pointers, String

验证string是不是 palindrome. 只考虑 alphanumeric, 其他字符可以忽略

#### Two Pointers
- Time O(n), Space O(1).
- 普通方法, 两边check, 速度相比较regular expression更快. leetcode 4ms.
- Use helper functions.

#### Check Palindrome
- 前后两个指针, 往中间移动, 查看是否字母重合

#### 过滤 alphanumeric
- 可以用 ASCII code 来手动过滤, 只要 '0' ~ '9', 'a' ~ 'z', 'A' - 'Z' 之间的
- 也可以用 regular expression: match 所有这些字母, 是 [a-zA-Z0-9]
- 那凡是不是这些字母的 match, 就是取反: "[^a-zA-Z0-9]". 测试: https://regex101.com/

```
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false

*/

/*
Two pointer: keep start++, end-- until valid char found.
Use helper function.
Runtime O(n), Space O(1)
*/

public class Solution {
    public boolean isPalindrome(String s) {
    	if (s == null || s.length() == 0) {
    		return true;
    	}
        int length = s.length();
    	int start = 0, end = length - 1;
    	while (start < end) {
    		while (start < length && !isValid(s.charAt(start))) start++;
    		while (end >= 0 && !isValid(s.charAt(end))) end--;
    		if (start < end && !compare(s.charAt(start), s.charAt(end))) return false;
    		start++;
    		end--;
    	}
    	return true;
    }

    private boolean compare(char m, char n) {
        if (isValid(m) & isValid(n)) return Character.toLowerCase(m) == Character.toLowerCase(n);
        return false;
    }

    private boolean isValid(char c) {
        if (c >= '0' &&  c <= '9') return true;
        if (c >= 'a' &&  c <= 'z') return true;
        if (c >= 'A' &&  c <= 'Z') return true;
        return false;
    }
}


/*
Use regular expression [^a-zA-Z0-9] to replace all non-alphanumeric chars with ""
*/
public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        String str = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}

/*
Same solution as above: just manual filter string
*/

public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        StringBuffer sb = new StringBuffer();
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c <= 'z' && c >= 'a') || (c >= '0' && c <= '9')) {
                sb.append(s.charAt(i));
            }
        }
        s = sb.toString();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}


```