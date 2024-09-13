package Coderally_2;
import java.io.*;
import java.util.*;

public class SugarRush1 { public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] input = reader.readLine().split(" ");
    int n = Integer.parseInt(input[0]);
    int k = Integer.parseInt(input[1]);
    String[] sweetsStr = reader.readLine().split(" ");
    int[] sweets = Arrays.stream(sweetsStr).mapToInt(Integer::parseInt).toArray();

    int result = minOperationsToSweeten(n, k, sweets);
    System.out.println(result);
}

    public static int minOperationsToSweeten(int n, int k, int[] sweets) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int sweetness : sweets) {
            heap.add(sweetness);
        }

        int operations = 0;
        while (heap.size() > 1 && heap.peek() < k) {
            int leastSweet1 = heap.poll();
            int leastSweet2 = heap.poll();
            int newSweetness = leastSweet1 * leastSweet2;
            heap.add(newSweetness);
            operations++;
        }

        if (heap.peek() >= k) {
            return operations;
        } else {
            return -1;
        }
    }
}
