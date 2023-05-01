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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ChessUI extends Application
    {
        public static void main (String[] args)
            {
                launch(args);
            }
        
        private GridPane boardView = new GridPane();
        private Board board = new Board();
        private Piece[][] gameBoard = board.getBoard();
        
        @Override
        public void start (Stage primaryStage) throws FileNotFoundException
            {
                for (int i = 0; i < 8; i++)
                    {
                        for (int j = 0; j < 8; j++)
                            {
                                ImageView square = makeSquare(i, j);
                                boardView.add(square, i, 7 - j);
                                
                                Piece gamePiece = gameBoard[i][j];
                                if (gamePiece == null)
                                    {
                                        continue;
                                    }
                                boardView.add(gamePiece, i, 7 - j);
                                gamePiece.setOnMouseClicked(event -> {
                                    System.out.println(board.getTurn() + ", " + gamePiece.getColor());
                                    System.out.println(gamePiece);
                                    if (board.getTurn() == gamePiece.getColor())
                                        {
                                            board.selectPiece(gamePiece);
                                        }
                                    else
                                        {
                                            try
                                                {
                                                    handle(board.handle(gamePiece));
                                                }
                                            catch (IOException e)
                                                {
                                                    throw new RuntimeException(e);
                                                }
                                            System.out.println("Starting handle");
                                        }
                                });
                            }
                    }
                
                Scene scene = new Scene(boardView);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        
        private void handle (List<Piece> updatedPositions) throws IOException
            {
                System.out.println(updatedPositions);
                boardView.getChildren().removeAll(updatedPositions);
                for (Piece piece : updatedPositions)
                    {
                        int file = piece.getFile();
                        int rank = piece.getRank();
                        //boardView.getChildren().remove(file, 7 - rank);
                        //boardView.add(makeSquare(file, rank), file, 7 - rank);
                        boardView.add(piece, file, 7 - rank);
                    }
            }
        
        private ImageView makeSquare (int i, int j) throws FileNotFoundException
            {
                String squareFile = "src/main/resources/chess/chess/square";
                if ((i + j) % 2 == 0)
                    {
                        squareFile += Color.WHITE.toString();
                    }
                else
                    {
                        squareFile += Color.BLACK.toString();
                    }
                squareFile += ".png";
                
                ImageView square = new ImageView(new Image(new FileInputStream(squareFile)));
                return square;
            }
    }
