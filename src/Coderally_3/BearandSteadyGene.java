package Coderally_3;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class BearandSteadyGene {
        public static int steadyGene(String gene) {
            int n = gene.length();
            int k = n / 4;

            // Calculate the required excess for each character
            Map<Character, Integer> freq = new HashMap<>();
            freq.put('A', 0);
            freq.put('C', 0);
            freq.put('G', 0);
            freq.put('T', 0);

            for (char ch : gene.toCharArray()) {
                freq.put(ch, freq.get(ch) + 1);
            }

            Map<Character, Integer> excess = new HashMap<>();
            for (char ch : "ACGT".toCharArray()) {
                excess.put(ch, Math.max(0, freq.get(ch) - k));
            }

            // If the gene is already steady
            if (excess.values().stream().allMatch(v -> v == 0)) {
                return 0;
            }

            int minLen = n;
            Map<Character, Integer> windowFreq = new HashMap<>();
            for (char ch : "ACGT".toCharArray()) {
                windowFreq.put(ch, 0);
            }

            int left = 0;
            for (int right = 0; right < n; right++) {
                char rightChar = gene.charAt(right);
                windowFreq.put(rightChar, windowFreq.get(rightChar) + 1);

                while (allExcessSatisfied(windowFreq, excess)) {
                    minLen = Math.min(minLen, right - left + 1);
                    char leftChar = gene.charAt(left);
                    windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);
                    left++;
                }
            }

            return minLen;
        }

        private static boolean allExcessSatisfied(Map<Character, Integer> windowFreq, Map<Character, Integer> excess) {
            for (char ch : "ACGT".toCharArray()) {
                if (windowFreq.get(ch) < excess.get(ch)) {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            String gene = scanner.next();
            scanner.close();

            System.out.println(steadyGene(gene));
        }
    }


