好久以前做的一个题了。
记得：'A' - 'A' = 0 呀。所以 any char - 'A' + 1 = 这个char在我们这个题目里面所代表的数字。
其次，26个字母代表着26位运算。和10位一样嘛，那第一位x26就好了。
```
/*

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

 A -> 1

 B -> 2

 C -> 3

 ...

 Z -> 26

 AA -> 27

 AB -> 28

*/

public class Solution {//ABC -> 'A', 'B', 'C'

public int titleToNumber(String s) {//S = AA

int rst = 0;

char[] arr = s.toCharArray();

for (int i = 0; i < arr.length; i++) {//i = 0,1,2 // (char c : arr)

rst = rst * 26 + arr[i] - 'A' + 1;//rst =1, 26 + 1 = 27, 

}

return rst;

}

}
```