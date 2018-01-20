M
1516438554

别无他法, 只能写一遍例子, 找规律,然后greedy. 
需要写一遍发现的规律比如: 从h大的开始排列, 先放入k小的. 写comparator的时候要注意正确性.
如果要sort, 并且灵活insert:用arrayList. 自己做一个object.
最后做那个'matchCount'的地方要思路清晰, 找到最正确的spot, 然后greedy insert.

O(n) space, O(nLog(n)) time, because of sorting.

可能有简化的余地, 代码有点太长.
比如试一试不用额外空间?

```
/*
Suppose you have a random list of people standing in a queue. 
Each person is described by a pair of integers (h, k), where h is the height of the person 
and k is the number of people in front of this person who have a height greater than or equal to h. 
Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

*/

/*
Thoughts:
Not having a great idea in mind, but we can pick each people and insert them into 'best at moment' spots.
Use arrayList<People> to make insertion easier.
Greedy solution.
*/
class Solution {
    class People {
        public int h;
        public int k;
        public People(int h, int k) {
            this.h = h;
            this.k = k;
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0] == null || people[0].length == 0) {
            return people;
        }
        final int[][] result = new int[people.length][people[0].length];
        
        // Set up the list and sort
        final List<People> peopleList = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            peopleList.add(new People(people[i][0], people[i][1]));
        }
        Collections.sort(peopleList, new Comparator<People>() {
            public int compare(People p1, People p2) {
                if (p1.h != p2.h) {
                    return p2.h - p1.h;
                } else {
                    return p1.k - p2.k;
                }
            }
        });

        // Find correct index and insert
        final List<People> resultList = new ArrayList<>();
        for (int i = 0; i < peopleList.size(); i++) {
            final People ppl = peopleList.get(i);
            int insertIndex = findCorrectIndex(resultList, ppl.h, ppl.k);
            resultList.add(insertIndex, ppl);
        }

        // Output result
        for (int i = 0; i < resultList.size(); i++) {
            result[i][0] = resultList.get(i).h;
            result[i][1] = resultList.get(i).k;
        }
        return result;
    }
    
    public int findCorrectIndex(final List<People> peopleList, int h, int k) {
        int matchCount = 0;
        int index = 0;
        for (; index < peopleList.size(); index++) {
            final People ppl = peopleList.get(index);
            matchCount += ppl.h >= h ? 1 : 0;
            if (matchCount > k) {
                return index;
            }
        }
        return index;
    }
}

```