package stream.stepic.primitive_streams;

import java.util.Scanner;

class Isogram {

    /*
    Need to implement a method to check if the given word is an isogram
    that means that no letter of the alphabet occurs more than once in this word,
    consecutive or non-consecutive.
     */
    public static boolean isIsogram(String word) {
        return word.chars().distinct().count() == word.length();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        System.out.println(isIsogram(word));
    }
}