package tictactoe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        GameInfo gameInfo = makeGameInfo(line);

        gameInfo.printInfo();
        gameInfo.printGameResult();
    }

    private static GameInfo makeGameInfo(String line) {
        return new GameInfo(line);
    }

    public static class GameInfo {
        private String[][] basicInfo;
        public GameInfo(String line) {

            String[] chars = line.split("");

            String[][] basicInfo = {
                    {chars[0], chars[1], chars[2]},
                    {chars[3], chars[4], chars[5]},
                    {chars[6], chars[7], chars[8]}
            };

            this.basicInfo = basicInfo;
        }

        public void printInfo() {
            System.out.println("---------");
            System.out.println("| " + Arrays.stream(basicInfo[0]).collect(Collectors.joining(" ")) + " |");
            System.out.println("| " + Arrays.stream(basicInfo[1]).collect(Collectors.joining(" ")) + " |");
            System.out.println("| " + Arrays.stream(basicInfo[2]).collect(Collectors.joining(" ")) + " |");
            System.out.println("---------");
        }

        public void printGameResult() {
            boolean isXWin = getIsWin("X");
            boolean isOWin = getIsWin("O");
            boolean isExistEmpty = getIsExistEmpty();
            int xCount = getCount("X");
            int oCount = getCount("O");

            if (Math.abs(xCount - oCount) > 1) {
                System.out.println("Impossible");
                return;
            }

            if (isExistEmpty) {
                if (isXWin && isOWin) {
                    System.out.println("Impossible");
                } else if (isXWin) {
                    System.out.println("X wins");
                } else if (isOWin) {
                    System.out.println("O wins");
                } else {
                    System.out.println("Game not finished");
                }
            } else {
                if (isXWin && isOWin) {
                    System.out.println("Impossible");
                } else if (isXWin) {
                    System.out.println("X wins");
                } else if (isOWin) {
                    System.out.println("O wins");
                } else {
                    System.out.println("Draw");
                }
            }

        }



        private boolean getIsExistEmpty() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (basicInfo[i][j].equals("_")) {
                        return true;
                    }
                }
            }

            return false;
        }

        private int getCount(String str) {
            int count = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (basicInfo[i][j].equals(str)) {
                        count++;
                    }
                }
            }

            return count;
        }

        private boolean getIsWin(String str) {
            boolean isWin = false;
            // 가로
            for (int i = 0; i < 3; i++) {
                if (basicInfo[i][0].equals(str) && basicInfo[i][1].equals(str) && basicInfo[i][2].equals(str)) {
                    return true;
                }
            }

            // 세로
            for (int i = 0; i < 3; i++) {
                if (basicInfo[0][i].equals(str) && basicInfo[1][i].equals(str) && basicInfo[2][i].equals(str)) {
                    return true;
                }
            }

            // 대각선
            if (basicInfo[0][0].equals(str) && basicInfo[1][1].equals(str) && basicInfo[2][2].equals(str)) {
                return true;
            }

            if (basicInfo[0][2].equals(str) && basicInfo[1][1].equals(str) && basicInfo[2][0].equals(str)) {
                return true;
            }

            return false;
        }
    }
}
