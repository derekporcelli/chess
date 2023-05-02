package chess.chess;

import javafx.scene.Node;

public class Square extends Node
    {
        private final int file;
        private final int rank;
    
        public Square (int file, int rank)
            {
                super();
                this.file = file;
                this.rank = rank;
                this.setStyle("-fx-background-color: transparent; -fx-background-radius: 0; -fx-padding: 0 0 0 0;");
            }
        
        public int getFile ()
            {
                return file;
            }
        
        public int getRank ()
            {
                return rank;
            }
    }
