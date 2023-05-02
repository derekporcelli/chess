package chess.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board
    {
        private Color turn;
        private Piece selectedPiece;
        private final Piece[][] board;
        
        public Board ()
            {
                turn = Color.WHITE;
                selectedPiece = null;
                board = new Piece[8][8];
                for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                            {
                                board[i][j] = new Piece.Empty(i, j);
                            }
                    }
                for (int i = 0; i < 8; i++)
                    {
                        board[i][1] = new Piece.Pawn(Color.WHITE, i, 1);
                    }
                for (int i = 0; i < 8; i++)
                    {
                        board[i][6] = new Piece.Pawn(Color.BLACK, i, 6);
                    }
            }
        
        public List<Piece> move (Piece original, Piece destination)
            {
                int originalFile = original.getFile();
                int originalRank = original.getRank();
                int destinationFile = destination.getFile();
                int destinationRank = destination.getRank();
                
                board[destinationFile][destinationRank] = board[originalFile][originalRank];
                board[originalFile][originalRank] = new Piece.Empty(originalFile, originalRank);
                board[destinationFile][destinationRank].setFile(destinationFile);
                board[destinationFile][destinationRank].setRank(destinationRank);
                board[destinationFile][destinationRank].move();
                turn = original.getColor().opposite();
                
                List<Piece> updatedPieces = new ArrayList<>();
                updatedPieces.add(board[originalFile][originalRank]);
                updatedPieces.add(board[destinationFile][destinationRank]);
                return updatedPieces;
            }
        
        public Piece[][] getBoard ()
            {
                return board;
            }
    
        public Color getTurn ()
            {
                return turn;
            }
        
        public void selectPiece (Piece piece)
            {
                selectedPiece = piece;
            }
        
        public List<Piece> handle (Piece destination)
            {
                List<Piece> updatedPieces = new ArrayList<>();
                if (selectedPiece == null)
                    {
                        return updatedPieces;
                    }
                
                switch (selectedPiece.getType())
                    {
                        case pawn ->
                            {
                                Piece.Pawn selectedPawn = ((Piece.Pawn) selectedPiece);
                                if (destination.getFile() != selectedPawn.getFile())
                                    {
                                        break;
                                    }
                                if (destination.getRank() == selectedPawn.getRank() + selectedPawn.getColor().getPawnMove())
                                    {
                                        updatedPieces.addAll(move(selectedPiece, destination));
                                    }
                                else if (destination.getRank() == selectedPawn.getRank() + (selectedPawn.getColor().getPawnMove() * 2) && selectedPawn.isUnmoved())
                                    {
                                        updatedPieces.addAll(move(selectedPiece, destination));
                                    }
                                break;
                            }
                    }
                selectedPiece = null;
                return updatedPieces;
            }
        
        @Override
        public String toString(){
            StringBuilder string = new StringBuilder();
            for(Piece[] rank : board){
                string.append(Arrays.toString(rank)).append("\n");
            }
            return string.toString();
        }
    }
