 
 
 
## Hash Map (1)
**0. [Group Anagrams.java](https://github.com/awangdev/LintCode/blob/master/Java/Group%20Anagrams.java)**      Level: Medium
      

给一串string, return list of list, 把anagram 放在一起.

#### Hash Map, key 是 character frequency
- 存anagram
- 用 character frequency 来做unique key
- 用固定长度的char[26] arr 存每个字母的frequency; 然后再 new string(arr).   
- 因为每个位子上的frequency的变化，就能构建一个unique的string
- O(nk), k = max word length


#### Hash Map, key 是 sorted string
- 和check anagram 想法一样：转化并sort char array，用来作为key。
- 把所有anagram 存在一起。注意结尾Collections.sort().
- O(NKlog(K)), N = string[] length, k = longest word length    




---

