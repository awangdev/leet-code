E
1516344636

方法1: two pointer. 前后两个指针, 在while loop里面跑. 注意 i<j, 一旦相遇, 就break. 找到合适的, 就做swap.
StringBuffer可以 sb.setCharAt()记得用.

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
Use i,j to find front and end vowels, and swap them.
*/
class Solution {

    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        final List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        final StringBuffer sb = new StringBuffer(s);
        
        int maxIndex = sb.length() - 1;
        int i = 0;
        int j = maxIndex;
        while (i < j) {
            while (i < maxIndex && !vowels.contains(sb.charAt(i))) i++;
            while (j > 0 && !vowels.contains(sb.charAt(j))) j--;
            
            if (i < j && vowels.contains(sb.charAt(i)) && vowels.contains(sb.charAt(j))) {
                char letter = sb.charAt(j);
                sb.setCharAt(j, sb.charAt(i));
                sb.setCharAt(i, letter);
                j--;
                i++;
            }
        }
        return sb.toString();
    }
}

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