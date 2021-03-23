package Matrix;

public class Matrix {
    public static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] answer;
        int width, height;
        int row, column;

        height = m1.length;
        if (m2.length > 0)
            width = m2[0].length;
        else
            width = 0;
        answer = new int[height][width];

        for (row = 0; row < height; row++)
            for (column = 0; column < width; column++)
                answer[row][column] = countCell(m1, m2, row, column);
        return answer;
    }

    private static int countCell(int[][] m1, int[][] m2, int row, int column) {
        int cell = 0;
        for (int c = 0; c < m2.length; c++)
            cell += m1[row][c] * m2[c][column];
        return cell;
    }

    // shitCode
    public static int[][] multiply(int[][] m1, double[][] m2) {
        int[][] answer;
        int width, height;
        int row, column;

        height = m1.length;
        if (m2.length > 0)
            width = m2[0].length;
        else
            width = 0;
        answer = new int[height][width];

        for (row = 0; row < height; row++)
            for (column = 0; column < width; column++)
                answer[row][column] = countCell(m1, m2, row, column);
        return answer;
    }

    private static int countCell(int[][] m1, double[][] m2, int row, int column) {
        int cell = 0;
        for (int c = 0; c < m2.length; c++)
            cell += m1[row][c] * m2[c][column];
        return cell;
    }

    public static void print(int[][] m) {
        for (int c1 = 0; c1 < m.length; c1++) {
            for (int c2 = 0; c2 < m[c1].length; c2++)
                System.out.print(m[c1][c2] + " ");
            System.out.println();
        }
    }
}
