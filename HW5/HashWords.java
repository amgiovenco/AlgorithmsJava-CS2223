import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class HashWords {

    private static final int C = 123;
    private static final int M = 997;
    private static String[] hashTable = new String[M];

    public static void main(String[] args) {
        String pathToFile = "C://Users//plumm//OneDrive//Desktop//Classes//2nd Year//B Term (2023)//Algorithms//hw5//DeclarationOfIndependence.txt"; //File path to declaration txt file
        Charset charset = Charset.forName("ISO-8859-1");

        try {
            Set<String> uniqueWords = new HashSet<>();
            for (String line : Files.readAllLines(Paths.get(pathToFile), charset)) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty() && uniqueWords.add(word)) {
                        int hashValue = hashWord(word);
                        int hashAddress = hashValue;

                        while (hashTable[hashAddress] != null) {
                            hashAddress = (hashAddress + 1) % M;
                        }

                        hashTable[hashAddress] = word;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < M; i++) {
            if (hashTable[i] != null) {
                System.out.println("Hash Address: " + i + ", Hashed Word: " + hashTable[i] + ", Hash Value of Word: " + hashWord(hashTable[i]));
            }
        }

        System.out.println("\n Analysis ");
        int nonEmptyAddresses = countNonEmptyAddresses();
        System.out.println("a. Number of non-empty addresses: " + nonEmptyAddresses);
        System.out.println("   Load factor: " + (double) nonEmptyAddresses / M);

        int[] longestEmptyAreaInfo = findLongestEmptyArea();
        System.out.println("b. Longest empty area length: " + longestEmptyAreaInfo[1] + " starting at index " + longestEmptyAreaInfo[0]);

        int[] longestClusterInfo = findLongestCluster();
        System.out.println("c. Longest cluster length: " + longestClusterInfo[1] + " starting at index " + longestClusterInfo[0]);

        int[] hashValueWithMostWordsInfo = findHashValueWithMostWords();
        System.out.println("d. Hash value with most words: " + hashValueWithMostWordsInfo[0] + ", Number of words: " + hashValueWithMostWordsInfo[1]);

        String[] farthestWordInfo = findFarthestWordFromHash();
        System.out.println("e. Word farthest from its hash value: " + farthestWordInfo[0] + ", Distance: " + farthestWordInfo[1]);
    }

    private static int hashWord(String word) {
        int h = 0;
        for (int i = 0; i < word.length(); i++) {
            h = ((h * C) + word.charAt(i)) % M;
        }
        return h;
    }

    private static int countNonEmptyAddresses() {
        int count = 0;
        for (String word : hashTable) {
            if (word != null) {
                count++;
            }
        }
        return count;
    }

    private static int[] findLongestEmptyArea() {
        int maxLength = 0;
        int currentLength = 0;
        int startIndex = -1;
        int maxStartIndex = -1;

        for (int i = 0; i < M; i++) {
            if (hashTable[i] == null) {
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStartIndex = startIndex >= 0 ? startIndex : i;
                }
            } else {
                startIndex = -1;
                currentLength = 0;
            }
        }
        return new int[]{maxStartIndex, maxLength};
    }

    private static int[] findLongestCluster() {
        int maxLength = 0;
        int currentLength = 0;
        int startIndex = -1;
        int maxStartIndex = -1;

        for (int i = 0; i < M; i++) {
            if (hashTable[i] != null) {
                if (currentLength == 0) startIndex = i;
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStartIndex = startIndex;
                }
            } else {
                currentLength = 0;
            }
        }
        return new int[]{maxStartIndex, maxLength};
    }

    private static int[] findHashValueWithMostWords() {
        int[] hashValueCounts = new int[M];
        for (String word : hashTable) {
            if (word != null) {
                int hashValue = hashWord(word);
                hashValueCounts[hashValue]++;
            }
        }

        int maxCount = 0;
        int hashValueWithMostWords = -1;
        for (int i = 0; i < M; i++) {
            if (hashValueCounts[i] > maxCount) {
                maxCount = hashValueCounts[i];
                hashValueWithMostWords = i;
            }
        }
        return new int[]{hashValueWithMostWords, maxCount};
    }

    // Refactored method to find the farthest word from its hash value without using Object[]
    private static String[] findFarthestWordFromHash() {
        String farthestWord = null;
        int maxDistance = -1;

        for (int i = 0; i < M; i++) {
            if (hashTable[i] != null) {
                int actualHashValue = hashWord(hashTable[i]);
                int distance = Math.abs(i - actualHashValue);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    farthestWord = hashTable[i];
                }
            }
        }
        return new String[]{farthestWord, Integer.toString(maxDistance)};
    }
}
