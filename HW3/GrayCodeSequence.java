package HW3;
import java.util.*;

public class GrayCodeSequence {

    public static List<String> generateGrayCode(int n) {
        // Base case for recursion: if n is 0, return a list with a single zero
        if (n == 0) {
            return new ArrayList<>(Collections.singletonList("0"));
        }
        // Base case for recursion: if n is 1, return a list with zero and one
        if (n == 1) {
            return new ArrayList<>(Arrays.asList("0", "1"));
        }

        // Recursive call to generate the Gray code sequence for n-1
        List<String> prevGrayCode = generateGrayCode(n - 1);
        List<String> result = new ArrayList<>();

        // Prepend 0 to the first half of the sequence
        for (String code : prevGrayCode) {
            result.add("0" + code);
        }

        // Reverse the sequence for the second half and prepend 1
        Collections.reverse(prevGrayCode);
        for (String code : prevGrayCode) {
            result.add("1" + code);
        }

        return result;
    }

    public static List<String> simulateMusicians(List<String> grayCode) {
        // An array of musician names to simulate joining and fading
        String[] musicians = {"Elmer", "Dietz", "Crizz", "Berty", "Alfie"};

        // A list to keep track of the actions for each code transition
        List<String> actions = new ArrayList<>();

        // A set to keep track of which musicians are currently "on stage"
        Set<String> onStage = new HashSet<>();

        // Iterate through each code in the Gray code sequence
        for (int i = 0; i < grayCode.size(); i++) {
            StringBuilder action = new StringBuilder();
            String currentCode = grayCode.get(i);
            String prevCode = i > 0 ? grayCode.get(i - 1) : "00000";

            if (currentCode.equals("00000")) {
                actions.add("SILENT STAGE"); 
                continue;
            }
        
            for (int j = 0; j < currentCode.length(); j++) {
                if (currentCode.charAt(j) != prevCode.charAt(j)) {
                    if (currentCode.charAt(j) == '1') {
                        onStage.add(musicians[j]);
                        action.append(musicians[j]).append(" Joins, ");
                    } 
                    else {
                        onStage.remove(musicians[j]);
                        action.append(musicians[j]).append(" Fades, ");
                    }
                }
            }
        
            if (action.length() > 0) {
                action.setLength(action.length() - 2);
            }
            actions.add("On stage: " + String.join(", ", onStage) + ", " + action);
        }
        
        return actions;
    }
    public static void main(String[] args) {
        int n = 5; // The order of the Gray code to generate 32 subsets (2^5)
        List<String> grayCode = generateGrayCode(n);
        List<String> actions = simulateMusicians(grayCode);
    
        // Ensure this only prints once by checking the console or the output file
        System.out.println("Index\tGray Code\tAction\t\tPlayers Playing");
        for (int i = 0; i < grayCode.size(); i++) {
            System.out.println(i + "\t" + grayCode.get(i) + "\t\t" + actions.get(i));
        }
    }
}
