import java.util.ArrayList;
import java.util.List;

public class Board {
    int dimension;
    List<List<Cell>> matrix;

    public Board(int dimension) {
        this.dimension = dimension;
        matrix = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                matrix.get(i).add(new Cell(i, j));
            }
        }
    }


    public void displayBoard() {
        for (int i = 0; i < dimension; i++) {
            List<Cell> cells = matrix.get(i);
            for (Cell cell : cells) {
                cell.displayCell();
            }
            System.out.println();
        }
    }
    public boolean placeSymbol(int row, int col, Player player) {
        if (row >= 0 && row < dimension && col >= 0 && col < dimension) {
            Cell cell = matrix.get(row).get(col);
            if (cell.player == null) {
                cell.setPlayer(player);
            } else {
                System.out.println("Cell already occupied.");
            }
        } else {
            System.out.println("Invalid cell position.");
        }
        return false;
    }

    public boolean isBoardFull() {
        for (List<Cell> row : matrix) {
            for (Cell cell : row) {
                if (cell.player == null) {
                    return false; // If any cell is empty, the board is not full
                }
            }
        }
        return true; // If all cells are filled, the board is full
    }

    public int getDimension() {
        return dimension;
    }

    public boolean checkForWinner(Player player) {
            char symbol = player.getSymbol();

            // Check rows
            for (int i = 0; i < dimension; i++) {
                boolean win = true;
                for (int j = 0; j < dimension; j++) {
                    if (matrix.get(i).get(j).player == null || matrix.get(i).get(j).player.getSymbol() != symbol) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }

            // Check columns
            for (int j = 0; j < dimension; j++) {
                boolean win = true;
                for (int i = 0; i < dimension; i++) {
                    if (matrix.get(i).get(j).player == null || matrix.get(i).get(j).player.getSymbol() != symbol) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }

            // Check diagonals
            boolean win = true;
            for (int i = 0; i < dimension; i++) {
                if (matrix.get(i).get(i).player == null || matrix.get(i).get(i).player.getSymbol() != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }

            win = true;
            for (int i = 0; i < dimension; i++) {
                if (matrix.get(i).get(dimension - 1 - i).player == null || matrix.get(i).get(dimension - 1 - i).player.getSymbol() != symbol) {
                    win = false;
                    break;
                }
            }
            return win;
        }

    public boolean isCellEmpty(int row, int col) {
        if (row >= 0 && row < dimension && col >= 0 && col < dimension) {
            Cell cell = matrix.get(row).get(col);
            return cell.player==null;
        } else {
            System.out.println("Invalid cell position.");
            return false;
        }
    }
    public void clearCell(int row, int col) {
        if (row >= 0 && row < dimension && col >= 0 && col < dimension) {
            Cell cell = matrix.get(row).get(col);
            cell.setPlayer(null);
        } else {
            System.out.println("Invalid cell position.");
        }
    }
}


