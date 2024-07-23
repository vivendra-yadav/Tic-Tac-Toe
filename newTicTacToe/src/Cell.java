public class Cell {
    int i;
    int j;
    Player player;
    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void displayCell(){
        if(player==null){
            System.out.print("| |");
        }
        else{
            System.out.print("|" + player.getSymbol() + "|");
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}