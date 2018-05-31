 
 
 
## Enumeration (1)
**0. [Next Closest Time.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Closest%20Time.java)**      Level: Medium
      

给一个时间string"12:09", 用里面的4个integer组合成其他时间string, 目标找最小的next time.

如果组合出的time string 在input time之前, 默认 + 24 hours.

#### String
- enumerate all candidates and filter to keep the correct ones
- String.compareTo(string) -> gives lexicographical comparision



---

