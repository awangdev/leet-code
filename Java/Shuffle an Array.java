M
1524032851
tags: Permutation

像shuffle music 一样, 做一套shuffle array的functions: 

shuffle() 给出random的permutation

reset() 给出最初的nums

#### Permutation
- Permutation 实际上就是在list/array/... 上面给元素换位置
- 硬换位置, 每次换的位置不同, 用random来找到要换的index
- 维持同一个random seed
- O(n)

##### Note
- compute all permutations 太慢, 不可行.

```

/*
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. 
Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

*/

/*
Thoughts:
1. Store original = nums;
2. Use universal Random object
3. Randomly pick n different indexes to swap around, which produces permutation
*/
class Solution {
    private int[] original;
    private Random random;
    
    public Solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            original = new int[]{ };
            return;
        }
        int n = nums.length;
        original = Arrays.copyOf(nums, n);
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (original == null || original.length == 0) {
            return original;
        }
        int n = original.length;
        int[] output = Arrays.copyOf(original, n);
        for (int i = 0; i < n; i++) {
            int offset = random.nextInt(n - i);
            swap(output, i, i + offset);
        }
        return output;
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[y];
        nums[y] = nums[x];
        nums[x] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

/*
It's not necessary to compute all permutations during initialization, not efficient.
Thoughts:
1. Store a original versiion
2. Find all permutations
3. Return a random item from the list
*/
class Solution {
    private int[] original;
    private List<int[]> permutation;
    public Solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            original = null;
            permutation = null;
        }
        int n = nums.length;
        // Copy
        original = Arrays.copyOf(nums, n);
        
        // Permutation
        Arrays.sort(nums);
        permutation = new ArrayList<>();
        permutation.add(nums);
        
        for (int pos = 0; pos < n; pos++) {
            for (int i = permutation.size() - 1; i >= 0; i--) {
                int[] arr = permutation.get(i);
                Arrays.sort(arr, pos, n);
                
                for (int j = pos + 1; j < n; j++) {
                    if (arr[j] == arr[j - 1]) {
                        continue;
                    }
                    swap(arr, pos, j);
                    permutation.add(Arrays.copyOf(arr, n));
                    swap(arr, pos, j);
                }
            }
        }
    }
    
    private void swap(int[] nums, int x, int y) {
        int temp = nums[y];
        nums[y] = nums[x];
        nums[x] = temp;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (permutation == null || permutation.size() == 0 || original == null || original.length == 0) {
            return null;
        }
        Random rd = new Random();
        int index = rd.nextInt(original.length);
        return permutation.get(index);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
```