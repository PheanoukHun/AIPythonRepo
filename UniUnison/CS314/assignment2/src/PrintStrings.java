public class PrintStrings {
    public static void main(String[] args) {

        int[][] matVals = { { 1, 2, 3, 0 }, { 0, 3, 2, 3 }, { 0, 0, 4, -1 }, { 1, 2, 3, 4 } };

        MathMatrix mat = new MathMatrix(matVals);
        System.out.println(mat);
    }
}
