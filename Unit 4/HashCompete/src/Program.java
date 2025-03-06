//package HashCompete;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    private static final String _bookFile = "pride_and_prejudice.txt";
    private static final String _bookFile2 = "frankenstein.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("Hello to the Hashing competition!");
        int lowestEfficiencyFactor = Integer.MAX_VALUE;
        int lowestEfficiencyNumber = 0;

        File file = new File(_bookFile);
        HashWordSet hash = new HashWordSet();
        Scanner reader = new Scanner(file);
        while (reader.hasNext()) {
            hash.add(reader.next(), 290);
        }
        reader.close();


        // for (int i = 0; i < 100000; i++) {
        //     HashWordSet hash = new HashWordSet();
        //     Scanner reader = new Scanner(file);
        //     while (reader.hasNext()) {
        //         hash.add(reader.next(), i);
        //     }
        //     if (hash.getEfficiencyFactor() < lowestEfficiencyFactor) {
        //         lowestEfficiencyFactor = hash.getEfficiencyFactor();
        //         lowestEfficiencyNumber = i;
        //     }
        //     System.out.println("Efficiency factor for number " + i + " is " +
        //             hash.getEfficiencyFactor() + ", lowest is " + lowestEfficiencyFactor + " with number "
        //             + lowestEfficiencyNumber);
        //     reader.close();

        // }

        // hash.printSortedHash();

        // int smallestEfficiencyFactor = Integer.MAX_VALUE;
        // int smallestEfficiencyPrime = 0;
        // for (int i = 0; i < 100000; i++) {
        // HashWordSet hash = new HashWordSet();
        // if (true) {
        // Scanner reader = new Scanner(file);
        // while (reader.hasNext()) {
        // hash.add(reader.next(), i);
        // }
        // reader.close();
        // if (hash.getEfficiencyFactor() < smallestEfficiencyFactor) {
        // smallestEfficiencyFactor = hash.getEfficiencyFactor();
        // smallestEfficiencyPrime = i;
        // }
        // System.out.println("Efficiency factor for prime " + i + " is " +
        // hash.getEfficiencyFactor());
        // }

        // }
        // System.out.println("The smallest efficiency factor is " +
        // smallestEfficiencyFactor + " with prime "
        // + smallestEfficiencyPrime);

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
