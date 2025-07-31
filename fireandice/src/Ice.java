public class Ice {
    public Ice(int startX,int startY,char map[][],int direction){
        push(direction);
        way.pop();
        this.map = map;
        this.direction = direction;
        lastX = startX;
        lastY = startY;
        dir = direction;
    }
    public void push(int directionn){
        if((directionn +3)%4 != 0){
            way.push((directionn+3)%4);
        }else{way.push(4);}
        if((directionn +2)%4 != 0){
            way.push((directionn+2)%4);
        }else{way.push(4);}
        if((directionn +1)%4 != 0){
            way.push((directionn+1)%4);
        }else{way.push(4);}
        way.push(directionn);
    }
    private int lastX;

    public char[][] getMap() {
        return map;
    }
    private int lastY;
    private int delete = 0;
    private int direction;
    private int errorCounter = 0;
    private char[][] map = new char[23][53];
    private int iceCount = 0;
    private int lifeCount = 0;
    private int tryCount = 0;
    private Coordinates[] coordinate = new Coordinates[150];
    private CircularQueue iceQueue = new CircularQueue(25);
    private Stack way = new Stack(4);
    private int dir = direction;
    public void nextIce(char[][] mapp){
        map = mapp;
        boolean isWhile = false;
        tryCount = iceCount;
        while (iceCount<24 && iceCount == tryCount &&errorCounter<50){
            int x = 0;
            int y = 0;
            if(dir == 1){
                x = 1;
            }else if(dir ==2){
                y = -1;
            }else if(dir ==3){
                x = -1;
            }else{
                y = +1;
            }
            if(map[lastY+y][lastX+x] == ' '){
                map[lastY+y][lastX+x] = '+';
                lastX = lastX + x;
                lastY = lastY + y;
                coordinate[iceCount] = new Coordinates(lastX,lastY);
                iceQueue.enqueue(coordinate[iceCount]);
                iceCount++;
                lifeCount++;
            }
            else{

                if(way.isEmpty()){
                    push(dir);
                }
                dir = (int)way.pop();
            }
            errorCounter++;
            isWhile = true;
        }
        if(lifeCount <80&& !isWhile){
            lifeCount++;
        }
        if (lifeCount>=80 && !isWhile&& delete<25){
            Coordinates tempCoordinate = (Coordinates)iceQueue.peek();
            int x = tempCoordinate.getX();
            int y = tempCoordinate.getY();
            iceQueue.enqueue(iceQueue.dequeue());
            delete++;
            map[y][x] = ' ';
        }

    }
}
// 1 > ,2 ^ , 3 < ,4 v