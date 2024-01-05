public class RandomPointSpawnTest {
    public static void main(String[] args) {
        DrawCanvas drawCanvas = new DrawCanvas();

        int[][] snakePosition = {{2, 0}, {0, 1}};
        RandomPointSpawn testPoint = new RandomPointSpawn(drawCanvas.X_GRID_FROM_USER, drawCanvas.Y_GRID_FROM_USER);
        System.out.println(testPoint.toString());

        testPoint.removeFilledSpaces(snakePosition);
        System.out.println(testPoint.toString());

        System.out.println(testPoint.getRandomPosition());
    }
}
