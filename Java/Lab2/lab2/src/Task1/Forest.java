package Task1;

public class Forest {
    private boolean[][] forest;
    private int activeForestRow = 0;
//    private int activeForestColumn = 0;
    private boolean isWinnieFound = false;

    public Forest(int forestLength) {
        forest = new boolean[forestLength][forestLength];
        for (int i = 0; i < forestLength; i++) {
            for (int j = 0; j < forestLength; j++) {
                forest[i][j] = false;
            }
        }

        int winnieRow = (int) Math.floor(Math.random() * forestLength);
        int winnieColumn = (int) Math.floor(Math.random() * forestLength);
        forest[winnieRow][winnieColumn] = true;

        System.out.println("Winnie is hiding there: " + winnieRow + " " + winnieColumn);
    }

//    public void checkWinnie(String beeName) {
//
//        if (activeForestColumn == forest.length - 1) {
//            activeForestColumn = 0;
//            activeForestRow++;
//        }
//        System.out.println(beeName + " is searching on " + activeForestRow + " " + activeForestColumn);
//        if (forest[activeForestRow][activeForestColumn]) {
//            System.out.println("Winnie found!");
//            isWinnieFound = true;
//        }
//        activeForestColumn++;
//    }

    public void checkWinnie(String beeName) {
        if (activeForestRow == forest.length || isWinnieFound) {
            return;
        }

//        System.out.println(beeName + " is searching on " + activeForestRow);
        for (int activeForestColumn = 0; activeForestColumn < forest.length; activeForestColumn++) {
            if (forest[activeForestRow][activeForestColumn]) {
                System.out.println("Winnie found!");
                isWinnieFound = true;
                break;
            }
        }
        activeForestRow++;
    }
    public boolean isWinnieFound() {
        return isWinnieFound;
    }
}
