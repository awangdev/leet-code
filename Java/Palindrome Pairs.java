H
1521010215
tags: Hash Table, String, Trie

Obvious的做法是全部试一遍, 判断, 变成 O(n^2) * O(m) = O(mn^2). O(m): isPalindrome() time.

当然不行了, 那就看是O(nlogN), 还是O(n)?

#### 方法1: Hash Table + Palindrome的性质. 复合型.
O(mn)

##### 思路
- 每一个word, 都可以拆分成 front + mid + end. 如果这个word + 其他word可以组成palindrome,那就是说
- 砍掉 (mid+end), front.reverse() 应该存在 words[] 里面.
- 砍掉 (front+mid), end.reverse() 应该存在 words[] 里面.
- 我们用HashMap存所有的<word, index>, 然后reverse, 找配对就好.

##### Corner case
- 如果有 empty string "", 那么它跟任何一个palindrome word, 都可以配对, 并且根据位置前后变换, 凑成2份 distinct indexes.
- 这样就有了那个 `if (reverseEnd.equals("")) {...}` 的logic.
- 注意: 虽然在处理砍头/砍尾的两个 for loop里面都在根据 empty string 重复记录, 
  但因为 "" 自己本身不能作为起点, 所以overall只会在被其他palindrome配对时记录一次.


#### 方法2: Trie
还要做一下那.

```
/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
*/

/*
Thoughts:
If pairing all elements in words, it'll take O(n^2), which is not sufficient.
What about O(nLogN)? O(n)? 
Given hints, we could leverage the characristic of palindrome to make it O(mn)

Given the 'pair' requirement, one palindrome can be broken down to 2 parts: front, mid, end, where mid can be ''
Taking any word from words: 'bat':
if we take front = 'b', mid = 'at', we want to find end = 'b' from rest of words
if we take front = 'ba', mid = 't', we want to find end = 'ab' from rest of words
if we take front = 'bat', mid = '', we want to find end = 'tab' from rest of words: found it.

corner case:
empty string? should be fine, matching with all palindrome words
what if ["aab", "baa", "aa", "bbaa"] creates same palindrome? They count as distinct pairs

- set of words
- cut string, look up, set pair
*/
/*
Thoughts:
If pairing all elements in words, it'll take O(n^2), which is not sufficient.
What about O(nLogN)? O(n)? 
Given hints, we could leverage the characristic of palindrome to make it O(mn)

Given the 'pair' requirement, one palindrome can be broken down to 2 parts: front, mid, end, where mid can be ''
Taking any word from words: 'bat':
if we take front = 'b', mid = 'at', we want to find end = 'b' from rest of words
if we take front = 'ba', mid = 't', we want to find end = 'ab' from rest of words
if we take front = 'bat', mid = '', we want to find end = 'tab' from rest of words: found it.

corner case:
empty string? should be fine, matching with all palindrome words
what if ["aab", "baa", "aa", "bbaa"] creates same palindrome? They count as distinct pairs

- set of words
- cut string, look up, set pair
*/
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> rst = new ArrayList<>();
        if (words == null || words.length == 0) {
            return rst;
        }

        int n = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
        }
        
        
        for (String word : words) { // O(n)
            // Cut middle and append the reverse to end
            for (int i = 0; i < word.length(); i++) { // O(m)
                String middle = word.substring(i + 1);
                if (!isPalindrome(middle)) {
                    continue;
                }
                String reverseEnd = new StringBuffer(word.substring(0, i + 1)).reverse().toString();
                if (map.containsKey(reverseEnd) && map.get(reverseEnd) != map.get(word)) {
                    int index1 = map.get(word);
                    int index2 = map.get(reverseEnd);
                    List<Integer> list = Arrays.asList(index1, index2);
                    rst.add(list);
                    if (reverseEnd.equals("")) { // empty create 2nd pair
                        list = Arrays.asList(index2, index1);
                        rst.add(list);
                    }
                }
            }
            
            // Cut front and append the reverse to front
            for (int i = 0; i < word.length(); i++) { // O(m)
                String middle = word.substring(0, i + 1);
                if (!isPalindrome(middle)) {
                    continue;
                }
                String reverseEnd = new StringBuffer(word.substring(i + 1)).reverse().toString();
                if (map.containsKey(reverseEnd) && map.get(reverseEnd) != map.get(word)) {
                    int index1 = map.get(word);
                    int index2 = map.get(reverseEnd);
                    List<Integer> list = Arrays.asList(index2, index1);
                    rst.add(list);
                    if (reverseEnd.equals("")) { // empty create 2nd pair
                        list = Arrays.asList(index1, index2);
                        rst.add(list);
                    }
                }
            }
        }
        
        return rst;
    }
    
    private boolean isPalindrome(String word) {
        if (word.length() == 1) {
            return true;
        }
        int start = 0;
        int end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}

// Still solution1, with a bit simplified code.
class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> rst = new ArrayList<>();
        if (words == null || words.length == 0) {
            return rst;
        }

        int n = words.length;
        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
        }
        
        
        for (String word : words) { // O(n)
            for (int i = 0; i < word.length(); i++) { // O(m)
                String middle = word.substring(i + 1);
                String reverseEnd = new StringBuffer(word.substring(0, i + 1)).reverse().toString();
                // Cut middle and append the reverse to end
                findPair(rst, word, middle, reverseEnd, true);

                // Cut front and append the reverse to front
                middle = word.substring(0, i + 1);
                reverseEnd = new StringBuffer(word.substring(i + 1)).reverse().toString();
                findPair(rst, word, middle, reverseEnd, false);
            }
        }
        
        return rst;
    }
    
    private void findPair(List<List<Integer>> rst, String word, String middle, String reverseEnd, boolean front) {
        if (!isPalindrome(middle)) {
            return;
        }
        if (map.containsKey(reverseEnd) && map.get(reverseEnd) != map.get(word)) {
            int index1 = map.get(word);
            int index2 = map.get(reverseEnd);
            List<Integer> list = Arrays.asList(index1, index2);
            if (!front) {
                Collections.reverse(list);
            }
            rst.add(list);
            if (reverseEnd.equals("")) { // empty create 2nd pair
                list = Arrays.asList(index2, index1);
                if (!front) {
                    Collections.reverse(list);
                }
                rst.add(list);
            }
        }
    }
    
    private boolean isPalindrome(String word) {
        if (word.length() == 1) {
            return true;
        }
        int start = 0;
        int end = word.length() - 1;
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}
```