E
tags: String

HackerRank里面的random 题目: 给一个string, 一切成两半, 看两半要变化多少个字符, 能变成anagram.

- 切两半成两个String A,B. 分别在int count[26]里面++, --.
- 记录 26个小写字母的频率. 如果全部抵消, 就是anagram.
- 注意: 最后count出来要除以2：字母不同，会在不同的字母位上加减count,那么就是刚好重复计算了一遍。所以除以二

- Note: HackerRank里要注意自己写: Scanner, import java.util, non-static method ...etc.

```
/*
HackerRank CodePair

Sid loves to read short stories. Being a Computer Science student, 
he decides to do some frequency analysis on his favorite reading material. 
For each data point, chooses a string of length a from one book, 
and a string of length b from a second book. The strings' lengths differ by no more than 1.
|a-b|≤1, where |x| represents the absolute value function.
 
The frequency analysis consists of checking how far the strings are 
from being anagrams of one another. Your challenge is to help him find 
the minimum number of characters of the first string he needs to change to 
make it an anagram of the second string.  He can neither add nor delete characters 
from the first string. Only replacement of the characters with new ones is allowed.

Input Format
The first line will contain an integer T representing 
the number of test cases. Each test case will contain a string having length (a+b) 
which will be concatenation of both the strings described in problem. 
The string will only contain small letters and without any spaces.

Output Format
An integer corresponding to each test case is printed in a different line i.e., 
the number of changes required for each test case. Print ‘-1’ if it is not possible.

Constraints
1 ≤ T ≤ 100
 1 ≤ a+b ≤ 10,000

Sample Input
5
aaabbb
ab
abc
mnop
xyyx
Sample Output
3
1
-1
2
0 

Explanation
In the five test cases
One string must be “aaa” and the other “bbb”. The lengths are a=3 and b=3, so the difference is less than 1. No characters are common between the strings, so all three must be changed.
One string must be “a” and the second “b”. The lengths are a=1 and b=1, so the difference is less than 1. One character must be changed to them the same.
Since the string lengths a and b must differ by no more than 1, the lengths are either a=1 and b=2 or a=2 and b=1. No sequence of substitutions will make the two  anagrams of one another.
One string must be “mn" and other be “op”. The length are a=2 and b=2, so the difference is less than 1. No characters are common between the strings, so both must be changed.
One string must be “xy” and the other be “yx”. The length are a=2 and b=2, so the difference is less than 1. No changes are needed because the second string is already an anagram of the first.
*/


/*
Check if they are valid string. If length == odd, then fail, -1
Use int[26] arr to add chars from s1. If non-26-char exist, return -1
use same int arr to remove chars from s2. 
count the non-zeros

Note: the order of letters does not matter, becase all we want to change to is anagram
*/
import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner in = new Scanner(System.in);
        int numOfCase = Integer.parseInt(in.nextLine());
        Solution sol = new Solution();
        
        while (numOfCase > 0) {
            int rst = sol.validate(in.nextLine());
            System.out.println(rst);
            numOfCase--;
        }
    }
    
    public int validate(String str) {
        if (str.length() % 2 == 1) {
            return -1;
        } 
        String sA = str.substring(0, str.length()/2);
        String sB = str.substring(str.length()/2);
        
        int[] arr = new int[26];
        for (int i = 0; i < sA.length(); i++) {
            char cA = sA.charAt(i);
            char cB = sB.charAt(i);
            if (cA < 'a' || cA > 'z' || cB < 'a' || cB > 'z') {
                return -1;
            } 
            arr[cA - 'a']++;
            arr[cB - 'a']--;
        }
        
        int count = 0;
        for (int num : arr) {
            count += Math.abs(num);
        }
        return count/2;
    }
    
   
}
```