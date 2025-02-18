//package HashCompete;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    private static final String _bookFile = "pride_and_prejudice.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello to the Hashing competition!");

        // read the book word by word and add each word to a HashWordSet

        File file = new File(_bookFile);
        HashWordSet hash = new HashWordSet();
        Scanner reader = new Scanner(file);
        while (reader.hasNext()) {
            hash.add(reader.next(), 41);
        }
        reader.close();
        //generate all primes within 1k for i.
        // int smallestEfficiencyFactor = Integer.MAX_VALUE;
        // int smallestEfficiencyPrime = 0;
        // for (int i = 0; i < 100000; i++) {
        //     HashWordSet hash = new HashWordSet();
        //     if (isPrime(i)) {
        //         Scanner reader = new Scanner(file);
        //         while (reader.hasNext()) {
        //             hash.add(reader.next(), i);
        //         }
        //         reader.close();
        //         if (hash.getEfficiencyFactor() < smallestEfficiencyFactor) {
        //             smallestEfficiencyFactor = hash.getEfficiencyFactor();
        //             smallestEfficiencyPrime = i;
        //         }
        //         System.out.println("Efficiency factor for prime " + i + " is " + hash.getEfficiencyFactor());
        //     }

        // }
        // System.out.println("The smallest efficiency factor is " + smallestEfficiencyFactor + " with prime "
        //         + smallestEfficiencyPrime);

        // print the hash object
        System.out.println(hash);

        System.out.println("Goodbye!");
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
