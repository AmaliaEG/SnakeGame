public class gameRun {
    private boolean alive = true;
    public gameRun() {
    }

    public void collision() {
        int x = snakePosition.getXPosition();
        int y = snakePosition.getYPosition();
        for(int i = 0; i < snakePosition.size(); i++) {
            if (x = snakePosition.get(i).get(0) && y = snakePosition.get(i).get(1)) {
                alive = false;
            }
        }
        
    }
}
