package Coderally_1;

import java.util.HashMap;
import java.util.Scanner;

public class LongestDistinctSubstring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        System.out.println(longestDistinctSubstring(str));
    }

    public static String longestDistinctSubstring(String str) {
        int n = str.length();
        int maxLength = 0;
        int start = 0;
        int startIndex = 0;

        HashMap<Character, Integer> seen = new HashMap<>();

        for (int end = 0; end < n; end++) {
            char currentChar = str.charAt(end);

            if (seen.containsKey(currentChar)) {
                start = Math.max(start, seen.get(currentChar) + 1);
            }

            seen.put(currentChar, end);

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                startIndex = start;
            }
        }

        return str.substring(startIndex, startIndex + maxLength);
    }
}
