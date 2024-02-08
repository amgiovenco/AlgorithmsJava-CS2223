package HW3;

public class PalindromeCheck {
    private static String cleanString(String str) {
        return str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
    
    // Recursive function to check if a string is a palindrome
    private static boolean isPalindromeRecursive(String str, int left, int right) {
        // Base case: if left >= right, we've checked all the characters
        if (left >= right) {
            return true;
        }
        // Check if the characters at left and right are equal
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }
        // Move towards the middle of the string
        return isPalindromeRecursive(str, left + 1, right - 1);
    }

    // The public method that will be called to check for palindrome
    public static boolean isPalindrome(String str) {
        str = cleanString(str);
        return isPalindromeRecursive(str, 0, str.length() - 1);
    }

    public static void main(String[] args) {
        // Test cases
        String[] testStrings = {
            "never odd or even",
            "Able was I ere I saw Elba",
            "A man, a plan, a canal: Panama!",
            "Hi, I'm Ally."
        };

        for (String test : testStrings) {
            System.out.println("Is \"" + test + "\" a palindrome? " + isPalindrome(test));
        }
    }
}

