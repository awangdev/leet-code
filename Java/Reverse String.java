E
tags: Two Pointers, String

Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail

```
/*
Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.
*/

/*
Thoughts:
Obvious: new StringBuilder().reverse().
Or, turn into charArray and reverse
*/
class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            char temp = s[i];
            s[i] = s[n - i - 1];
            s[n - i - 1] = temp;
        }
    }
}
```