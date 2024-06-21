package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] inputArray = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', };
        int counter = 0;

        boolean playing = true;

        printField(inputArray);

        do {
            String location = sc.nextLine();

            String row = location.substring(0, 1);
            String column = location.substring(2, 3);

            int rowInt;
            int columnInt;

            try {
                rowInt = Integer.parseInt(row);
                columnInt = Integer.parseInt(column);
            } catch (Exception ex) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (rowInt < 0 || rowInt > 3 || columnInt < 0 || columnInt > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            rowInt--;
            columnInt--;

            if (inputArray[rowInt * 3 + columnInt] != 'X' && inputArray[rowInt * 3 + columnInt] != 'O') {
                if (counter % 2 == 0) {
                    inputArray[rowInt * 3 + columnInt] = 'X';
                } else {
                    inputArray[rowInt * 3 + columnInt] = 'O';
                }
                counter++;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            printField(inputArray);

            playing = check(inputArray);;

        } while (playing);

    }

    private static boolean check(char[] inputArray) {
        int xCount = 0, oCount = 0;
        String result = "";
        boolean xHasLine = false, oHasLine = false;
        boolean hasEmptySpace = false;
        boolean keepPlaying = true;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (inputArray[x * 3 + y] == 'X') {
                    xCount++;
                }
                if (inputArray[x * 3 + y] == 'O') {
                    oCount++;
                }
                if (inputArray[x * 3 + y] != 'X' && inputArray[x * 3 + y] != 'O') {
                    hasEmptySpace = true;
                }
            }
        }

        if ((inputArray[0] == 'X' && inputArray[1] == 'X' && inputArray[2] == 'X')
                || (inputArray[3] == 'X' && inputArray[4] == 'X' && inputArray[5] == 'X')
                || (inputArray[6] == 'X' && inputArray[7] == 'X' && inputArray[8] == 'X')) {
            xHasLine = true;
        }

        if ((inputArray[0] == 'O' && inputArray[1] == 'O' && inputArray[2] == 'O')
                || (inputArray[3] == 'O' && inputArray[4] == 'O' && inputArray[5] == 'O')
                || (inputArray[6] == 'O' && inputArray[7] == 'O' && inputArray[8] == 'O')) {
            oHasLine = true;
        }


        if ((inputArray[0] == 'X' && inputArray[3] == 'X' && inputArray[6] == 'X')
                || (inputArray[1] == 'X' && inputArray[4] == 'X' && inputArray[7] == 'X')
                || (inputArray[2] == 'X' && inputArray[5] == 'X' && inputArray[8] == 'X')) {
            xHasLine = true;
        }

        if ((inputArray[0] == 'O' && inputArray[3] == 'O' && inputArray[6] == 'O')
                || (inputArray[1] == 'O' && inputArray[4] == 'O' && inputArray[7] == 'O')
                || (inputArray[2] == 'O' && inputArray[5] == 'O' && inputArray[8] == 'O')) {
            oHasLine = true;
        }


        if ((inputArray[0] == 'X' && inputArray[4] == 'X' && inputArray[8] == 'X')
            || (inputArray[2] == 'X' && inputArray[4] == 'X' && inputArray[6] == 'X')) {
            xHasLine = true;
        }

        if ((inputArray[0] == 'O' && inputArray[4] == 'O' && inputArray[8] == 'O')
                || (inputArray[2] == 'O' && inputArray[4] == 'O' && inputArray[6] == 'O')) {
            oHasLine = true;
        }

        if (xHasLine && oHasLine) {
            result = "Impossible";
        } else if ((!xHasLine && !oHasLine) && !hasEmptySpace) {
            result = "Draw";
            keepPlaying = false;
        } else if (xHasLine) {
            result = "X wins";
            keepPlaying = false;
        } else if (oHasLine) {
            result = "O wins";
            keepPlaying = false;
        }

        if (xCount - oCount > 1 || xCount - oCount < -1) {
            result = "Impossible";
        }

        System.out.println(result);

        return keepPlaying;
    }

    private static void printField(char[] inputArray) {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");

            for (int j = 0; j < 3; j++) {
                System.out.print(inputArray[i * 3 + j] + " ");
            }

            System.out.println("|");
        }

        System.out.println("---------");
    }
}
