package Coderally_2;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class CentralPerkMenuLocked {

    /*
     * Complete the 'coffeeHouse' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER a
     *  2. INTEGER_ARRAY arr
     */
    public static List<Integer> coffeeHouse(int a, List<Integer> arr) {
        // Create a map to store the index of each price
        Map<Integer, Integer> priceIndexMap = new HashMap<>();

        // Iterate through the list of prices
        for (int i = 0; i < arr.size(); i++) {
            int price = arr.get(i);
            int complement = a - price;

            // Check if the complement is already in the map
            if (priceIndexMap.containsKey(complement)) {
                // Return the indices of the two prices (adjusted for 1-based indexing)
                List<Integer> result = Arrays.asList(priceIndexMap.get(complement) + 1, i + 1);
                Collections.sort(result); // Ensure the result is in ascending order
                return result;
            }

            // Add the current price and its index to the map
            priceIndexMap.put(price, i);
        }

        // In case no solution is found, return an empty list
        return Collections.emptyList();
    }
}

public class CentralPerkMenulocked {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int v = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, v).forEach(vItr -> {
            try {
                int a = Integer.parseInt(bufferedReader.readLine().trim());
                int n = Integer.parseInt(bufferedReader.readLine().trim());
                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> result = CentralPerkMenuLocked.coffeeHouse(a, arr);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
