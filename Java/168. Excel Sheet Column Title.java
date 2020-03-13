E
tags: Math
time: O(n)
space: O(1)

#### 基本换算
- 26位, 像10位一样去思考
- 从末尾开始, mod %26 可以拿到 末尾数字 remain = n % 26
- 特殊: remain = 0 的时候, 也就是说n是26的倍数, 末尾应该是 'Z'
- 记录'Z'了之后, n--


```
/*
Given a positive integer, return its corresponding column title 
as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
*/

/*
Thoughts:
26 bits => num / 26 = 1 => 'A' on that digit
*/
class Solution {
    public String convertToTitle(int n) {
        if (n <= 0) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remain = n % 26;
            n = n / 26;
            if (remain == 0) {
                sb.append("Z");
                n--;
            } else {
                sb.append((char)('A' + remain - 1));
            }
        }
        return sb.reverse().toString();
    }
}
```