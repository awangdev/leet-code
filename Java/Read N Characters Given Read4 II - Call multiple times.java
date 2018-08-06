H
1533532566
tags: String, Enumeration

Read N Character using `Read4(char[] buf)` 的加强版: 可以不断读 read(buf, n)

#### String 
- 注意String的index handle, 慢慢写edge case
- 理解题目意思: `read4(char[] buf)` 这样的 `populate input object` 的function稍微少一点. 
- 遇到时候, 仔细理解function用法, 不要慌乱. 其实思考方式很简单, 仔细handle string 还有 edge case就好了.

```
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) 
that reads n characters from the file.

Note:
The read function may be called multiple times.

Example 1: 

Given buf = "abc"
read("abc", 1) // returns "a"
read("abc", 2); // returns "bc"
read("abc", 1); // returns ""
Example 2: 

Given buf = "abc"
read("abc", 4) // returns "abc"
read("abc", 1); // returns ""

 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

/*
Cache results from read4(char*); only read again if not enough; 
Cache the read cacheIndex of read4 result;
If read4 returns 0, just return ""; maybe have global variable to mark end.
*/
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private char[] cache = new char[4];
    private int cacheIndex = 0;
    private int count = 0;
    //private int index = 0;
    private boolean isEnd = false;
    public int read(char[] buf, int n) {
        if (buf == null || n <= 0 || isEnd) return 0; // if read4 is ended, return 0;
        
        // decrease n for existing read4 content
        int i = 0; // current iteration
        while (cacheIndex > 0 && cacheIndex < count && i < n) {
            buf[i++] = cache[cacheIndex++];
        }
        // read while using while loop until n is exhausted
        while (i < n) {
            cache = new char[4];
            count = read4(cache);
            int range = i + 3 < n ? count : Math.min(n - i, count);
            System.arraycopy(cache, 0, buf, i, range);
            i += range;
            if (range < 4) cacheIndex = range;
            if (count < 4 && range == count) isEnd = true;
            if (count < 4) break;
        }
        return i;
    }
}
```