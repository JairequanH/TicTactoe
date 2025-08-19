import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int gameMode = 0;
        boolean gameOver = false;
        char[] board = new char[9];
        Scanner scanner = new Scanner(System.in);
        char turn = 'o';
        int newMove = -1;
        int[] movesO = new int[3];
        int[] movesX = new int[3];

        while(gameMode !=5){
            System.out.println("\nEnter your game mode:    " +
                    "\n-1 for regular" +
                    "\n-2 for 3 pieces only");
            while(gameMode != 1 && gameMode != 2 && gameMode != 5){
                gameMode = scanner.nextInt();
                if(gameMode != 1 && gameMode != 2 && gameMode != 5){
                    System.out.println("Invalid game mode");
                    System.out.println(gameMode);
                }
            }

            //game mode 1 which is regular tic-tac-toe
            if(gameMode == 1){
                while(gameWon(board)==0){
                    printBoard(board);

                    boolean boardFull = true;
                    for(int i = 0; i<board.length; i++){
                        if(board[i] == '\0'){
                            boardFull = false;
                        }
                    }
                    if(boardFull){
                        gameMode = 0;
                        System.out.println("\nGAME OVER");
                        break;
                    }

                    System.out.println("\nplayer's " + turn + " turn enter valid spot (1-9)");

                    newMove = scanner.nextInt()-1;

                    if(newMove >= 0 && newMove <=8 && board[newMove] == '\0'){
                        board[newMove] = turn;
                        if(turn == 'o'){
                            turn = 'x';
                        }else{
                            turn = 'o';
                        }
                    }

                    if(gameWon(board) == 1 || gameWon(board) == 2){
                        System.out.println("GAME OVER");
                        printBoard(board);
                    }
                }
                resetBoard(board);
                gameMode = 0;

            }

            //gameMode 2 where a move disappears 3 moves after placing
            if(gameMode == 2){
                int count = 0;
                int[] lastMoves = new int[2];
                while(gameWon(board)==0){
                    if(count>5) {
                        printBoardThree(board, lastMoves);
                    }else{
                        printBoard(board);
                    }
                    System.out.println("\nplayer's " + turn + " turn enter valid spot (1-9)");

                    newMove = scanner.nextInt()-1;

                    if(newMove >= 0 && newMove <=8 && board[newMove] == '\0'){
                        board[newMove] = turn;
                        count++;

                        if(turn == 'o'){
                            if(count>6)
                                board[movesO[0]] = '\0';
                            addMove(movesO, newMove);
                            turn = 'x';
                        }else{
                            if(count>6)
                                board[movesX[0]] = '\0';
                            addMove(movesX, newMove);

                            turn = 'o';
                        }
                    }

                    if(gameWon(board) == 1 || gameWon(board) == 2){
                        System.out.println("GAME OVER");
                        printBoard(board);
                    }
                    lastMoves[0] = movesX[0];
                    lastMoves[1] = movesO[0];
                }
                resetBoard(board);
                gameMode = 0;

            }

        }
    }

    public static void printBoard(char[] board) {
        for (int i = 0; i <= 8; i++) {
            if (i == 0 || i == 3 || i == 6)
                System.out.print("\n----------------\n");
            if (board[i] == 'x') {
                System.out.print("| X |");
            }
            else if (board[i] == 'o') {
                System.out.print("| O |");
            }else{
                System.out.print("|   |");
            }
        }
    }

    public static void printBoardThree(char[] board, int[] last) {
        for (int i = 0; i <= 8; i++) {
            if (i == 0 || i == 3 || i == 6)
                System.out.print("\n----------------\n");
            if (board[i] == 'x') {
                if (i == last[0]) {
                    System.out.print("| x |");
                } else {
                    System.out.print("| X |");
                }

            }else if(board[i] == 'o'){
                if(i == last[1]) {
                    System.out.print("| o |");
                }else{
                    System.out.print("| O |");
                }
            }else{
                System.out.print("|   |");
            }
        }
    }


    public static int gameWon(char[] board) {
        //check x for win
        //checking horizontals
        if (board[0] == 'x' && board[1] == 'x' && board[2] == 'x') {
            return 1;
        }
        if (board[3] == 'x' && board[4] == 'x' && board[5] == 'x') {
            return 1;
        }
        if (board[6] == 'x' && board[7] == 'x' && board[8] == 'x') {
            return 1;
        }

        //checking vertically
        if (board[0] == 'x' && board[3] == 'x' && board[6] == 'x') {
            return 1;
        }
        if (board[1] == 'x' && board[4] == 'x' && board[7] == 'x') {
            return 1;
        }
        if (board[2] == 'x' && board[5] == 'x' && board[8] == 'x') {
            return 1;
        }

        //checking diagonally
        if (board[0] == 'x' && board[4] == 'x' && board[8] == 'x') {
            return 1;
        }
        if (board[2] == 'x' && board[4] == 'x' && board[6] == 'x') {
            return 1;
        }

        //check o for win
        //checking horizontals
        if (board[0] == 'o' && board[1] == 'o' && board[2] == 'o') {
            return 2;
        }
        if (board[3] == 'o' && board[4] == 'o' && board[5] == 'o') {
            return 2;
        }
        if (board[6] == 'o' && board[7] == 'o' && board[8] == 'o') {
            return 2;
        }

        //checking vertically
        if (board[0] == 'o' && board[3] == 'o' && board[6] == 'o') {
            return 2;
        }
        if (board[1] == 'o' && board[4] == 'o' && board[7] == 'o') {
            return 2;
        }
        if (board[2] == 'o' && board[5] == 'o' && board[8] == 'o') {
            return 2;
        }

        //checking diagonally
        if (board[0] == 'o' && board[4] == 'o' && board[8] == 'o') {
            return 2;
        }
        if (board[2] == 'o' && board[4] == 'o' && board[6] == 'o') {
            return 2;
        }

        //game not won so return 0
        return 0;
    }

    public static void resetBoard(char[] board) {
        for (int i = 0; i <= 8; i++) {
            board[i] = '\0';
        }
    }

    public static void addMove(int[] moves, int tempMove){
        moves[0] = moves[1];
        moves[1] = moves[2];
        moves[2] = tempMove;
    }
}