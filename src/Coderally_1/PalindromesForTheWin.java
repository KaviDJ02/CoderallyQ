package Coderally_1;

import java.util.Scanner;

public class PalindromesForTheWin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        StringBuilder stb = new StringBuilder(str);
        stb.reverse();

        if(stb.toString().equals(str)){
            System.out.println("yes");
        }else System.out.println("no");
    }
}
