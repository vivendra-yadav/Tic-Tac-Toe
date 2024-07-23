import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        // Game loop
        while (true) {
            System.out.println("Enter the Dimension of the Board");
            int dimension = scn.nextInt();

            int noOfPlayers = dimension - 1;
            System.out.println("Do you want Bot? Enter Y or N");
            String bot = scn.next();
            List<Player> players = new ArrayList<>();
            if (bot.equalsIgnoreCase("Y")) {
                players.add(new Bot("Bot", 'B'));
                noOfPlayers--;
            }
            Board board = new Board(dimension);

            for (int i = 0; i < noOfPlayers; i++) {
                System.out.println("Enter name for Player " + (i + 1));
                String name = scn.next();
                System.out.println("Enter symbol for Player " + (i + 1));
                char symbol = scn.next().charAt(0);
                players.add(new Player(name, symbol));
            }

            Collections.shuffle(players);
            Stack<int[]> movesHistory = new Stack<>();
            Player currentPlayer = null; // Track the current player

            while (true) {
                if (currentPlayer == null) {
                    currentPlayer = players.get(0); // Start with the first player
                }
                System.out.println(currentPlayer.getName() + "'s turn:");

                if (currentPlayer instanceof Bot) {
                    int[] move = ((Bot) currentPlayer).selectMove(board);
                    board.placeSymbol(move[0], move[1], currentPlayer);
                    movesHistory.push(move);
                } else {

                    System.out.println("Please enter target row");
                    int row = scn.nextInt();
                    System.out.println("Please enter target col");
                    int col = scn.nextInt();
                    int[] move = {row, col};
                    board.placeSymbol(row, col, currentPlayer);
                    movesHistory.push(move);
                }

                board.displayBoard();

                if (board.checkForWinner(currentPlayer)) {
                    System.out.println("Congratulations! " + currentPlayer.getName() + " wins!");
                    break;
                }

                if (board.isBoardFull()) {
                    System.out.println("It's a draw! The board is full.");
                    break;
                }

                // Ask for undo only after human player's move
                if (!(currentPlayer instanceof Bot)) {
                    System.out.println("Do you want to undo the last move? Enter Y or N");
                    String undoChoice = scn.next();
                    if (undoChoice.equalsIgnoreCase("Y")) {
                        if (!movesHistory.isEmpty()) {
                            int[] lastMove = movesHistory.pop();
                            board.clearCell(lastMove[0], lastMove[1]);
                            System.out.println("Last move undone.");
                            board.displayBoard();
                            continue; // Continue the loop without switching player
                        } else {
                            System.out.println("No moves to undo.");
                        }
                    }
                }

                // Switch to the next player
                int currentPlayerIndex = players.indexOf(currentPlayer);
                currentPlayer = players.get((currentPlayerIndex + 1) % players.size());
            }

            // Ask for replay
            System.out.println("Do you want to play again? Enter Y or N");
            String replayChoice = scn.next();
            if (!replayChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }
    }
}
