M
1528126427
tags: String

#### Break by space, then flip 
- 结尾不能有空格
- trim() output
- 如果Input是 ""的话，split以后就啥也没有了
- 另个题目Reverse Words in String (char[]) 可以in-place, 条件是char[]里面是没有首尾空格.
- Time, Space: O(n)

#### Other methods
- flip entire string, then flip each individual string (代码有点多, 这道题犯不着)

```
/*
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
Hide Company Tags Bloomberg
Hide Tags String
Hide Similar Problems (M) Reverse Words in a String II


*/

/*
    For simplicity of code, try the appending from behind.

    Have multiple two other ways to do it: 
        1. flip all,then flip each individual word; 
        2. break into parts and append from end to beginning.
*/
public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0 || s.indexOf(" ") == -1) {
            return s;
        }

        String[] strs = s.split("\\s+");
        StringBuffer sb = new StringBuffer();

        for (String str : strs) {
            sb.insert(0, str + " ");
        }

        return sb.toString().trim();
    }
}




/*
Thinking Process: 
1. Reverse it like reversing a int array
2. Use Split into arrays.
3. When reversing, make sure not empty string ""
*/
public class Solution {
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        for (int i = 0, j = strs.length - 1; i < j; i++, j--) {
            String temp = new String(strs[j]);
            strs[j] = new String(strs[i]);
            strs[i] = temp;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++){
            if (strs[i].length() > 0) {
                sb.append(strs[i]);
                if (i < strs.length - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}


/*

    Thoughts: 12.08.2015,
    Try the reverse method on string
    This is the leaset favor: because it creates too much trouble. 
    Simply reverse the words broken into String[] with split(" ") is much easier

    Only good practice here: the reverse with StringBuffer
*/
    // I LOVE YOU 
public class Solution {
    public String reverseWords(String s) {
        //Reverse the contents of the string buffer
        public void reverse(StringBuffer sb, int start, int end) {
            for (int i = start, j = end; i < j; i++,j--) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(j));
                sb.setCharAt(j, temp);
            }
        }

        if (s == null || s.length() == 0 || s.indexOf(" ") == -1) {
            return s;
        }
        s = s.trim();//no head && tail " "
        if (s.length() == 0) {
            return s;
        }

        StringBuffer sb = new StringBuffer();
        for (String str : s.split(" ")) {
            if (str.trim().length() != 0) {
                sb.append(str + " ");
            }
        }

        reverse(sb, 0, sb.length() - 1);
        sb.append(" ");
        int start = 1;
        int end = start + sb.substring(start).indexOf(" ");
        //Process all words separate by space
        while (end != -1 && start < end) {
            reverse(sb, start, end - 1);
            start = end + 1;
            end = start + (sb.substring(start)).indexOf(" ");
        }

        return sb.toString().trim();
    }

    

}

```