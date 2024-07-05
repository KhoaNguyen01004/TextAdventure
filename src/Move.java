import java.util.Random;

public class Move extends GameMap {

    private final Random random = new Random();

    private Coordinate currentCoordinate;

    public Move() {
        currentCoordinate = randCoordinate();
    }

    public int getX(){
        return currentCoordinate.getX();
    }

    public int getY(){
        return currentCoordinate.getY();
    }

    private Coordinate randCoordinate() {
        Coordinate randCurrentCoordinate = new Coordinate(random.nextInt(4), random.nextInt(3));
        if (roomNeedLock(randCurrentCoordinate.getX(), randCurrentCoordinate.getY())) {
            randCoordinate();
        }
        return randCurrentCoordinate;
    }

    public void left() {
        if ((currentCoordinate.getX() - 1) >= 0) {
            currentCoordinate = new Coordinate(currentCoordinate.getX() - 1, currentCoordinate.getY());
        } else {
            hitWallPrompt();
        }
    }

    public void right() {
        if ((currentCoordinate.getX() + 1) <= 3) {
            currentCoordinate = new Coordinate(currentCoordinate.getX() + 1, currentCoordinate.getY());
        } else {
            hitWallPrompt();
        }
    }

    public void up() {
        if (currentCoordinate.equals(new Coordinate(1, 0)) ||
                currentCoordinate.equals(new Coordinate(2, 1))) {
            currentCoordinate = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() + 1);
        }
        else{
            System.out.println("There is no way to go up from here!");
        }
    }

    public void down(){
        if (currentCoordinate.equals(new Coordinate(1, 2)) ||
                currentCoordinate.equals(new Coordinate(2, 1))) {
            currentCoordinate = new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() - 1);
        }
        else{
            System.out.println("There is no way to go down from here!");
        }
    }

    private void hitWallPrompt() {
        System.out.println("There is a wall there");
    }
}
