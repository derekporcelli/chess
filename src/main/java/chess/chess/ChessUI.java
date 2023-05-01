package chess.chess;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ChessUI extends Application
    {
        
        public static void main (String[] args)
            {
                launch(args);
            }
        
        private GridPane boardView = new GridPane();
        private Board board = new Board();
        public Piece selectedPiece = null;
        
        @Override
        public void start (Stage primaryStage) throws FileNotFoundException
            {
                Piece[][] gameBoard = board.getBoard();
                for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                            {
                                String squareFile = "src/main/resources/chess/chess/square";
                                if ((i + j) % 2 == 0)
                                    {
                                        squareFile += Color.WHITE.name();
                                    }
                                else
                                    {
                                        squareFile += Color.BLACK.name();
                                    }
                                squareFile += ".png";
                                
                                Square square = new Square(i, j);
                                square.setGraphic(new ImageView(new Image(new FileInputStream(squareFile))));
                                boardView.add(square, i, j);
                                square.setOnMouseClicked(event -> {
                                    if (selectedPiece == null)
                                        {
                                            return;
                                        }
                                    // Move the pawn to the square
                                    move(selectedPiece, square.getFile(),
                                        square.getRank()
                                    );
                                    selectedPiece = null;
                                });
                            }
                    }
                for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                            {
                                Piece gamePiece = gameBoard[i][7 - j];
                                if (gamePiece == null)
                                    {
                                        continue;
                                    }
                                boardView.add(gamePiece, i, j);
                                gamePiece.setOnMouseClicked(event -> {
                                    selectedPiece = gamePiece;
                                });
                            }
                    }
                
                Scene scene = new Scene(boardView);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        
        public void move (Piece piece, int destinationFile, int destinationRank)
            {
                boardView.getChildren().remove(piece);
                boardView.add(piece, destinationFile, destinationRank);
                board.move(piece.getFile(), piece.getRank(), destinationFile, destinationRank);
            }
    }
