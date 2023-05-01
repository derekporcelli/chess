package chess.chess;

public class Board
    {
        private Piece[][] board;
        
        public Board ()
            {
                board = new Piece[8][8];
                for (int i = 0; i < 8; i++)
                    {
                        board[i][1] = new Piece.Pawn(Color.WHITE, i, 1);
                    }
            }
        
        public void move (int originalFile, int originalRank, int destinationFile, int destinationRank)
            {
                board[destinationFile][destinationRank] = board[originalFile][originalRank];
                board[originalFile][originalRank] = null;
            }
        
        public Piece[][] getBoard(){
            return board;
        }
    }
