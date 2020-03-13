E
tags: String, Basic Implementation
time: O(n)
space: O(1)

```

/*
We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 

Notes:

S contains only uppercase, lowercase and spaces. Exactly one space between each word.
1 <= S.length <= 150.
 */

/*
1. check letter and apply ruleA/B.
2. use index to append # of 'a' to end of string.

- split into array
- build all back together
*/
class Solution {
    Character[] chars = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    HashSet<Character> set = new HashSet<>(Arrays.asList(chars));
    
    public String toGoatLatin(String S) {
        StringBuffer sb = new StringBuffer();
        String[] arr = S.split(" ");
        for (int i = 0; i < arr.length; i++) {
            sb.append(convert(arr[i], i));
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    
    public String convert(String s, int index) {
        StringBuffer sb = new StringBuffer(s);
        char c = s.charAt(0);

        if (!set.contains(s.charAt(0))) {
            sb.deleteCharAt(0);
            sb.append(c);
        }
        sb.append("ma");
        for (int i = 0; i <= index; i++) sb.append("a");
        return sb.toString();
    }
}
```