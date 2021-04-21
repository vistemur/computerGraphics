package Matrix;

public class Matrix {
    public static float[][] multiply(float[][] m1, float[][] m2) {
        float[][] answer;
        int width, height;
        int row, column;

        height = m1.length;
        if (m2.length > 0)
            width = m2[0].length;
        else
            width = 0;
        answer = new float[height][width];

        try {
            for (row = 0; row < height; row++)
                for (column = 0; column < width; column++)
                    answer[row][column] = countCell(m1, m2, row, column);
        } catch (Throwable e) {
            System.out.println("matrix multiply error");
        }
        return answer;
    }

    private static float countCell(float[][] m1, float[][] m2, int row, int column) {
        float cell = 0F;
        for (int c = 0; c < m2.length; c++)
            cell += m1[row][c] * m2[c][column];
        return cell;
    }

    public static float[][] resize(float[][] matrix, int width, int height) {
        float[][] answer;

        answer = new float[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.length > y && matrix[y].length > x)
                    answer[y][x] = matrix[y][x];
                else
                    answer[y][x] = 1F;
            }
        }
        return answer;
    }

    public static void print(int[][] m) {
        for (int[] ints : m) {
            for (int anInt : ints)
                System.out.print(anInt + " ");
            System.out.println();
        }
    }
}
