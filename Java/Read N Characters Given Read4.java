E
1533408053
tags: String, Enumeration

Read4 题目. 理解题目: 是有个input object buff, 会被populated with data.

#### String in char[] format
- 理解题目: 其实就是track `可以read多少bytes by read4() response`
- 另外一个有用的function `System.arraycopy(src, srcIndex, dest, destIndex, length)`

```
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) 
that reads n characters from the file.

Example 1:

Input: buf = "abc", n = 4
Output: "abc"
Explanation: The actual number of characters read is 3, which is "abc".
Example 2:

Input: buf = "abcde", n = 5 
Output: "abcde"
Note:
The read function will only be called once for each test case.
*/

/*
- use temp to store all items read, and System.copy into buf.
- track overall progress: i 
- if read4 returns < 4, mark end and return.
*/
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        if (buf == null || n <= 0) return 0;
        int i = 0;
        while (i < n) {
            char[] temp = new char[4];
            int count = read4(temp);
            int range =  i + 3 < n ? count : Math.min(n - i, count);
            System.arraycopy(temp, 0, buf, i, range);
            i += range;
            if (count < 4) break;
        }
        return i;
    }
}
```