import java.util.*;

public class RandomPointSpawn {

    private int x, y;
    private Random rand = new Random();
    private ArrayList<ArrayList<Integer>> spawnSpaces;

    public RandomPointSpawn(int x, int y /* Position Snake*/) {
        this.x = x;
        this.y = y;

        this.spawnSpaces = new ArrayList<>(x);
        for(int i = 0; i < y; i++){
            spawnSpaces.add(new ArrayList<Integer>());
        }

        fillSpawnSpaces();

    }

    public ArrayList<ArrayList<Integer>> fillSpawnSpaces() {
        int spawnSpacesLength = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                spawnSpaces.get(j).add(j);

                /*for (int t = 0; t < 2; t++) {
                    if (t == 0) {
                        spawnSpaces.get(spawnSpacesLength).add(i);
                    } else if (t == 1) {
                        spawnSpaces.get(spawnSpacesLength).add(j);
                    }

                }*/
                spawnSpacesLength++;
            }
        }
        return spawnSpaces;
    }

    public ArrayList<ArrayList<Integer>> removeFilledSpaces(int[][] snakeOccupation){
        for(int i = 0; i < snakeOccupation.length; i++){
                int toRemoveX = snakeOccupation[i][0];
                int toRemoveY = snakeOccupation[i][1];

                System.out.println("removed space: " + toRemoveX + ", " + toRemoveY);
                spawnSpaces.get(toRemoveX).remove(toRemoveY);
        }

        return spawnSpaces;
    }

    public int[][] getRandomPosition(){
        int randomX = rand.nextInt(spawnSpaces.size());
        int randomY = -1;

        if(!spawnSpaces.get(randomX).isEmpty()){
            randomY = spawnSpaces.get(randomX).remove(rand.nextInt(spawnSpaces.get(randomX).size()));
        }
        return new int[][]{{randomX, randomY}};
    }

 
    public String toString() {
        String s = "";
        for (int i = 0; i < spawnSpaces.size(); i++) {
            for (int j = 0; j < spawnSpaces.get(i).size(); j++) {
                s += spawnSpaces.get(i).get(j) + " ";
            }
            s += "\n";
        }
        return s;
    }

}
