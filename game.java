import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    static String[] boardPositions = {" ", " ", " ", " ", " ", " ", " ", " ", " "};
    static List<Integer> chosenPositions = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static boolean gameContinue = true;
    static String player1Symbol;
    static String player2Symbol;

    public static void main(String[] args) {
        boardUpdate();
        player1Symbol = symbolChoice();
        player2Symbol = player1Symbol.equals("X") ? "O" : "X";
        
        while (gameContinue) {
            int position = personMove(1, player1Symbol);
            blockFill(player1Symbol, position);
            if (winCheck()) {
                if (!continuePlay()) break;
            } else {
                position = personMove(2, player2Symbol);
                blockFill(player2Symbol, position);
                winCheck();
            }
        }
    }

    public static void boardUpdate() {
        System.out.println("Welcome to the Tic Tac Toe game.");
        System.out.println("This is your game board.");
        System.out.println("-------------");
        System.out.println("| " + boardPositions[0] + " | " + boardPositions[1] + " | " + boardPositions[2] + " |");
        System.out.println("-------------");
        System.out.println("| " + boardPositions[3] + " | " + boardPositions[4] + " | " + boardPositions[5] + " |");
        System.out.println("-------------");
        System.out.println("| " + boardPositions[6] + " | " + boardPositions[7] + " | " + boardPositions[8] + " |");
        System.out.println("-------------");
    }

    public static String symbolChoice() {
        System.out.println("Player 1, choose the symbol you would like to use in the game (X or O): ");
        String choice = "";
        while (!choice.equals("X") && !choice.equals("O")) {
            System.out.print("Enter the choice of your symbol: ");
            choice = scanner.nextLine().toUpperCase();
            if (!choice.equals("X") && !choice.equals("O")) {
                System.out.println("Sorry, but the choice of symbol is not available.");
            }
        }
        System.out.println("Player 1 has chosen " + choice + " as a symbol.");
        System.out.println("Player 2 will have " + (choice.equals("X") ? "O" : "X") + " as the symbol.");
        return choice;
    }

    public static int personMove(int player, String symbol) {
        int gamePosition = -1;
        while (gamePosition < 1 || gamePosition > 9 || chosenPositions.contains(gamePosition)) {
            System.out.print("Player " + player + " (" + symbol + "), enter the position to fill the symbol (1-9): ");
            try {
                gamePosition = Integer.parseInt(scanner.nextLine());
                if (gamePosition < 1 || gamePosition > 9 || chosenPositions.contains(gamePosition)) {
                    System.out.println("Either your choice is invalid or it is already taken.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
        }
        chosenPositions.add(gamePosition);
        return gamePosition;
    }

    public static void blockFill(String symbol, int position) {
        boardPositions[position - 1] = symbol;
        boardUpdate();
    }

    public static boolean continuePlay() {
        System.out.print("Do you want to play again (Y or N)? ");
        String booleanChoice = scanner.nextLine().toUpperCase();
        while (!booleanChoice.equals("Y") && !booleanChoice.equals("N")) {
            System.out.print("Wrong input. Do you want to play again (Y or N)? ");
            booleanChoice = scanner.nextLine().toUpperCase();
        }
        if (booleanChoice.equals("Y")) {
            boardPositions = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
            chosenPositions.clear();
            boardUpdate();
            player1Symbol = symbolChoice();
            player2Symbol = player1Symbol.equals("X") ? "O" : "X";
            return true;
        } else {
            gameContinue = false;
            return false;
        }
    }

    public static boolean winCheck() {
        String[][] winConditions = {
            {boardPositions[0], boardPositions[1], boardPositions[2]},
            {boardPositions[3], boardPositions[4], boardPositions[5]},
            {boardPositions[6], boardPositions[7], boardPositions[8]},
            {boardPositions[0], boardPositions[3], boardPositions[6]},
            {boardPositions[1], boardPositions[4], boardPositions[7]},
            {boardPositions[2], boardPositions[5], boardPositions[8]},
            {boardPositions[0], boardPositions[4], boardPositions[8]},
            {boardPositions[2], boardPositions[4], boardPositions[6]}
        };
        
        for (String[] condition : winConditions) {
            if (condition[0].equals(condition[1]) && condition[1].equals(condition[2]) && !condition[0].equals(" ")) {
                System.out.println("Game has been won by " + condition[0]);
                return true;
            }
        }
        return false;
    }
}

