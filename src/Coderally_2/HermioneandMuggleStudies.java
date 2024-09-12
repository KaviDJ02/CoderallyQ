package Coderally_2;


import java.io.*;
import java.util.stream.IntStream;

class HermioneandMuggleStudies {

    /*
     * Complete the 'divisors' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER x as parameter.
     */

    public static int divisors(int x) {
        // Count how many divisors of 'x' are divisible by 2
        int count = 0;
        for (int i = 1; i <= x; i++) {
            if (x % i == 0 && i % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read the number of test cases
        int y = Integer.parseInt(bufferedReader.readLine().trim());

        // Loop through each test case
        IntStream.range(0, y).forEach(yItr -> {
            try {
                // Read the integer X
                int x = Integer.parseInt(bufferedReader.readLine().trim());

                // Call the divisors function from HermioneandMuggleStudies class and store the result
                int result = HermioneandMuggleStudies.divisors(x);

                // Write the result to output
                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Close the readers and writers
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}


