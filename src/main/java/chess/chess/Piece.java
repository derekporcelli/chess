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
        
        public Piece (Color color, String name, int file, int rank)
            {
                super();
                this.file = file;
                this.rank = rank;
                this.color = color;
                try
                    {
                        sprite = new ImageView(new Image(new FileInputStream(path + name + color + ".png")));
                    }
                catch (FileNotFoundException e)
                    {
                        throw new RuntimeException(e);
                    }
                this.setGraphic(sprite);
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
        
        public Color getColor ()
            {
                return color;
            }
        
        public ImageView getSprite ()
            {
                return sprite;
            }
        
        public static class Pawn extends Piece
            {
                private static int VALUE = 1;
                private boolean isUnmoved;
                private static String name = "pawn";
                
                public Pawn (Color color, int file, int rank)
                    {
                        super(color, name, file, rank);
                        isUnmoved = true;
                    }
            }
    }
