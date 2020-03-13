M
1517990589
tags: Bit Manipulation, Trie

比较难想到. 利用到XOR性质A^B=C, then A=B^C.
1. 枚举可能的A, 2. 然后一个个猜.

1. 枚举A: 因为求MAX肯定是找leading-1最多的数字, 那么枚举A从(1000000...000)开始, 
每次多一位取1或者0
2. 因为枚举A的时候是按照每个bit来, 那么B和C也要以同样数位出现.
这里吧B和C变成了prefix的形式, 放在了set里面. 
跟2sum用hashmap的思想类似, 每次用枚举的 A^B=C, 看看结果C是否已经在set里面. 
如果在, 证明枚举的A可能被B^C得出, 那么就找到了一种情况.

还用到一些技巧: 
mask = (1 << i); // i位mask
mask = mask | (1 << i); // prefix mask

```
/*
Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do t`his in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
*/

/*
Thoughts:
Brutle: O(n^2) solution over nums.

Bit Manipulation.
Write out: 5 = 0101, 25 = 16 + 8 + 1 = 00011001 => 00000101 ^ 00011001 = 00011100 = 28

To find maximum: we want the result to have as many leading '1' as possible.
One thing about XOR: if A^B=C, then A=B^C.
Let's say A is the max result we want, so: res ^ B = C, then res = B ^ C

Introduce a concept of 'prefix' of the binary numbers: use a leading-1 mask to cut front part of the binary. 
Imagine B and C are all prefixes in a set.
If res ^ B can be found from the set, that means it's possible to have B ^ C = res, where B and C are direclty picked from the set.
That is: there can be a result value, which can be generated with XOR by 2 numbers with prefix B and C.

All we have to do now:
Generate all possibilities of res: each bit can be 0 or 1.
res should grow from 1000000.....00000 (32-bit) by OR with (1 << i)
save res as it grows.
next res candidate can be 1100000.....00000, next 1010000.....00000 or 1110000.....00000


res generation refer to:
http://www.cnblogs.com/grandyang/p/5991530.html

O(32 * n) = O(n)
*/
class Solution {
    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            // Prepare prefixes
            mask = mask | (1 << i);
            final Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }
            
            // Validate result ^ B = C?
            int temp = res | (1 << i);
            for (int prefix : set) {
                if (set.contains(temp ^ prefix)) {
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }
}

```