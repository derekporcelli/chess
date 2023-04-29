package chess.chess;

import java.util.ArrayList;
import java.util.List;

abstract public class Piece
    {
        private Square currentSquare;
        private Color color;
        
        public Piece (Square startingSquare, Color color)
            {
                currentSquare = startingSquare;
                this.color = color;
            }
        
        public Square getCurrentSquare ()
            {
                return currentSquare;
            }
        
        public void kill ()
            {
                currentSquare = null;
            }
        
        abstract public List<Move> getPossibleMoves ();
        
        abstract public void move ();
        
        class Pawn extends Piece
            {
                private static int VALUE = 1;
                private boolean isUnmoved;
                
                public Pawn (Square startingSquare, Color color)
                    {
                        super(startingSquare, color);
                        isUnmoved = true;
                    }
                
                @Override
                public List<Move> getPossibleMoves ()
                    {
                        List<Move> moves = new ArrayList<>();
                        
                    }
            }
    }
