package chess.chess;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

abstract public class Piece extends Button
    {
        private Color color;
        private ImageView sprite;
        private static String path = "src/main/resources/chess/chess/";
        private int file;
        private int rank;
        private PieceType type;
        private boolean isUnmoved;
        
        public Piece (Color color, PieceType type, int file, int rank)
            {
                super();
                this.file = file;
                this.type = type;
                this.rank = rank;
                this.color = color;
                isUnmoved = true;
                try
                    {
                        sprite = new ImageView(new Image(new FileInputStream(path + type.toString() + color + ".png")));
                    }
                catch (FileNotFoundException e)
                    {
                        throw new RuntimeException(e);
                    }
                this.setGraphic(sprite);
                this.setStyle("-fx-background-color: transparent; -fx-background-radius: 0; -fx-padding: 0 0 0 0;");
            }
        
        @Override
        public String toString(){
            return color.toString() + ": " + file + ", " + rank;
        }
        
        public boolean isUnmoved ()
            {
                return isUnmoved;
            }
        
        public void move ()
            {
                isUnmoved = false;
            }
        
        public PieceType getType ()
            {
                return type;
            }
        
        public int getFile ()
            {
                return file;
            }
        
        public int getRank ()
            {
                return rank;
            }
        
        public Color getColor ()
            {
                return color;
            }
        
        public ImageView getSprite ()
            {
                return sprite;
            }
        
        public void setFile (int file)
            {
                this.file = file;
            }
        
        public void setRank (int rank)
            {
                this.rank = rank;
            }
        
        public static class Empty extends Piece
            {
                private static int VALUE = 0;
                private static PieceType type = PieceType.empty;
                
                public Empty (int file, int rank)
                    {
                        super(Color.EMPTY, type, file, rank);
                    }
            }
        
        public static class Pawn extends Piece
            {
                private static int VALUE = 1;
                private static PieceType type = PieceType.pawn;
                
                public Pawn (Color color, int file, int rank)
                    {
                        super(color, type, file, rank);
                    }
            }
    }
