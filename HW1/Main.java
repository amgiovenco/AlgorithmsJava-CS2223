import java.util.Scanner;

public class Main {
    public static int greenMarker = 3;
    public static int yellowMarker = 7;
    public static int orangeMarker = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Who goes first? (1: User, 2: Computer): ");
        int turn = sc.nextInt();
        while (true) {
            if (turn == 1) {
                userMove(sc);
                turn = 2;
            } 
            else {
                computerMove();
                turn = 1;
            }
            if (gameOver()) {
                System.out.println(turn == 1 ? "Computer Wins!" : "User Wins!");
                break;
            }
        }
        sc.close();
    }

    private static void userMove(Scanner sc) {
        System.out.println("Choose a color (1: Green, 2: Yellow, 3: Orange): ");
        int choice = sc.nextInt();
        System.out.println("How many markers do you want to remove?");
        int marker = sc.nextInt();
        removeMarker(choice, marker);
    }

    public static void computerMove() {
        int xorResult = greenMarker ^ yellowMarker ^ orangeMarker;
        
        // If in a zero-position, make a random move
        if (xorResult == 0) {
            makeRandomMove();
            return;
        }
    
        // Otherwise, try to leave a zero-position for opponent
        if ((greenMarker ^ xorResult) < greenMarker) {
            removeMarker(1, greenMarker - (greenMarker ^ xorResult));
            return;
        }
    
        if ((yellowMarker ^ xorResult) < yellowMarker) {
            removeMarker(2, yellowMarker - (yellowMarker ^ xorResult));
            return;
        }
    
        if ((orangeMarker ^ xorResult) < orangeMarker) {
            removeMarker(3, orangeMarker - (orangeMarker ^ xorResult));
            return;
        }
    }

    private static void makeRandomMove() {
        int choice;
        int marker;
        do {
            choice = (int) (Math.random() * 3) + 1;
            switch (choice) {
                case 1:
                    marker = (int) (Math.random() * greenMarker) + 1;
                    break;
                case 2:
                    marker = (int) (Math.random() * yellowMarker) + 1;
                    break;
                default:
                    marker = (int) (Math.random() * orangeMarker) + 1;
                    break;
            }
        } 
        while (marker == 0);
        removeMarker(choice, marker);
    }

    public static void removeMarker(int choice, int marker) {
        switch (choice) {
            case 1:
                greenMarker -= marker;
                break;
            case 2:
                yellowMarker -= marker;
                break;
            case 3:
                orangeMarker -= marker;
                break;
        }
    }

    private static boolean gameOver() {
        return greenMarker <= 0 && yellowMarker <= 0 && orangeMarker <= 0;
    }
}
