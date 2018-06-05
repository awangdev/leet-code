M
tags: Sort, Two Pointers, String


```
/*
Given a string which contains only letters. Sort it by lower case first and upper case second.

Example
For "abAcD", a reasonable answer is "acbAD"

Note
It's not necessary to keep the original order of lower-case letters and upper case letters.

Challenge
Do it in one-pass and in-place.

Tags Expand 
String Two Pointers LintCode Copyright Sort

Thoughts:
Another two pointer sorting.
Difference: use a ASCII code 'a' as the pivot. all the letters that from a ~ z 
have bigger integer values, and A~Z have small integer values.
This problem requires lowcase+upperCase, so we'd sort the list from high to low.
NOTE: in the 2 while loop, the it's always having ">='

*/


public class Solution {
    /** 
     *@param chars: The letter array you should sort by Case
     *@return: void
     */
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) {
            return;
        }
        char pivot = 'a';
        int start = 0; int end = chars.length - 1;
        while (start <= end) {
            while (start <= end && chars[start] >= pivot) {
                start++;
            }
            while (start <= end && chars[end] < pivot) {
                end--;
            }
            if (start <= end) {
                char temp = chars[end];
                chars[end] = chars[start];
                chars[start] = temp;
                start++;
                end--;
            }
        }
    }
}












```