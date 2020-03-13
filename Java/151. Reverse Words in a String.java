M
tags: String
time: O(n)
spacee: O(n)

#### Method1: Split string by space, then flip 
- Option1: With `s.split(" ")`: No brain, and super fast
- Option2: With `s.split("\\s+")`, it skips space, but slow. Use sb.insert(0, xxx)
- trim() output
- Time, Space: O(n)

#### Method2: Flip entire, then individual, two pointer
- flip entire string, then flip each individual string
- Time, Space: O(n)

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
Method1:
    For simplicity of code, try the appending from behind.

    Have multiple two other ways to do it: 
        1. flip all,then flip each individual word; 
        2. break into parts and append from end to beginning.
*/
public class Solution {
    public String reverseWords(String s) {
        if (s == null) return s;

        String[] strs = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String str : strs) {
            if (str.length() != 0) sb.insert(0, str + " ");
        }
        return sb.toString().trim();
    }
}


/*
1. Reverse it like reversing a int array
2. Use Split into arrays.
3. When reversing, make sure not empty string ""
*/
public class Solution {
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        reverse(strs);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++){
            if (strs[i].length() > 0) {
                sb.append(strs[i]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private void reverse(String[] strs) {
        int i = 0, j = strs.length - 1;
        while (i < j) {
            String temp = strs[i];
            strs[i++] = strs[j];
            strs[j--] = temp;
        }
    }
}

/*
Method2:
1. to char array and flip all
2. flip individual
3. skip all spaces
*/
public class Solution {
    public String reverseWords(String s) {
        if (s == null) return s;
        int n = s.length();
        // 1. flip all
        char[] arr = s.toCharArray();
        reverse(arr, 0, n - 1);
        // 2. reverse individual
        reverseIndividual(arr, n);
        // 3. output and skip space
        return output(arr);
    }

    private String output(char[] arr) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (char c : arr) {
            count += c == ' ' ? 1 : 0;
            if (count != 0 & c != ' ') count = 0;
            if (count <= 1) sb.append(c);
        }
        return sb.toString().trim();
    }

    private void reverseIndividual(char[] arr, int n) {
        int i = 0, j = 0;
        while (i < n) {
            while (i < j || i < n && arr[i] == ' ') i++; // skip space
            while (j < i || j < n && arr[j] != ' ') j++; // skip non-space
            reverse(arr, i , j - 1);
        }
    }

    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;
        }
    }
}



```