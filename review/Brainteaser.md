 
 
 
## Brainteaser (2)
**0. [Nim Game.java](https://github.com/awangdev/LintCode/blob/master/Java/Nim%20Game.java)**      Level: Easy      Tags: [Brainteaser, DP, Game Theory]
      
#### Brainteaser
- 著名Nim游戏
- 写一些，发现n=4,5,6,7,8...etc之后的情况有规律性: 谁先手拿到4就输了.
- 最终很简单n%4!=0就可以了,  time, space O(1)

#### DP
- 正规地找规律做, 就跟 coins in a line 一样, 按照先手后手来做
- 可以rolling array 优化空间
- Time O(n), 当然啦, 这个题目这样会timeout, 可以使用brainteaser的做法写出结果.



---

**1. [319. Bulb Switcher.java](https://github.com/awangdev/LintCode/blob/master/Java/319.%20Bulb%20Switcher.java)**      Level: Medium      Tags: [Brainteaser, Math]
      

#### Brainteaser
- https://leetcode.com/problems/bulb-switcher/discuss/77104/Math-solution..

#### Brutle:
- if just impl, it take O(n^2):
- repating: some pos are toggled mutiple times: if we know total times, easy to determin each pos.
- loop over [2, n], count times on each index



---

