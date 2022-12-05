public class MatrixMultiply {
    private static int[][] a;
    private static int[][] b;
    private static int[][] result;
    private static int threadsAmount;

    public static int[][] multiply(int[][] a, int[][] b, int threadsAmount, String method) {
        MatrixMultiply.a = a;
        MatrixMultiply.b = b;
        MatrixMultiply.threadsAmount = threadsAmount;
        result = new int[a.length][a.length];

        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                result[i][j] = 0;
            }
        }

        Thread[] tasks = new Thread[threadsAmount];
        for(int i = 0; i < tasks.length; i++){
            switch (method) {
                case "fox" -> tasks[i] = new Thread(new Fox(i));
                case "cannon" -> tasks[i] = new Thread(new Cannon(i));
                case "tape" -> tasks[i] = new Thread(new Tape(i));
            }
        }

        for (Thread task : tasks) {
            task.start();
        }

        for (Thread task : tasks) {
            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static int[][] successive(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < a.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    private static class Tape implements Runnable{

        private final int idx;
        public Tape(int part_index){
            this.idx = part_index;
        }

        @Override
        public void run() {
            int pivot = (int) Math.ceil(a.length / (double) threadsAmount);
            for (int row = idx * pivot; row < (idx + 1) * pivot && row < a.length; row++) {
                int counter = 0;
                int index = row;
                while (counter < a.length) {
                    int cell = 0;
                    for (int i = 0; i < a.length; i++) {
                        cell += a[row][i] * b[i][index];
                    }

                    result[row][index] = cell;
                    counter++;
                    index = (index + 1) % a.length;
                }
            }
        }
    }
    private static class Fox implements Runnable{

        private final int idx;
        public Fox(int part_index){
            this.idx = part_index;
        }

        @Override
        public void run() {
            int pivot = (int) Math.ceil(a.length / (double) threadsAmount);
            for (int row = idx * pivot; row < (idx + 1) * pivot && row < a.length; row++) {
                int counter = 0;
                int row1 = row;
                int row2 = row;
                while (counter < a.length) {
                    for (int i = 0; i < a.length; i++) {
                        result[row][i] += a[row][row2] * b[row1][i];
                    }

                    row1 = (row1 + 1) % a.length;
                    row2 = (row2 + 1) % a.length;
                    counter++;
                }
            }
        }
    }
    private static class Cannon implements Runnable{

        private final int idx;
        public Cannon(int part_index){
            this.idx = part_index;
        }

        @Override
        public void run() {
            int pivot = (int) Math.ceil(a.length / (double) threadsAmount);
            for (int row = idx * pivot; row < (idx + 1) * pivot && row < a.length; row++) {
                int counter = 0;
                int row1 = row;
                int row2 = row;
                while (counter < a.length) {
                    for (int i = 0; i < a.length; i++) {
                        result[row][i] += a[row][row1] * b[row2][i];
                    }

                    row1 = (a.length + row1 - 1) % a.length;
                    row2 = (a.length + row2 - 1) % a.length;
                    counter++;
                }
            }
        }
    }
}
