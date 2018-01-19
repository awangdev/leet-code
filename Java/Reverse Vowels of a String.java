E
1516344636

方法1: two pointer. 还没有做

方法2: 拿出所有vowels, 反过来放进去. O(n)

```
/**
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

 */

 /*
Thoughts:
vowels: a e i o u A E I O U
HashMap, store the <pos, letter> and put them back in reverse order.
*/
class Solution {

    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        final List<Character> matches = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                matches.add(s.charAt(i));
            }
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (vowels.contains(letter)) {
                int lastMatchIndex = matches.size() - 1;
                sb.append(matches.get(lastMatchIndex));
                matches.remove(lastMatchIndex);
            } else {
                sb.append(letter);
            }
        }
        return sb.toString();
    }
}
```