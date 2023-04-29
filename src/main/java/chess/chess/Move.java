package chess.chess;

public class Move
    {
        private Square currentSquare;
        private Square nextSquare;
        
        public Move (Square currentSquare, Square nextSquare)
            {
                this.currentSquare = currentSquare;
                this.nextSquare = nextSquare;
            }
        
        public Square getCurrentSquare ()
            {
                return currentSquare;
            }
        
        public Square getNextSquare ()
            {
                return nextSquare;
            }
    }
