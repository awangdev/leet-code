M
tags: String

```
/*
Write a method to replace all spaces in a string with %20. 
The string is given in a characters array, you can assume it has enough space for replacement and you are given the true length of the string.

Example
Given "Mr John Smith", length = 13.

The string after replacement should be "Mr%20John%20Smith".

Note
If you are using Java or Python，please use characters array instead of string.

Challenge
Do it in-place.

Tags Expand 
String Cracking The Coding Interview

Thoughts:
Overriding the array from the back to front.
This is because as we re-writing the string from the back, stuff at head of the string does not change yet.
This is wonderful:) 

*/


public class Solution {
    /**
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
    	if (string == null || string.length == 0) {
    		return 0;
    	}
    	int count = 0;
    	for (char c : string) {
    		if (c == ' ') {
    			count += 2;
    		}
    	}
    	int lastIndex = length + count - 1;
    	//from back to front:
    	for (int i = length - 1; i >= 0; i--) {
    		if (string[i] == ' ') {
				string[lastIndex--] = '0';
				string[lastIndex--] = '2';
				string[lastIndex--] = '%';
    		} else {
    			string[lastIndex--] = string[i];
    		}
    	}
    	return length + count;
    }
}

```