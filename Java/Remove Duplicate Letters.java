H
1528267312
tags: Stack, Greedy, Hash Table

#### Hash Table, Greedy
- count[] = int[256], 不需要 `c-'a'`
- boolean visited[]: 一旦一个字母固定了位置后, 再次遇到时候, 直接跳过用过的character
- 如果tail字母可以变小, 那就delete掉tail, 重新接上新字母 (前提条件: 去掉的字母后面还会再出现, set visited[tail] = false)
- Space: O(1) count[], visited[].
- Time: Go through all letters O(n)

#### Stack
- Use stack instead of stringBuffer: keep append/remove last added item
- However, stringBuffer appears to be faster than stack.

```
/*
Given a string which contains only lowercase letters, 
remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:

Input: "bcabc"
Output: "abc"
Example 2:

Input: "cbacdcbc"
Output: "acdb"

*/

/*
Thoughts:
- Count # of chars in int[26].
- try all letters of string, and add to stringBuffer sb
- have while loop to remove tail letter from sb, if the curr letter is more suitable && tail letter has more occurance in the future
*/

class Solution { 
    String removeDuplicateLetters(String s) {
        // check edge case
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        // build count array
        int[] count = new int[256];
        boolean[] visited = new boolean[256];
        for (char c : arr) {
            count[c]++;
        }

        // loop over s and aggregate; 
        StringBuffer sb = new StringBuffer("0");
        for (char c : arr) {
            count[c]--;
            if (visited[c]) {
                continue;
            }
            char lastChar = sb.charAt(sb.length() - 1);
            // Compare with tail; reduce tail letter if necessary && applicable
            while (c < lastChar && count[lastChar] > 0) {
                sb.deleteCharAt(sb.length() - 1);
                visited[lastChar] = false;
                lastChar = sb.charAt(sb.length() - 1);
            }
            sb.append((char)(c));
            visited[c] = true;
        }

        return sb.substring(1).toString();
    }

}



// Using stack over StringBuffer
class Solution { 
    String removeDuplicateLetters(String s) {
        // check edge case
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] arr = s.toCharArray();
        // build count array
        int[] count = new int[256];
        boolean[] visited = new boolean[256];
        for (char c : arr) {
            count[c]++;
        }

        // loop over s and aggregate; 
        Stack<Character> stack = new Stack<>();
        stack.push('0');
        for (char c : arr) {
            count[c]--;
            if (visited[c]) {
                continue;
            }
            // Compare with tail; reduce tail letter if necessary && applicable
            while (c < stack.peek() && count[stack.peek()] > 0) {
                visited[stack.pop()] = false;
            }
            stack.push(c);
            visited[c] = true;
        }

        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.substring(1).toString();
    }

}
```