// Give a list of string, where every string in the list is of size 5. Return the list of 5 string such that all the characters in each of the strings are unique
// i.e if we combine all the strings(not nnecessary) we will have 25 unique characters)
// eg
// Input explanation
// List of string with length of 5 each
// intput = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra", "ocean", "quick", "world", "jumps", "foxes", "liver"]

// Without Bit Masking DP solution

import java.util.*;

public class UniqueWordsSelector {
    public List<String> solution(String[] strs) {
        Map<String, List<String>> memo = new HashMap<>();
        return dfs(0, 0, strs, new HashSet<>(), memo);
    }

    private List<String> dfs(int i, int curWordsCount, String[] strs, Set<Character> charsUsed, Map<String, List<String>> memo) {
        // Base case: If 5 words are selected, return an empty list
        if (curWordsCount == 5) return new ArrayList<>();
        // Base case: If all words are processed, return null
        if (i == strs.length) return null;

        // Memoization key: Current index, word count, and characters used
        String memoKey = i + "|" + curWordsCount + "|" + charsUsed;
        if (memo.containsKey(memoKey)) return memo.get(memoKey);

        // Skip the current word and move to the next
        List<String> result = dfs(i + 1, curWordsCount, strs, charsUsed, memo);
        if (result != null) {
            memo.put(memoKey, result);
            return result;
        }

        // Check if the current word can be added
        if (canAdd(i, strs, charsUsed)) {
            // Add current word's characters to the set
            for (char c : strs[i].toCharArray()) charsUsed.add(c);

            // Recursively try including the current word
            result = dfs(i + 1, curWordsCount + 1, strs, charsUsed, memo);
            if (result != null) {
                result.add(strs[i]); // Add the word to the result
                memo.put(memoKey, result);
                return result;
            }

            // Backtrack: Remove the characters of the current word
            for (char c : strs[i].toCharArray()) charsUsed.remove(c);
        }

        // Cache and return null if no solution is found
        memo.put(memoKey, null);
        return null;
    }

    private boolean canAdd(int i, String[] strs, Set<Character> charsUsed) {
        for (char c : strs[i].toCharArray()) {
            if (charsUsed.contains(c)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        UniqueWordsSelector selector = new UniqueWordsSelector();
        String[] strs = {"abcde", "klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra", "fghij"};
        List<String> result = selector.solution(strs);
        System.out.println(result);
    }
}

// With Bit Masking DP solution

import java.util.*;
class Main {
    public static void main(String[] args) {
        String[] strs = new String[]{"abcde","klmno", "pqrst", "uvwxy", "zabcd", "apple", "zebra",  "fghij"};
    Map<String, List<String>> memo = new HashMap<>();
            List<String> ans =  dp(0, 0, 0, strs, memo);;
        System.out.println(ans);
    }

private static List<String> dp(int i, int curWordsCount, int bitmask, String[] strs, Map<String, List<String>> memo) {
    // Base case: If we've selected 5 words, return an empty list.
    if (curWordsCount == 5) 
        return new ArrayList<>();
    
    // Base case: If we've reached the end of the array, return null.
    if (i == strs.length) 
        return null;

    // Create a memoization key
    String key = i + "," + curWordsCount + "," + bitmask;
    if (memo.containsKey(key)) 
        return memo.get(key);

    // Skip the current string
    List<String> result = dp(i + 1, curWordsCount, bitmask, strs, memo);
    if (result != null) {
        memo.put(key, result);
        return result;
    }

    // Try to include the current string if possible
    int strBitmask = 0;
    boolean canAdd = true;
    for (char c : strs[i].toCharArray()) {
        int bit = 1 << (c - 'a');
        System.out.println(c + " : " + bit + " : "+ bitmask);
        if ((bitmask & bit) != 0) { // Character already used
            canAdd = false;
            break;
        }
        strBitmask |= bit;
    }

    if (canAdd) {
        result = dp(i + 1, curWordsCount + 1, bitmask | strBitmask, strs, memo);
        if (result != null) {
            result.add(strs[i]);
            memo.put(key, result);
            return result;
        }
    }

    // Cache and return the result
    memo.put(key, null);
    return null;
}

    
}
