 
 
 
## Enumeration (4)
**0. [Majority Number II.java](https://github.com/awangdev/LintCode/blob/master/Java/Majority%20Number%20II.java)**      Level: Medium      Tags: [Enumeration, Greedy]
      

#### Array
- 分三份：a b c考虑
- 若a: countA++; 或b: countB++
- 或c:countA--, countB--
- 注意: 按照if statement的顺序, valA&&countA 比valB&&countB有优先性
- 最后出现的两个count>0的a和b,自然是potentially大于1/3的。其中有一个大于1/3.
- 比较countA和countB哪个大，就return哪一个。



---

**1. [Rotate Image.java](https://github.com/awangdev/LintCode/blob/master/Java/Rotate%20Image.java)**      Level: Medium      Tags: [Array, Enumeration]
      

#### 找公式规律
- 找到个转角度的规律公式: r = c; c = (w - r)
- 用temp variable, swap in place.



---

**2. [Next Closest Time.java](https://github.com/awangdev/LintCode/blob/master/Java/Next%20Closest%20Time.java)**      Level: Medium      Tags: [Basic Implementation, Enumeration, String]
      

给一个时间string"12:09", 用里面的4个integer组合成其他时间string, 目标找最小的next time.

如果组合出的time string 在input time之前, 默认 + 24 hours.

#### String
- enumerate all candidates and filter to keep the correct ones
- String.compareTo(string) -> gives lexicographical comparision



---

**3. [Spiral Matrix.java](https://github.com/awangdev/LintCode/blob/master/Java/Spiral%20Matrix.java)**      Level: Medium      Tags: [Array, Enumeration]
      

从(0,0)坐标, 走完spiral matrix, 把结果存在list里.

#### DX, DY
- Basic implementation, array, enumeration
- 写一下position前进的方向: RIGHT->DOWN->LEFT->UP
- 用一个direction status 确定方向
- 写一个compute direction function 改变方向 `(direction + 1) % 4`
- `boolean[][] visited` 来track走过的地方



---

