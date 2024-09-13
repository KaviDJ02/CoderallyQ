package Coderally_3;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
class MinimumLossResult {



        /*
         * Complete the 'minimumLoss' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts LONG_INTEGER_ARRAY price as parameter.
         */

        public static long minimumLoss(List<Long> price) {

            TreeSet<Long> priceSet = new TreeSet<>();
            long min = Long.MAX_VALUE;
            for (int i = 0; i < price.size(); i++) {
                Long higher = priceSet.higher(price.get(i));
                if (higher != null) {
                    min = Math.min(higher - price.get(i), min);
                }
                priceSet.add(price.get(i));
            }

            return min;
        }

    }

    public class MinimumLoss {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Long> price = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

            long result = MinimumLossResult.minimumLoss(price);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

