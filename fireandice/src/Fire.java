import java.util.Random;

public class Fire{
    public Fire(int startX,int startY,char map[][]){
        coordinate[0] = new Coordinates(startX,startY);
        tempQueue.enqueue(coordinate[coordinateNum]);
        this.map = map;
    }

    public char[][] getMap() {
        return map;
    }

    private  char map[][] = new char[23][53];
    private Coordinates[] coordinate = new Coordinates[150];
    private CircularQueue tempQueue = new CircularQueue(50);
    private CircularQueue fireQueue = new CircularQueue(50);
    private int coordinateNum = 0;
    private int count = 0;
    private int countPlus = 0;
    private int tryNum = 0;
    private int delete = 0;
    private int counter = 0;
    private int lastDir = 1;
    private int life[] = new int[50];


    public void nextFire(char mapp[][]){
        map = mapp;
        countPlus = count;
        while ((count < 49)&&count == countPlus && counter < 200 ){
            Coordinates tempCoord = (Coordinates)tempQueue.peek();
            int x = tempCoord.getX();
            int y = tempCoord.getY();
            if(lastDir == 1){
                y += 1;
                lastDir++;
            } else if (lastDir == 2) {
                x += 1;
                lastDir++;
            }else if(lastDir == 3){
                x -= 1;
                lastDir++;
            }else{
                y -= 1;
                lastDir = 1;
                Coordinates temppCoord = (Coordinates) tempQueue.peek();

                if(map[temppCoord.getY()][temppCoord.getX()] == ' '){
                    map[temppCoord.getY()][temppCoord.getX()] = '-';
                }
                fireQueue.enqueue(tempQueue.dequeue());
                for(int i = 0;i<count;i++){
                    life[i]--;
                }
                life[count] = 100;
                count++;

            }
            boolean isExist = false;
            int size1 = tempQueue.size();
            int size = fireQueue.size();
            if(!fireQueue.isEmpty()){
                for(int i = 0;i < size;i++){
                    Coordinates coordinatesTemp = (Coordinates) fireQueue.peek();
                    if((x == coordinatesTemp.getX())&&(y == coordinatesTemp.getY())){
                        isExist = true;
                    }
                    fireQueue.enqueue(fireQueue.dequeue());
                }
                for(int i = 0;i<size1;i++){
                    Coordinates coordinatesTemp = (Coordinates) tempQueue.peek();
                    if((x == coordinatesTemp.getX())&&(y == coordinatesTemp.getY())){
                        isExist = true;
                    }
                    tempQueue.enqueue(tempQueue.dequeue());
                }
            }
            if((map[y][x] == ' ')&&(!isExist)){
                coordinateNum++;
                coordinate[coordinateNum] = new Coordinates(x,y);
                tempQueue.enqueue(coordinate[coordinateNum]);
            }
            counter++;
        }
        if(count >= 49 && tryNum <50){
            tryNum++;
        } else if(count >= 49 && delete < 49){
            Coordinates tempCoordinate = (Coordinates)fireQueue.peek();
            int x = tempCoordinate.getX();
            int y = tempCoordinate.getY();
            fireQueue.enqueue(fireQueue.dequeue());
            delete++;
            map[y][x] = ' ';

        }
    }
}
