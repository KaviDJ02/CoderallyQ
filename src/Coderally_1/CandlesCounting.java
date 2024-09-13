package Coderally_1;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    static final int MOD = 1000000007;

    public static int candlesCounting(int k, List<List<Integer>> candles) {
        int n = candles.size();
        // dp[i][mask] = number of subsequences ending at candle i with color bitmask 'mask'
        int[][] dp = new int[n][1 << k];

        // Each candle alone can form a subsequence with its color
        for (int i = 0; i < n; i++) {
            int color = candles.get(i).get(1) - 1; // 0-indexed color
            dp[i][1 << color] = 1; // A single candle forms a subsequence with just its color
        }

        // Iterate through candles
        for (int i = 0; i < n; i++) {
            int h_i = candles.get(i).get(0);
            int c_i = candles.get(i).get(1) - 1;

            for (int j = 0; j < i; j++) {
                int h_j = candles.get(j).get(0);
                int c_j = candles.get(j).get(1) - 1;

                // Only extend strictly increasing subsequences
                if (h_i > h_j) {
                    for (int mask = 0; mask < (1 << k); mask++) {
                        int newMask = mask | (1 << c_i); // Add current candle's color to the bitmask
                        dp[i][newMask] = (dp[i][newMask] + dp[j][mask]) % MOD;
                    }
                }
            }
        }

        // Sum up subsequences that have all K colors
        int fullMask = (1 << k) - 1; // Full bitmask where all K colors are present
        int result = 0;
        for (int i = 0; i < n; i++) {
            result = (result + dp[i][fullMask]) % MOD;
        }

        return result;

    }

}

public class CandlesCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> candles = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                candles.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.candlesCounting(k, candles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

//Terminated due to timeout
