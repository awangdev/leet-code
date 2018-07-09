M
1531112557
tags: Sort, Two Pointers, String, Partition

给一串字符(ASCII 大写, 小写字母), 要求sort 小写字母, 在大写字母前面. 

字母间的前后顺序无所谓, 也不需要preserve original order .

跟sort color分成相似.

#### Partition + Two pointers
- 其实就是quick sort里面的partition function的简化版
- Two pointers, 找一个 pivot 'a' 来区分大写小写字母
- ASCII code 里面 大写字母在小写字母前面, 数字更小
- 然后 while, move start++, end--,
- 每一轮都swap

#### Two pointers
- 直接用两个 pointer left/right 标记开头结尾
- 每次遇到 `>= 'a'` 就是小写字母, swap(chars, i, left);
- 每次遇到 `< 'a'` 就是大写字母, swap(chars, i, right);
- 注意: 每次处理完left swap, 任由for loop i++, 因为确定 [0 left] 都是准确的
- 每次处理完 right swap, 我们不确定从 right index 换过来的是不是正确的, 所以 i--, 跟for loop 的 i++抵消.
- 写 while loop 的 solution看起来更容易理解.

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

*/

/*

Thoughts:
Another two pointer sorting.
Difference: use a ASCII code 'a' as the pivot. all the letters that from a ~ z 
have bigger integer values, and A~Z have small integer values.
This problem requires lowcase+upperCase, so we'd sort the list from high to low.
NOTE: in the 2 while loop, the it's always having ">='

*/
public class Solution {
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) return;

        char pivot = 'a';
        int start = 0, end = chars.length - 1;

        while (start <= end) {
            while (start <= end && chars[start] >= pivot) start++;
            while (start <= end && chars[end] < pivot) end--;

            if (start <= end) swap(chars, start++, end--);
        }
    }

    private void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
}

// while loop instead of for loop
class Solution {
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) return;

        int i = 0, left = 0, right = chars.length - 1;
        while(i <= right) {
            if (chars[i] >= 'a') swap(chars, i++, left++); // we know any index < left is accurate, i++
            else swap(chars, i, right--); // not sure what on index right, need to re-consider i
        }
    }
    
    private void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
}


// Pure Two pointers with for loop
class Solution {
    public void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0) return;

        int left = 0, right = chars.length - 1;
        for (int i = 0; i <= right; i++) {
            if (chars[i] >= 'a') swap(chars, i, left++); // we know any index < left is accurate
            else swap(chars, i--, right--); // not sure what on index right, need to re-consider i, so i--
        }
    }
    
    private void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
}



```