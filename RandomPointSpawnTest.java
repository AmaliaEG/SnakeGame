public class RandomPointSpawnTest {
    public static void main(String[] args) {
        int[][] snakePosition = {{2, 0}, {0, 1}};
        RandomPointSpawn testPoint = new RandomPointSpawn(3, 4);
        System.out.println(testPoint.toString());

        testPoint.removeFilledSpaces(snakePosition);
        System.out.println(testPoint.toString());

        System.out.println(testPoint.getRandomPosition());
    }
}
