E
tags: String

给一个String, 里面有lower case character 和 ' '. 找最后一个单个word的长度

#### basics
- 从末尾找' ', 找到了计算长度
- 记得要s.trim(), 把首尾的space去掉

```
/*
Given a string s consists of upper/lower-case alphabets 
and empty space characters ' ', 

return the length of last word in the string.

If the last word does not exist, return 0.

Example
Given s = "Hello World", return 5.

Note
A word is defined as a character sequence consists of non-space characters only.

Tags Expand 
String

*/

/*
Thoughts:
Traverse from end, find space, return length
*/
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.trim();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                return s.length() - i - 1;
            }
        }

        return s.length();
    }
}

/**

Thoughts:
1. Split by space
2. return last word's length

Note: Java split: have to add '\\' in order to pass the key word. 

*/

public class Solution {
    /**
     * @param s A string
     * @return the length of last word
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
        	return 0;
        }
        String[] arr = s.split("\\ ");
        String lastWord = arr[arr.length - 1];

       	return lastWord.length();
    }
}

```