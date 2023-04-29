package chess.chess;

public class Square
    {
        private char file;
        private int row;
        
        public Square (char file, int row)
            {
                this.file = file;
                this.row = row;
            }
        
        public char getFile ()
            {
                return file;
            }
        
        public int getRow ()
            {
                return row;
            }
    }
