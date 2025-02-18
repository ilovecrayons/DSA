//package HashCompete;

/**
 * A HashWordSet object represents a set words using a simplified hash table as
 * as the internal data structure. The hash table uses separate chaining
 * (a linked list in each has bucket index) to resolve collision.
 * The hash table has a fixed number of buckets.
 */
public class HashWordSet {
    private final static int BUCKETS = 53;
    private Node[] elementData;
    private int size;
    
    /**
     * Normalizes the given word by eliminating special characters from
     * its beginning and ending. 
     * @param word - word to be normalized.
     * @return the normalized version of the word.
     */
    private static String normalize(String word) {
        String specialChars = " ~!@#$%^&*()_+`-={}[]|\\:\";'<>?,./â€œâ€�â„¢";
        int i = 0;
        while (i < word.length() && specialChars.indexOf(""+word.charAt(i)) != -1) {
            i++;
        }
        int j = word.length()-1;
        while(j > i && specialChars.indexOf(""+word.charAt(j)) != -1) {
            j--;
        }
        return word.substring(i, j+1).toUpperCase();
    }
    
    /**
     * Determines if the given word exists in the hash
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
     * @return a string with the textual representation of the hash.
     */
    public String toString() {
        int minLength = -1;
        int avgLength = -1;
        int maxLength = -1;
        double efficiencyFactor = 0;
        
        // TODO: calculate the minLength, avgLength and maxLength of the linked lists in the buckets
        // TODO: calculate the efficiencyFactor as the sum (bucketLength - avgLength)^2 / BUCKETS
        // TODO: constructs the resulting string containing all the calculated values.
        for(int i = 0; i < BUCKETS; i++) {
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
        
        for(int i = 0; i < BUCKETS; i++) {
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
     * @param word - the word for which the hash value is calculated.
     * @return the hash value, as a number in the range [0, 52]
     */
    private int hash(String word, int constant) {
        int hash = 0;
        int pow = 1;
        for (int i = word.length()-1; i >= 0; i--) {
            hash = (hash + (word.charAt(i) * pow)) % BUCKETS;
            pow = (pow * constant) % BUCKETS;
        }
        return hash;
        
    }
    
    public int getEfficiencyFactor(){
        int minLength = -1;
        int avgLength = -1;
        int maxLength = -1;
        double efficiencyFactor = 0;
        
        for(int i = 0; i < BUCKETS; i++) {
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
        
        for(int i = 0; i < BUCKETS; i++) {
            Node current = elementData[i];
            int length = 0;
            while (current != null) {
                length++;
                current = current.next;
            }
            efficiencyFactor += Math.pow(length - avgLength, 2);
        }
        efficiencyFactor /= BUCKETS;

        return (int)efficiencyFactor;
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

    
}