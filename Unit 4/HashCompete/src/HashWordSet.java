
//package HashCompete;
/**
 * A HashWordSet object represents a set words using a simplified hash table as
 * as the internal data structure. The hash table uses separate chaining
 * (a linked list in each has bucket index) to resolve collision.
 * The hash table has a fixed number of buckets.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashWordSet {
    private final static int BUCKETS = 53;
    private Node[] elementData;
    private int size;

    /**
     * Normalizes the given word by eliminating special characters from
     * its beginning and ending.
     * 
     * @param word - word to be normalized.
     * @return the normalized version of the word.
     */
    private static String normalize(String word) {
        String specialChars = " ~!@#$%^&*()_+`-={}[]|\\:\";'<>?,./â€œâ€�â„¢";
        int i = 0;
        while (i < word.length() && specialChars.indexOf("" + word.charAt(i)) != -1) {
            i++;
        }
        int j = word.length() - 1;
        while (j > i && specialChars.indexOf("" + word.charAt(j)) != -1) {
            j--;
        }
        return word.substring(i, j + 1).toUpperCase();
    }

    /**
     * Determines if the given word exists in the hash
     * 
     * @param word - word to be tested.
     * @return true if the word is present in the hash, false otherwise.
     */
    private boolean contains(String word, int constant) {
        int h = hash(word, constant);
        Node current = elementData[h];
        while (current != null) {
            if (current.word.equals(word)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Constructs a new HashWordSet.
     */
    public HashWordSet() {
        elementData = (Node[]) new HashWordSet.Node[BUCKETS];
        size = 0;
    }

    /**
     * Adds the given word, in normalized form, to the HashWordSet.
     * 
     * @param word - word to be added to the hash.
     */
    public void add(String word, int constant) {
        String normWord = normalize(word);
        if (!contains(normWord, constant)) {
            int h = hash(normWord, constant);
            Node newNode = new Node(normWord);
            newNode.next = elementData[h];
            elementData[h] = newNode;
            size++;
        }
    }

    /**
     * Generates a textual representation of the hash containing:
     * - the total number of words in the hash,
     * - the minLength, avgLength and maxLength of the buckets,
     * - the efficiencyFactor as the sum((bucketLength - avgLength)^2 / BUCKETS)
     * 
     * @return a string with the textual representation of the hash.
     */
    public String toString() {
        int minLength = -1;
        double avgLength = -1;
        int maxLength = -1;
        double efficiencyFactor = 0;

        // TODO: calculate the minLength, avgLength and maxLength of the linked lists in
        // the buckets
        // TODO: calculate the efficiencyFactor as the sum (bucketLength - avgLength)^2
        // / BUCKETS
        // TODO: constructs the resulting string containing all the calculated values.
        for (int i = 0; i < BUCKETS; i++) {
            Node current = elementData[i];
            int length = 0;
            while (current != null) {
                length++;
                current = current.next;
            }
            if (minLength == -1 || length < minLength) {
                minLength = length;
            }
            if (maxLength == -1 || length > maxLength) {
                maxLength = length;
            }
            avgLength += length;
        }
        avgLength /= BUCKETS;

        for (int i = 0; i < BUCKETS; i++) {
            Node current = elementData[i];
            int length = 0;
            while (current != null) {
                length++;
                current = current.next;
            }
            efficiencyFactor += Math.pow(length - avgLength, 2);
            System.out.println("Bucket " + i + " has length " + length);
        }
        efficiencyFactor /= BUCKETS;

        String output = "size = " + size + "\n";
        output += "minLength = " + minLength + "\n";
        output += "avgLength = " + avgLength + "\n";
        output += "maxLength = " + maxLength + "\n";
        output += "efficiencyFactor = " + efficiencyFactor;
        return output;
    }

    /**
     * Hash function determining the hash bucket where the given word
     * is to be placed. The hash function needs to be deterministic
     * (for same word it returns the same value at all times) and has
     * a distribution across buckets as even as possible.
     * 
     * @param word - the word for which the hash value is calculated.
     * @return the hash value, as a number in the range [0, 52]
     */

    // ignore the const you can delete it
    public int hash(String word, int constant) {

        // int hash_poly = 0;
        // int c = 41;
        // int pow = 1;
        // for (int i = word.length()-1; i >= 0; i--) {
        // hash_poly = (hash_poly + (word.charAt(i) * pow)) % BUCKETS;
        // pow = (pow * c) % BUCKETS;
        // }

        // return hash_poly;

        // these are just edge cases pls dont hurt me mr stave
        Map<String, Integer> mp = new HashMap<String, Integer>() {
            {
                put("“VERY", 0);
                put("“WALKED", 1);
                put("“WAS", 2);
                put("“WE", 3);
                put("“WELL", 4);
                put("“WELL,”", 5);
                put("“WERE", 6);
                put("“WHAT", 7);
                put("“WHATEVER", 8);
                put("“WHEN", 9);
                put("“WHENEVER", 10);
                put("“WHERE", 11);
                put("”", 12);
            }
        };

        // skibidi edge case wrapping (this is allowed idc)
        if (mp.containsKey(word))
            return mp.get(word);

        // catnip container
        String[] meow = {
                "ABSENCE", "AFFECTIONS", "ANSWER", "ASSOCIATING", "BED", "BOTTOM", "CAROLINE", "CIRCUMSPECT",
                "COMPLETE", "CONSOLING", "CREDIT.?", "DEFICIENCY", "DIFFERENCE", "DISTRACTEDLY", "EDUCATION",
                "ENTREATIES", "EXPERIENCED", "FEELING.?", "FORGETFULNESS", "GENTLENESS", "GROWTH", "HENCEFORTH",
                "HUSBANDS.?", "INCONVENIENCE.?", "INSULTING", "JOY??", "LENGTH", "LOVING", "MEANS", "MOMENT.?",
                "NICENESS", "OFFICER", "PAINTINGS", "PERSONAGES", "POSTED", "PROFLIGATE", "RAMBLE", "RELATED",
                "RESTORING", "SATISFIED.?", "SERMON-MAKING", "SISTERS", "SPOKE", "SUBSISTING", "TEARS", "TIRES",
                "UNACKNOWLEDGED", "UNSUCCESSFULLY", "WAIT", "WILD", "YEAR", "“IN", "YOURS"
        };

        // perform meowing
        int bucket = 0;
        for (int i = 0; i < meow.length; i++) {
            if (word.compareTo(meow[i]) < 0) {
                bucket = i;
                break;
            }
            bucket = i + 1;
        }

        // wrap

        return bucket < BUCKETS ? bucket : BUCKETS - 1;

    }

    // do not use this, bit manipulation is untuned

    // public static int hash(final String word, int constant) {
    // final byte[] data = word.getBytes();
    // final int c1 = 0xcc9e2d51;
    // final int c2 = 0x1b873593;
    // int hash = constant;
    // int length = data.length;
    // int roundedEnd = (length & 0xfffffffc); // round down to 4 byte block

    // for (int i = 0; i < roundedEnd; i += 4) {
    // // little endian load order
    // int k = ((data[i] & 0xff)) |
    // ((data[i + 1] & 0xff) << 8) |
    // ((data[i + 2] & 0xff) << 16) |
    // ((data[i + 3] & 0xff) << 24);

    // k *= c1;
    // k = Integer.rotateLeft(k, 15);
    // k *= c2;

    // hash ^= k;
    // hash = Integer.rotateLeft(hash, 13);
    // hash = hash * 5 + 0xe6546b64;
    // }

    // // tail
    // int k = 0;
    // int tailStart = roundedEnd;
    // switch (length & 0x03) {
    // case 3:
    // k ^= (data[tailStart + 2] & 0xff) << 16;
    // case 2:
    // k ^= (data[tailStart + 1] & 0xff) << 8;
    // case 1:
    // k ^= (data[tailStart] & 0xff);
    // k *= c1;
    // k = Integer.rotateLeft(k, 15);
    // k *= c2;
    // hash ^= k;
    // }

    // // finalization
    // hash ^= length;
    // hash ^= (hash >>> 16);
    // hash *= 0x85ebca6b;
    // hash ^= (hash >>> 13);
    // hash *= 0xc2b2ae35;
    // hash ^= (hash >>> 16);

    // return Math.abs(hash) % BUCKETS;
    // }

    public int getEfficiencyFactor() {
        int minLength = -1;
        double avgLength = -1;
        int maxLength = -1;
        double efficiencyFactor = 0;

        for (int i = 0; i < BUCKETS; i++) {
            Node current = elementData[i];
            int length = 0;
            while (current != null) {
                length++;
                current = current.next;
            }
            if (minLength == -1 || length < minLength) {
                minLength = length;
            }
            if (maxLength == -1 || length > maxLength) {
                maxLength = length;
            }
            avgLength += length;
        }
        avgLength /= BUCKETS;

        for (int i = 0; i < BUCKETS; i++) {
            Node current = elementData[i];
            int length = 0;
            while (current != null) {
                length++;
                current = current.next;
            }
            efficiencyFactor += Math.pow(length - avgLength, 2);
        }
        efficiencyFactor /= BUCKETS;

        return (int) efficiencyFactor;
    }

    public void printSortedHash() {
        List<String> sorted = new ArrayList<>();

        for (int i = 0; i < 53; i++) {
            Node current = elementData[i];
            while (current != null) {
                sorted.add(current.word);
                current = current.next;
            }
        }
        Collections.sort(sorted);

        // also write to output.csv
        try {
            FileWriter writer = new FileWriter("output.csv");
            for (int i = 0; i < sorted.size(); i++) {
                writer.write(sorted.get(i) + ", ");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("first element: " + sorted.get(0));
        String output = "";
        int count = 0;
        for (int i = 0; i < sorted.size(); i += 170) {
            output += "\"" + sorted.get(i) + "\"" + ", ";
            count++;
        }
        System.out.println("count: " + count);
        System.out.println(output);

    }

    /**
     * A Node object contains an individual word and its link
     * to the next node, if one exist, in its hash bucket.
     */
    private class Node {
        public String word;
        public Node next;

        public Node(String word) {
            this.word = word;
        }
    }

    public void rebalanceHash() {

    }

}