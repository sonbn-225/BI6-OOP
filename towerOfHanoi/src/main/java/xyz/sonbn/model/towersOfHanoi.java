package xyz.sonbn.model;

/**
 * Created by SonBN on 13-Nov-16.
 */
public class towersOfHanoi {
    private tower[] towers = new tower[3];

    public towersOfHanoi (int numberOfDisk){
        for (int i = 0; i < 3; i++) {
            towers[i] = new tower(i);
        }
        for (int i = 0; i < numberOfDisk; i++) {
            int radius = numberOfDisk - i;
            disk disk = new disk(0, radius, numberOfDisk);
            towers[0].pushDisk(disk);
        }
    }

    public void resetDisk(int numberOfDisk){
        for (int i = 0; i < 3; i++) {
            towers[i].removeAllDisk();
        }
        for (int i = 0; i < numberOfDisk; i++) {
            int radius = numberOfDisk - i;
            disk disk = new disk(0, radius, numberOfDisk);
            towers[0].pushDisk(disk);
        }
    }

    public tower getTower(int number){
        return this.towers[number];
    }
}
