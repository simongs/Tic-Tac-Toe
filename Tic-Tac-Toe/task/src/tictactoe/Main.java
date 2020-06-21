package tictactoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter cells: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        GameInfo gameInfo = new GameInfo(line);
        gameInfo.printInfo();


        boolean b;
        do {
            System.out.print("Enter the coordinates: ");
            String nextItem = scanner.nextLine();
            b = gameInfo.pushNextItem(nextItem);
        } while (b == false);

        gameInfo.printInfo();

        //gameInfo.printGameResult();
    }

    public static class GameInfo {
        
        private Map<String, String> reversePointInfo;
        private String[][] basicInfo;
        public GameInfo(String line) {

            String removedUnderLine = line.replace("_", " ");
            String[] chars = removedUnderLine.split("");

            String[][] basicInfo = {
                    {chars[0], chars[1], chars[2]},
                    {chars[3], chars[4], chars[5]},
                    {chars[6], chars[7], chars[8]}
            };

            this.basicInfo = basicInfo;
            this.reversePointInfo = makeReversePointInfo();
        }

        private Map<String, String> makeReversePointInfo() {
            Map<String, String> reversePointInfo = new HashMap<>();
            reversePointInfo.put("1 1", "2 0");
            reversePointInfo.put("1 2", "1 0");
            reversePointInfo.put("1 3", "0 0");
            reversePointInfo.put("2 1", "2 1");
            reversePointInfo.put("2 2", "1 1");
            reversePointInfo.put("2 3", "0 1");
            reversePointInfo.put("3 1", "2 2");
            reversePointInfo.put("3 2", "1 2");
            reversePointInfo.put("3 3", "0 2");
            return reversePointInfo;
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

        public boolean pushNextItem(String nextItem) {
            // validation user input
            try {
                String[] nextItemArray = nextItem.split(" ");
                int xPos = Integer.valueOf(nextItemArray[0]);
                int yPos = Integer.valueOf(nextItemArray[1]);

                if (xPos > 3 || yPos > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                return false;
            }

            // 사용자가 입력한 좌표로 실제 배열 좌표 정보를 가져온다.
            String reversedPointInfo = reversePointInfo.get(nextItem);
            String[] reversedNextItemArray = reversedPointInfo.split(" ");
            int xPos = Integer.valueOf(reversedNextItemArray[0]);
            int yPos = Integer.valueOf(reversedNextItemArray[1]);

            // 이미 값이 있으면 에러처리
            // 없으면 특정 값을 입력한다.
            String currentValue = basicInfo[xPos][yPos];
            if (!currentValue.equals(" ")) {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            } else {
                basicInfo[xPos][yPos] = "X";
                return true;
            }


        }
    }
}
