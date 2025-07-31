public class CompTreasureNode {
    public Character names;
    public CompTreasureNode down;
    public  LocatioanNode right;
    CompTreasureNode(Character dataToAdd){
        names = dataToAdd;
        down =  null;
        right = null;
    }
    public Character getNames() {
        return names;
    }

    public void setNames(Character names) {
        this.names = names;
    }

    public CompTreasureNode getDown() {
        return down;
    }

    public void setDown(CompTreasureNode down) {
        this.down = down;
    }

    public LocatioanNode getRight() {
        return right;
    }

    public void setRight(LocatioanNode right) {
        this.right = right;
    }
}
