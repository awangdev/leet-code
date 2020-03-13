H
tags: MiniMax
time: TODO
space: TODO

TODO: revist time/space complexity

#### Minimax, find target, and use it to eliminate
- `擒贼先擒王`: find the candidate that has largest set of correlations with the rest candidates, and eliminate based on this candidate.
    - `approach A`: count the candidate that has 0 overlaps, find min of this poll
    - `approach B`: count the candidate that has largest # of connections
- cross-compare, count `match==0` <word, count>: find candidates that has 0 overlap with others
    - pick `min-count candidate A`: it is a candidate that has overlaps with most strings (since 0-match-count is lowest)
    - the above candidate will help to **eliminate** a largerset of overlapped candidates
    - guess A, return matchCount.
- filter set with matchCount: eliminateCandidate

```
/*
This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
*/



// Minimax ApproachA:
/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
/*
- pick a word A, if return 3 matches: eliminate all that has less than 3 matches with A
- pick another word, return x matches, eliminate all that has less than x matches
- repeat until found
*/
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        Set<String> set = new HashSet<>();
        for (String ss : wordlist) set.add(ss);
        for(int i = 0; i < 10; i++) {
            String target = pickCandidate(set);
            int count = master.guess(target);
            set = eliminateCandidate(set, target, count);
            if (set.isEmpty()) break;
        }
    }
   
    // Pick candidate that has largest correlation with the rest: but calculating `0-match-count`
    // Use it to eliminate its friends.
    private String pickCandidate(Set<String> words) {
        Map<String, Integer> matchCount = new HashMap<>();
        for (String a : words) {
            matchCount.putIfAbsent(a, 0);
            for (String b : words) {
                if (a.equals(b)) continue;
                if (countMatch(a, b) == 0) matchCount.put(a, matchCount.get(a) + 1);
            }
        }
        // find min-0-match-count candidate: that has overlaps with most words
        int min = Integer.MAX_VALUE;
        String candidate = null;
        for (String s : matchCount.keySet()) {
            if (matchCount.get(s) < min) {
                min = matchCount.get(s);
                candidate = s;
            }
        }
        return candidate;
    }
    
    public Set<String> eliminateCandidate(Set<String> words, String target, int count) {
        Set<String> set = new HashSet<>();
        for (String s : words) {
            if (s.equals(target)) continue;
            if (countMatch(s, target) == count) set.add(s);
        }
        return set;
    }
   
    private int countMatch(String a, String b) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (a.charAt(i) == b.charAt(i)) count++;
        }
        return count;
    }
}


// Minimax ApproachB:
class Solution {
    public void findSecretWord(String[] wordlist, Master master) {
        Set<String> set = new HashSet<>();
        for (String ss : wordlist) set.add(ss);
        for(int i = 0; i < 10; i++) {
            String target = pickCandidate(set);
            int count = master.guess(target);
            set = eliminateCandidate(set, target, count);
            if (set.isEmpty()) break;
        }
    }
   
    // Pick candidate that has largest/max number of connections with the rest, but calculating `match-count`
    // Use it to eliminate its friends.
    private String pickCandidate(Set<String> words) {
        Map<String, Integer> matchCount = new HashMap<>();
        for (String a : words) {
            matchCount.putIfAbsent(a, 0);
            for (String b : words) {
                if (a.equals(b)) continue;
                if (countMatch(a, b) != 0) matchCount.put(a, matchCount.get(a) + 1);
            }
        }
        // find min count candidate
        int max = Integer.MIN_VALUE;
        String candidate = null;
        for (String s : matchCount.keySet()) {
            if (matchCount.get(s) > max) {
                max = matchCount.get(s);
                candidate = s;
            }
        }
        return candidate;
    }
   
    public Set<String> eliminateCandidate(Set<String> words, String target, int count) {
        Set<String> set = new HashSet<>();
        for (String s : words) {
            if (s.equals(target)) continue;
            if (countMatch(s, target) == count) set.add(s);
        }
        return set;
    }
   
    private int countMatch(String a, String b) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (a.charAt(i) == b.charAt(i)) count++;
        }
        return count;
    }
}

```