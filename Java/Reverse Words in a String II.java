M

In-place reverse.

reverse用两回. 全局reverse。局部:遇到空格reverse。

注意：结尾点即使没有' '也要给reverse一下最后一个词。


```
/*
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array

Hide Company Tags Amazon Microsoft
Hide Tags String
Hide Similar Problems (M) Reverse Words in a String (E) Rotate Array


*/

/*
    Recap: 02.10.2016
    //1. reverse all. 2. reverse local with 2 pointer.
    //build reverse(start,end)
*/

public class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        reverse(s, 0, s.length - 1);
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            } else if (i == s.length - 1) {
                reverse(s, start, i);
            }
        }//end for
    }
    
    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}


/*
Thoughts: write an example: reverse the whole thing, then reverse each individual word, split by space.

Note: becase we don't have space at end of the char[], so we will ignore last word. Remember to reverse that one.
*/
public class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int len = s.length;
        //reverse whole
        for (int i = 0; i < len / 2; i++) {
            char temp = s[i];
            s[i] = s[len - 1 - i];
            s[len - 1 - i] = temp;
        }

        //reverse partial
        int start = 0;
        int mid = 0;
        int end = 0;
        for (int i = 0; i < len; i++) {
            if (s[i] == ' ') {
                mid = start + (end - start) / 2;
                for (int j = start; j <= mid; j++) {
                    char temp = s[j];
                    s[j] = s[end - (j - start)];
                    s[end - (j - start)] = temp;
                }
                start = i + 1;
            } else {
                end = i;
            }
        }

        //Process last word
        mid = start + (end - start) / 2;
        for (int j = start; j <= mid; j++) {
            char temp = s[j];
            s[j] = s[end - (j - start)];
            s[end - (j - start)] = temp;
        }
        
    }
}
```