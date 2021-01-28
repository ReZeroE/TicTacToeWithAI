import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TTTSmartCPU {
    // field
    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
    static ArrayList<Integer> allPlayerPos = new ArrayList<Integer>();
    static ArrayList<Integer> allCPUPos = new ArrayList<Integer>();
    static int cpuMove;
    static int placeHold;
    static int randomizedVal;
    static int position; // AKA int playerMove

    static int controlVal;
    static int breakPoint;

    static ArrayList<Integer> AryListOfOne = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfTwo = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfThree = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfFour = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfFive = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfSix = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfSeven = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfEight = new ArrayList<Integer>();
    static ArrayList<Integer> AryListOfNine = new ArrayList<Integer>();

    public static void main(String[] args) {

        // for(int x = 1; x <= 30; x++) {
        arraySet();
        char[][] gameBoard = { { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ',
            ' ', ' ' }, { '-', '-', '-', '+', '-', '-', '-', '+', '-', '-',
                '-' }, { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ',
                    ' ' }, { '-', '-', '-', '+', '-', '-', '-', '+', '-', '-',
                        '-' }, { ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ',
                            ' ', ' ' } };

        // printGameBoard(gameBoard);

        while (true) {

            // CPU's move======================================================1
            Random rand = new Random();
            randomizedVal = rand.nextInt(9) + 1;
            cpuMove = randomizedVal;

            cpuBlockAlg();
            cpuWinCheckAlg();
            winningCPUAlg();

            while (playerPos.contains(cpuMove) || cpuPos.contains(cpuMove)) {
                System.out.println(
                    "Countermeasure fails. Value Randomized. (loop)");
                randomizedVal = rand.nextInt(9) + 1;
                cpuMove = randomizedVal;

                cpuBlockAlg();
                cpuWinCheckAlg();
                winningCPUAlg();

                breakPoint++;
                if (breakPoint == 10) {
                    System.out.println(
                        "Error. Program auto-termination activated.");
                    System.exit(0);
                }

            }
            // store all CPU positions into the field
            allCPUPos.add(cpuMove);
            System.out.println("all CPU positions: " + allCPUPos);

            placeX(gameBoard, cpuMove, "CPU");

            printGameBoard(gameBoard);
            // ================================================================1

            String result = checkWin();
            if (result.length() > 0) {
                System.out.println(result);
                clearAllVal();
                break;
            }

            // Player's
            // Move====================================================2
            playersTurn(gameBoard);
            // cpu2AsPlayer(gameBoard);
            // =================================================================2

            result = checkWin();
            if (result.length() > 0) {
                printGameBoard(gameBoard);
                System.out.println(result);
                clearAllVal();
                break;
            }
        }
// }
    }
    // -------------------------------------------------------

    // if (gameBoard[0][1] == ' '){
    //
    // }

    // -----------------------------------------------------------


    // Print game board
    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }


    // Places the player piece on the game board
    public static void placeX(char[][] gameBoard, int position, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPos.add(position);
        }
        else if (user.equals("CPU")) {
            symbol = 'O';
            cpuPos.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][1] = symbol;
                break;
            case 2:
                gameBoard[0][5] = symbol;
                break;
            case 3:
                gameBoard[0][9] = symbol;
                break;
            case 4:
                gameBoard[2][1] = symbol;
                break;
            case 5:
                gameBoard[2][5] = symbol;
                break;
            case 6:
                gameBoard[2][9] = symbol;
                break;
            case 7:
                gameBoard[4][1] = symbol;
                break;
            case 8:
                gameBoard[4][5] = symbol;
                break;
            case 9:
                gameBoard[4][9] = symbol;
                break;
            default:
                break;
        }
    }


    // Check who the winner is
    public static String checkWin() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List topCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List botCol = Arrays.asList(3, 6, 9);

        List lUpDia = Arrays.asList(1, 5, 9);
        List rUpDia = Arrays.asList(3, 5, 7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(topCol);
        winningConditions.add(midCol);
        winningConditions.add(botCol);
        winningConditions.add(lUpDia);
        winningConditions.add(rUpDia);

        for (List i : winningConditions) {
            if (playerPos.containsAll(i)) {
                return "Congratulations you won!!";
            }
            else if (cpuPos.containsAll(i)) {
                return "CPU One wins! Sorry! :(";
            }
            else if (playerPos.size() + cpuPos.size() == 9) {
                return "Tie!";
            }
        }

        return "";
    }


    // Player Move
    public static void playersTurn(char[][] gameBoard) {
        System.out.println("Please enter your placement: ");
        Scanner scanner = new Scanner(System.in);

        Exception excep = null;
        Exception excep2 = null;
        try {
            position = scanner.nextInt();
        }
        catch (InputMismatchException ex) {
            excep = ex;
            while (excep != null) {
                System.out.println(
                    "Input unrecognizable. Please re-input a correct value from 1~9!");
                scanner = new Scanner(System.in);
                try {
                    position = scanner.nextInt();
                    excep2 = null;
                }
                catch (InputMismatchException ex2) {
                    excep2 = ex2;
                }
                finally {
                    excep = excep2;
                }
            }
        }

        while (position > 9 || position < 0) {
            System.out.println("Please re-input a correct value from 1~9!");
            scanner = new Scanner(System.in);
            position = scanner.nextInt();
        }

        while (playerPos.contains(position) || allCPUPos.contains(position)) {
            printGameBoard(gameBoard);
            System.out.println(
                "Position taken! Please re-enter another positon!");
            position = scanner.nextInt();
        }

        // store all player position into the field
        allPlayerPos.add(position);
        System.out.println("all player positions: " + allPlayerPos);

        placeX(gameBoard, position, "player");
    }


    // CPU wincheck algorithms
    public static void cpuWinCheckAlg() {
        if (allCPUPos.contains(1) && allCPUPos.contains(2) && !allPlayerPos
            .contains(3) && !allCPUPos.contains(3)) {
            int placement = 3;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(4) && allCPUPos.contains(5) && !allPlayerPos
            .contains(6) && !allCPUPos.contains(6)) {
            int placement = 6;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(7) && allCPUPos.contains(8) && !allPlayerPos
            .contains(9) && !allCPUPos.contains(9)) {
            int placement = 9;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(2) && allCPUPos.contains(3) && !allPlayerPos
            .contains(1) && !allCPUPos.contains(1)) {
            int placement = 1;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(5) && allCPUPos.contains(6) && !allPlayerPos
            .contains(4) && !allCPUPos.contains(4)) {
            int placement = 4;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(9) && allCPUPos.contains(8) && !allPlayerPos
            .contains(7) && !allCPUPos.contains(7)) {
            int placement = 7;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(1) && allCPUPos.contains(4) && !allPlayerPos
            .contains(7) && !allCPUPos.contains(7)) {
            int placement = 7;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(2) && allCPUPos.contains(5) && !allPlayerPos
            .contains(8) && !allCPUPos.contains(8)) {
            int placement = 8;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(3) && allCPUPos.contains(6) && !allPlayerPos
            .contains(9) && !allCPUPos.contains(9)) {
            int placement = 9;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(7) && allCPUPos.contains(4) && !allPlayerPos
            .contains(1) && !allCPUPos.contains(1)) {
            int placement = 1;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(8) && allCPUPos.contains(5) && !allPlayerPos
            .contains(2) && !allCPUPos.contains(2)) {
            int placement = 2;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(9) && allCPUPos.contains(6) && !allPlayerPos
            .contains(3) && !allCPUPos.contains(3)) {
            int placement = 3;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(1) && allCPUPos.contains(5) && !allPlayerPos
            .contains(9) && !allCPUPos.contains(9)) {
            int placement = 9;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(9) && allCPUPos.contains(5) && !allPlayerPos
            .contains(1) && !allCPUPos.contains(1)) {
            int placement = 1;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(3) && allCPUPos.contains(5) && !allPlayerPos
            .contains(7) && !allCPUPos.contains(7)) {
            int placement = 7;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(7) && allCPUPos.contains(5) && !allPlayerPos
            .contains(3) && !allCPUPos.contains(3)) {
            int placement = 3;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(1) && allCPUPos.contains(3) && !allPlayerPos
            .contains(2) && !allCPUPos.contains(2)) {
            int placement = 2;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(4) && allCPUPos.contains(6) && !allPlayerPos
            .contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(7) && allCPUPos.contains(9) && !allPlayerPos
            .contains(8) && !allCPUPos.contains(8)) {
            int placement = 8;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(7) && allCPUPos.contains(1) && !allPlayerPos
            .contains(4) && !allCPUPos.contains(4)) {
            int placement = 4;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(8) && allCPUPos.contains(2) && !allPlayerPos
            .contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(9) && allCPUPos.contains(3) && !allPlayerPos
            .contains(6) && !allCPUPos.contains(6)) {
            int placement = 6;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(7) && allCPUPos.contains(3) && !allPlayerPos
            .contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else if (allCPUPos.contains(1) && allCPUPos.contains(9) && !allPlayerPos
            .contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else {
            //
        }
    }


    // CPU block algorithms
    public static void cpuBlockAlg() {

        if (allPlayerPos.contains(1) && allPlayerPos.contains(2)
            && !allPlayerPos.contains(3) && !allCPUPos.contains(3)) {
            int placement = 3;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(4) && allPlayerPos.contains(5)
            && !allPlayerPos.contains(6) && !allCPUPos.contains(6)) {
            int placement = 6;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(7) && allPlayerPos.contains(8)
            && !allPlayerPos.contains(9) && !allCPUPos.contains(9)) {
            int placement = 9;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(2) && allPlayerPos.contains(3)
            && !allPlayerPos.contains(1) && !allCPUPos.contains(1)) {
            int placement = 1;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(5) && allPlayerPos.contains(6)
            && !allPlayerPos.contains(4) && !allCPUPos.contains(4)) {
            int placement = 4;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(9) && allPlayerPos.contains(8)
            && !allPlayerPos.contains(7) && !allCPUPos.contains(7)) {
            int placement = 7;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(1) && allPlayerPos.contains(4)
            && !allPlayerPos.contains(7) && !allCPUPos.contains(7)) {
            int placement = 7;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(2) && allPlayerPos.contains(5)
            && !allPlayerPos.contains(8) && !allCPUPos.contains(8)) {
            int placement = 8;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(3) && allPlayerPos.contains(6)
            && !allPlayerPos.contains(9) && !allCPUPos.contains(9)) {
            int placement = 9;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(7) && allPlayerPos.contains(4)
            && !allPlayerPos.contains(1) && !allCPUPos.contains(1)) {
            int placement = 1;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(8) && allPlayerPos.contains(5)
            && !allPlayerPos.contains(2) && !allCPUPos.contains(2)) {
            int placement = 2;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(9) && allPlayerPos.contains(6)
            && !allPlayerPos.contains(3) && !allCPUPos.contains(3)) {
            int placement = 3;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(1) && allPlayerPos.contains(5)
            && !allPlayerPos.contains(9) && !allCPUPos.contains(9)) {
            int placement = 9;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(9) && allPlayerPos.contains(5)
            && !allPlayerPos.contains(1) && !allCPUPos.contains(1)) {
            int placement = 1;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(3) && allPlayerPos.contains(5)
            && !allPlayerPos.contains(7) && !allCPUPos.contains(7)) {
            int placement = 7;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(7) && allPlayerPos.contains(5)
            && !allPlayerPos.contains(3) && !allCPUPos.contains(3)) {
            int placement = 3;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(1) && allPlayerPos.contains(3)
            && !allPlayerPos.contains(2) && !allCPUPos.contains(2)) {
            int placement = 2;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(4) && allPlayerPos.contains(6)
            && !allPlayerPos.contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(7) && allPlayerPos.contains(9)
            && !allPlayerPos.contains(8) && !allCPUPos.contains(8)) {
            int placement = 8;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(7) && allPlayerPos.contains(1)
            && !allPlayerPos.contains(4) && !allCPUPos.contains(4)) {
            int placement = 4;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(8) && allPlayerPos.contains(2)
            && !allPlayerPos.contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(9) && allPlayerPos.contains(3)
            && !allPlayerPos.contains(6) && !allCPUPos.contains(6)) {
            int placement = 6;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(7) && allPlayerPos.contains(3)
            && !allPlayerPos.contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else if (allPlayerPos.contains(1) && allPlayerPos.contains(9)
            && !allPlayerPos.contains(5) && !allCPUPos.contains(5)) {
            int placement = 5;
            cpuMove = placement;
        }
        else {
            //
        }
    }


    // 100% win/tie algorithms (starting first required)
    public static void winningCPUAlg() {
        ArrayList<Integer> placementInt = new ArrayList<Integer>();
        placementInt.add(5);

        if (allPlayerPos.isEmpty()) {
            Random random = new Random();
            int plaHold = random.nextInt(4);
            // field
            placeHold = plaHold;

            if (plaHold == 0) {
                int cpuMoveHoldOne = 1;
                cpuMove = cpuMoveHoldOne;
            }
            else if (plaHold == 1) {
                int cpuMoveHoldOne = 3;
                cpuMove = cpuMoveHoldOne;
            }
            else if (plaHold == 2) {
                int cpuMoveHoldOne = 7;
                cpuMove = cpuMoveHoldOne;
            }
            else if (plaHold == 3) {
                int cpuMoveHoldOne = 9;
                cpuMove = cpuMoveHoldOne;
            }
        }
        else if (!allPlayerPos.equals(placementInt) && allPlayerPos
            .size() == 1) {
            // Second CPU move
            controlVal = 0;

            System.out.println("second step countermeasure activated");
            counterAllOther();
            counterNotFiveDiagonal();
            counterNotFiveNextTo();
            counterNotFivePlaTaken();
            cpuWinCheckAlg();
        }

        else if (allPlayerPos.equals(placementInt) && allPlayerPos
            .size() == 1) {
            // Second CPU move - ver.2
            controlVal = 1;

            System.out.println("second step - ver2 countermeasure activated");
            counterPlacementFive();
            cpuWinCheckAlg();
            if (cpuMove == randomizedVal) {
                System.out.println(
                    "Second step countermeasure fails. Value Randomized.");
            }
        }

        else if (allPlayerPos.size() == 2 && controlVal == 0) {
            // Third CPU move
            System.out.println("third step countermeasure activated");
            cornerID();
            cpuWinCheckAlg();
            if (cpuMove == randomizedVal) {
                System.out.println(
                    "Third step ountermeasure fails. Value Randomized.");
            }
        }
    }


    // Step two countermeasure if player's first move diagonal from CPU's
    public static void counterNotFiveDiagonal() {

        if (allCPUPos.equals(AryListOfOne) && allPlayerPos.equals(
            AryListOfSix)) {
            int cpuMoveHoldTwo = 3;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfOne) && allPlayerPos.equals(
            AryListOfEight)) {
            int cpuMoveHoldTwo = 7;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfThree) && allPlayerPos.equals(
            AryListOfFour)) {
            int cpuMoveHoldTwo = 1;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfThree) && allPlayerPos.equals(
            AryListOfEight)) {
            int cpuMoveHoldTwo = 9;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfSeven) && allPlayerPos.equals(
            AryListOfTwo)) {
            int cpuMoveHoldTwo = 1;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfSeven) && allPlayerPos.equals(
            AryListOfSix)) {
            int cpuMoveHoldTwo = 9;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfNine) && allPlayerPos.equals(
            AryListOfFour)) {
            int cpuMoveHoldTwo = 7;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfNine) && allPlayerPos.equals(
            AryListOfTwo)) {
            int cpuMoveHoldTwo = 3;
            cpuMove = cpuMoveHoldTwo;
        }
    }


    // Step two countermeasure if player's first move is next to CPU's first
    // move
    public static void counterNotFiveNextTo() {

        if (allCPUPos.equals(AryListOfOne) && allPlayerPos.equals(
            AryListOfTwo)) {
            int cpuMoveHoldTwo = 7;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfOne) && allPlayerPos.equals(
            AryListOfFour)) {
            int cpuMoveHoldTwo = 3;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfThree) && allPlayerPos.equals(
            AryListOfTwo)) {
            int cpuMoveHoldTwo = 9;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfThree) && allPlayerPos.equals(
            AryListOfSix)) {
            int cpuMoveHoldTwo = 1;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfSeven) && allPlayerPos.equals(
            AryListOfFour)) {
            int cpuMoveHoldTwo = 9;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfSeven) && allPlayerPos.equals(
            AryListOfEight)) {
            int cpuMoveHoldTwo = 1;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfNine) && allPlayerPos.equals(
            AryListOfEight)) {
            int cpuMoveHoldTwo = 3;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfNine) && allPlayerPos.equals(
            AryListOfSix)) {
            int cpuMoveHoldTwo = 7;
            cpuMove = cpuMoveHoldTwo;
        }
    }


    // step two countermeasure if player's first move coincides with CPU's
    public static void counterNotFivePlaTaken() {

        if (allCPUPos.equals(AryListOfOne) && allPlayerPos.equals(
            AryListOfThree)) {
            int cpuMoveHoldTwo = 7;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfOne) && allPlayerPos.equals(
            AryListOfSeven)) {
            int cpuMoveHoldTwo = 3;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfThree) && allPlayerPos.equals(
            AryListOfOne)) {
            int cpuMoveHoldTwo = 9;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfThree) && allPlayerPos.equals(
            AryListOfNine)) {
            int cpuMoveHoldTwo = 1;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfSeven) && allPlayerPos.equals(
            AryListOfOne)) {
            int cpuMoveHoldTwo = 9;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfSeven) && allPlayerPos.equals(
            AryListOfNine)) {
            int cpuMoveHoldTwo = 1;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfNine) && allPlayerPos.equals(
            AryListOfThree)) {
            int cpuMoveHoldTwo = 7;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfNine) && allPlayerPos.equals(
            AryListOfSeven)) {
            int cpuMoveHoldTwo = 3;
            cpuMove = cpuMoveHoldTwo;
        }
    }


    // step two countermeasure for all other possibilities
    public static void counterAllOther() {
        Random randomTwo = new Random();
        int plaHoldTwo = randomTwo.nextInt(2);

        if (placeHold == 0 && allPlayerPos.equals(AryListOfNine)) {
            if (plaHoldTwo == 0) {
                System.out.println("p1");
                int cpuMoveHoldTwo = 3;
                cpuMove = cpuMoveHoldTwo;
            }
            else {
                System.out.println("p2");
                int cpuMoveHoldTwo = 7;
                cpuMove = cpuMoveHoldTwo;
            }
        }
        else if (placeHold == 1 && allPlayerPos.equals(AryListOfSeven)) {
            if (plaHoldTwo == 0) {
                int cpuMoveHoldTwo = 1;
                cpuMove = cpuMoveHoldTwo;
            }
            else {
                int cpuMoveHoldTwo = 9;
                cpuMove = cpuMoveHoldTwo;
            }
        }
        else if (placeHold == 2 && allPlayerPos.equals(AryListOfThree)) {
            if (plaHoldTwo == 0) {
                int cpuMoveHoldTwo = 1;
                cpuMove = cpuMoveHoldTwo;
            }
            else {
                int cpuMoveHoldTwo = 9;
                cpuMove = cpuMoveHoldTwo;
            }
        }
        else if (placeHold == 3 && allPlayerPos.equals(AryListOfOne)) {
            if (plaHoldTwo == 0) {
                int cpuMoveHoldTwo = 7;
                cpuMove = cpuMoveHoldTwo;
            }
            else {
                int cpuMoveHoldTwo = 3;
                cpuMove = cpuMoveHoldTwo;
            }
        }
    }


    // step two counter measure if the player's first move is five
    public static void counterPlacementFive() {
        if (allCPUPos.equals(AryListOfOne)) {
            int cpuMoveHoldTwo = 9;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfThree)) {
            int cpuMoveHoldTwo = 7;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfSeven)) {
            int cpuMoveHoldTwo = 3;
            cpuMove = cpuMoveHoldTwo;
        }
        else if (allCPUPos.equals(AryListOfNine)) {
            int cpuMoveHoldTwo = 1;
            cpuMove = cpuMoveHoldTwo;
        }
    }


    // step three for CPU
    public static void cornerID() {
        if (allCPUPos.contains(1) && !allPlayerPos.contains(2) && !allPlayerPos
            .contains(3) && !allCPUPos.contains(2) && !allCPUPos.contains(3)) {
            int cpuMoveHoldThree = 3;
            cpuMove = cpuMoveHoldThree;
        }
        else if (allCPUPos.contains(1) && !allPlayerPos.contains(4)
            && !allPlayerPos.contains(7) && !allCPUPos.contains(4) && !allCPUPos
                .contains(7)) {
            int cpuMoveHoldThree = 7;
            cpuMove = cpuMoveHoldThree;
        }
        else if (allCPUPos.contains(3) && !allPlayerPos.contains(1)
            && !allPlayerPos.contains(2) && !allCPUPos.contains(1) && !allCPUPos
                .contains(2)) {
            int cpuMoveHoldThree = 1;
            cpuMove = cpuMoveHoldThree;
        }
        else if (allCPUPos.contains(3) && !allPlayerPos.contains(6)
            && !allPlayerPos.contains(9) && !allCPUPos.contains(6) && !allCPUPos
                .contains(9)) {
            int cpuMoveHoldThree = 9;
            cpuMove = cpuMoveHoldThree;
        }
        else if (allCPUPos.contains(7) && !allPlayerPos.contains(4)
            && !allPlayerPos.contains(1) && !allCPUPos.contains(4) && !allCPUPos
                .contains(1)) {
            int cpuMoveHoldThree = 1;
            cpuMove = cpuMoveHoldThree;
        }
        else if (allCPUPos.contains(7) && !allPlayerPos.contains(8)
            && !allPlayerPos.contains(9) && !allCPUPos.contains(8) && !allCPUPos
                .contains(9)) {
            int cpuMoveHoldThree = 9;
            cpuMove = cpuMoveHoldThree;
        }
        else if (allCPUPos.contains(9) && !allPlayerPos.contains(3)
            && !allPlayerPos.contains(6) && !allCPUPos.contains(3) && !allCPUPos
                .contains(6)) {
            int cpuMoveHoldThree = 3;
            cpuMove = cpuMoveHoldThree;
        }
        else if (allCPUPos.contains(9) && !allPlayerPos.contains(8)
            && !allPlayerPos.contains(7) && !allCPUPos.contains(8) && !allCPUPos
                .contains(7)) {
            int cpuMoveHoldThree = 7;
            cpuMove = cpuMoveHoldThree;
        }
    }


    // Array Storage
    public static void arraySet() {
        AryListOfOne.add(1);
        AryListOfTwo.add(2);
        AryListOfThree.add(3);
        AryListOfFour.add(4);
        AryListOfFive.add(5);
        AryListOfSix.add(6);
        AryListOfSeven.add(7);
        AryListOfEight.add(8);
        AryListOfNine.add(9);
    }


    // CPU-2 as player
    public static void cpu2AsPlayer(char[][] gameBoard) {

        Random rand = new Random();
        position = rand.nextInt(9) + 1;

        while (playerPos.contains(position) || allCPUPos.contains(position)) {
            position = rand.nextInt(9) + 1;
        }

        // store all player position into the field
        allPlayerPos.add(position);
        System.out.println("all player positions: " + allPlayerPos);

        placeX(gameBoard, position, "player");

        printGameBoard(gameBoard);
    }


    // clear all values
    public static void clearAllVal() {
        allCPUPos.clear();
        allPlayerPos.clear();
        playerPos.clear();
        cpuPos.clear();

        cpuMove = 100;
        placeHold = 100;
        randomizedVal = 100;
        position = 100;
        controlVal = 100;
        breakPoint = 100;
    }
}
