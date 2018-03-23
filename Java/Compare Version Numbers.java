M
1521777505
tags: String

给两串version number,  由数字和'.' 组成. 比较先后顺序. 

If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

#### divide and conquer 
- 用 str.split("\\.") 分割string
- Convert成integer, 逐个击破

#### 注意
- '1.0' 和 '0' 是相等的
- 如果可以假设version integer都是valid, 直接Integer.parseInt()就可以了
- 不然的话, 可以compare string

```
/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
*/

/*
Thoughts:
- divide and conqure: divide by '.'
- during the same cun, the one with no sub-division, it's the leading version
- use Integer.parseInt() with assumption that the numbers are valid integer.
*/
class Solution {
    public int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] subVersion1 = version1.split("\\.");
        String[] subVersion2 = version2.split("\\.");
        int size = Math.max(subVersion1.length, subVersion2.length);
        int rst = 0;
        for (int i = 0; i < size; i++) {
            if (i >= subVersion1.length) {
                rst = Integer.parseInt(subVersion2[i]) == 0 ? 0 : -1; // assume the missing subVersion1 = 0
            } else if (i >= subVersion2.length) {
                rst = Integer.parseInt(subVersion1[i]) == 0 ? 0 : 1; // assume the missing subVersion2 = 0
            } else { // both exist
                if (Integer.parseInt(subVersion1[i]) != Integer.parseInt(subVersion2[i])) {
                    rst = Integer.parseInt(subVersion1[i]) < Integer.parseInt(subVersion2[i]) ? -1 : 1;    
                }
            }
            if (rst != 0) {
                return rst;
            }
        }
        return rst;
    }
}


/*
Thoughts:
- divide and conqure: divide by '.'
- during the same cun, the one with no sub-division, it's the leading version
- compare string
*/
class Solution {
    public int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        if (!version1.contains(".") && !version2.contains(".")) {
            return compareString(version1, version2);
        }
        String[] subVersion1 = version1.split("\\.");
        String[] subVersion2 = version2.split("\\.");
        int size = Math.max(subVersion1.length, subVersion2.length);
        int rst = 0;
        for (int i = 0; i < size; i++) {
            if (i >= subVersion1.length) {
                rst = compareString("0", subVersion2[i]);    
            } else if (i >= subVersion2.length) {
                rst = compareString(subVersion1[i], "0");
            } else {
                rst = compareString(subVersion1[i], subVersion2[i]);    
            }
            if (rst != 0) {
                return rst;
            }
        }
        return rst;
    }
    
    /*
        Assume the number can be super large, and can't be saved in Integer, or Long.
        Compare number by each digit
    */
    private int compareString(String str1, String str2) {
        if (str1.equals(str2)) {
            return 0;
        }
        while (str1 != null && str1.length() > 1 && str1.charAt(0) == '0') {
            str1 = str1.substring(1);
        }
        
        while (str2 != null && str2.length() > 1 && str2.charAt(0) == '0') {
            str2 = str2.substring(1);
        }
        
        if (str1.length() != str2.length()) {
            return str1.length() < str2.length() ? -1 : 1;
        }
        for (int i = 0; i < str1.length(); i++) {
            int digit1 = str1.charAt(i) - '0';
            int digit2 = str2.charAt(i) - '0';
            if (digit1 < digit2) {
                return -1;
            } else if (digit1 > digit2) {
                return 1;
            }
        }
        return 0;
    }
}
```