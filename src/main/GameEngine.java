package main;

public class GameEngine {

    private GameState gameStatus = GameState.IN_GAME;
    private String playerName;
    private Board gameBoard;
    private Move playerMove;
    private AIEngine aiEngine;
    private Scanner sc = new Scanner(System.in);

    public GameEngine(String playerName){
        this.playerName = playerName;
        gameBoard = new Board();
        run();
    }


    private void run(){
        while(gameStatus == GameState.IN_GAME){
            System.out.println(playerName + "'s Turn");
            playerMove = new Move(sc.nextLine());
            playerMove.makeMove(gameBoard);
            checkVictory();
            if (gameStatus == GameState.HUMAN_WINNER){
                showWinner();
                break;
            }
            aiEngine.makeMove(gameBoard);
            checkVictory();
            if (gameStatus == GameState.AI_WINNER){
                showWinner();
                break;
            }
        }
    }

    private void checkVictory(){
        int noKings = 0;
        GameState tempState;
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8; j++) {
                if (board.hasPiece(i,j)){
                    Piece piece = board.getCell(i,j);
                    if (piece.toString().equals("k")){
                        tempState = GameState.AI_WINNER;
                        noKings++;
                    }else if ( piece.toString().equals("K")) {
                        tempState = GameState.HUMAN_WINNER;
                        noKings++;
                    }
                }
            }
        }
        if (noKings == 2) {
            gameStatus = IN_GAME;
        } else {
            gameStatus = tempState;
        }
    }

    private void showWinner(){
        switch(gameStatus){
            case HUMAN_WINNER:
                System.out.println("White has beaten the black !");
                break;
            case AI_WINNER:
                System.out.println("Black has beaten the white !");
                break;
        }
    }

    private enum GameState {
        IN_GAME, AI_WINNER, HUMAN_WINNER
    }



    private void makeActualMove (int oldX , int oldY , int newX , int newY){

    Piece movingPiece =this.gameBoard.getPiece(oldX,oldY);
    this.gameBoard.setCell(oldX,oldY) = null;
    this.gameBoard.setCell(newX, newY) = movingPiece ;
    movingPiece.setX(newX);
    movingPiece.setY(newY);


    }
}
