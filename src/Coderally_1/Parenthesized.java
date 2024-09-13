package Coderally_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Parenthesized {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String expression = br.readLine().trim();

            if (isProperlyParenthesized(expression)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        private static boolean isProperlyParenthesized(String expression) {
            Stack<Character> stack = new Stack<>();

            for (char ch : expression.toCharArray()) {
                if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
            }

            return stack.isEmpty();
        }
    }

