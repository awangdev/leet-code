E

Similar to Reverse Integer.
可以用StringBuffer, 也可以two pointer reverse head/tail

```
/*
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".
*/

/*
Thoughts:
Obvious: new StringBuilder().reverse().
Or, turn into charArray and reverse
*/
class Solution {
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        int length = arr.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[length - i - 1];
            arr[length - i - 1] = temp;
        }
        return String.valueOf(arr);
        
        //return new StringBuilder(s).reverse().toString();
    }
}
```