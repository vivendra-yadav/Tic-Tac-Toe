import java.util.Random;

public class Bot extends Player{

    public Bot(String name, char symbol) {
        super(name, symbol);
    }
    public int[] selectMove(Board board) {
        // Implement your bot's strategy here
        // For simplicity, let's assume the bot selects a random empty cell
        Random random = new Random();
        int dimension = board.getDimension();
        int row, col;
        do {
            row = random.nextInt(dimension);
            col = random.nextInt(dimension);
        }
        while (!board.isCellEmpty(row, col));
        return new int[]{row, col};
    }
}

