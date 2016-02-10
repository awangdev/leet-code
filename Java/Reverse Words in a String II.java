/*
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array

Tags: String
Similar Problems: (M) Reverse Words in a String, (E) Rotate Array


*/

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