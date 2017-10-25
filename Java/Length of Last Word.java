/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Example
Given s = "Hello World", return 5.

Note
A word is defined as a character sequence consists of non-space characters only.

Tags Expand 
String

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
