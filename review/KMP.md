 
 
 
## KMP (1)
**0. [Shortest Palindrome.java](https://github.com/awangdev/LintCode/blob/master/Java/Shortest%20Palindrome.java)**      Level: Hard      Tags: [KMP, String]
      
#### Divide by mid point, Brutle
- check (mid, mid+1), or (mid-1, mid+1).
- If the two position matches, that is a palindrome candidate
- 比较front string 是否是 end string 的substring
- O(n^2)
- timeout on last case: ["aaaaaa....aaaacdaaa...aaaaaa"]

#### KMP
- TODO



---

