package Matrix;

public class Matrix {
    public static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] answer = new int[m1[0].length][m2.length];

        for (int c1 = 0; c1 < answer.length; c1++) {
            for (int c2 = 0; c2 < answer[c1].length; c2++) {
                for (int c3 = 0; c3 < answer[c1].length; c3++) {
                    answer[c2][c3] += m2[c2][c1] * m1[c1][c3];
                }
            }
        }
        return answer;
    }

    public static void print(int[][] m) {
        for (int c1 = 0; c1 < m[0].length; c1++) {
            for (int c2 = 0; c2 < m.length; c2++) {
                System.out.print(m[c2][c1] + " ");
            }
            System.out.println();
        }
    }
}
