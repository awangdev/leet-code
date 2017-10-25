E

第一步：
生题型, 理解题意需要时间：
从字面和画图而言, 就是定住房子一个个过，房子左右的distance需要足够达到heater. 目标是招尽可能小的radius, 所以house和heater紧贴着是必要的.
在for loop里面定下house，把heater当作一个区间移动, 达到的第一个合适区间，这就是当下最小的理想radius，取这个值跟既定radius作比较。
比较之后，继续移动house，再试着移动heater区间去match。

第二步：
Binary Search

注意！
题目没有说given array是否sort, 我们必须sort才能够区间移动或者binary search.
TODO:
http://www.cnblogs.com/grandyang/p/6181626.html

```
/*
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

*/

/*
Thoughts:
When a house sits in between heaters, the diff between left-heater-to-house && house-to-right-heater should at least equal.
Also, because we are finding the smallest sutable radius, we'll always compare heater adjacent to the house. Therefore, we can loop over house, then move heater position to find correct covering.

Follow the above rule:
1. Loop over houses.
2. Start radius = 0, and find the minmimum feasible (when comparing, pick the larger distance becuase it'll cover both side of house)
3. Calculate abs value of distance, because house can be ahead of 1st heater, in middle of two heaters or after last heater.
4. Keep while loop for each heater, until the house-to-right-heater distance is at least equal to the left-heater-to-house.
*/

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int heaterIndex = 0;
        int radius = 0;
        int heatersLength = heaters.length;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int i = 0; i < houses.length; i++) {
            int housePos = houses[i];
            // Keeps looping until this position status: leftHeaterPos - housePos - rightHeaterPos, and the diff of two direction is at least equal
            // when equal, heaterIndex+1 beacuse it will be used to calculate the minimum feasible distance right after
            while (heaterIndex < heatersLength - 1 && Math.abs(heaters[heaterIndex + 1] - housePos) <= Math.abs(heaters[heaterIndex] - housePos)) {
                heaterIndex++;
            }
            // heaterPos will be exactly the one after current housePos, so use this as the radius
            radius = Math.max(radius, Math.abs(heaters[heaterIndex] - housePos));
        }
        return radius;
    }
}
```