注意如何滤过: alphanumeric 
```
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Example
"A man, a plan, a canal: Panama" is a palindrome.

"race a car" is not a palindrome.

Note
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

Challenge
O(n) time without extra memory.

Tags Expand 
String Two Pointers
*/
/*
Thoughts:
Pointer from front to end. Front char has to equal end char.
For loop check till s.length()/2.
If even, for example, s.length() == 4, i will be [0 ~ 1].
If odd, for example, s.length() == 5, i will be [0 ~ 1]. And inddex 2 itself stands out, and won't hurt palindrome.
*/

public class Solution {
    /**
     * @param s A string
     * @return Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	int start = 0;
    	int end = s.length() - 1;
    	s = s.toLowerCase();
    	while (start < end) {
    		while (start < s.length() && 
    			(s.charAt(start) < '0' || (s.charAt(start) > '9' && s.charAt(start) < 'a') || s.charAt(start) > 'z') ) {
    			start++;
    		}
    		while (end >= 0 && 
    			(s.charAt(end) < '0' || (s.charAt(end) > '9' && s.charAt(end) < 'a') || s.charAt(end) > 'z')) {
    			end--;
    		}
    		if (start < end && s.charAt(start) != s.charAt(end)) {
    			return false;
    		}
    		start++;
    		end--;
    	}
    	return true;
    }
}

```