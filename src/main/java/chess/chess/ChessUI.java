package chess.chess;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
        
        private final GridPane boardView = new GridPane();
        private final Board board = new Board();
        private final Piece[][] gameBoard = board.getBoard();
        
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
                                gamePiece.setOnMouseClicked(event -> input(gamePiece));
                            }
                    }
                
                Scene scene = new Scene(boardView);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
    
        private void input (Piece gamePiece)
            {
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
                    }
            }
    
        private void handle (List<Piece> updatedPositions) throws IOException
            {
                boardView.getChildren().removeAll(updatedPositions);
                for (Piece piece : updatedPositions)
                    {
                        int file = piece.getFile();
                        int rank = piece.getRank();
                        Node nodeToRemove = null;
                        for (Node node : boardView.getChildren()) {
                            if (GridPane.getRowIndex(node) == 7 - rank && GridPane.getColumnIndex(node) == file) {
                                nodeToRemove = node;
                                break;
                            }
                        }
    
                        if (nodeToRemove != null) {
                            boardView.getChildren().remove(nodeToRemove);
                        }
                        //boardView.getChildren().remove(file, 7 - rank);
                        boardView.add(makeSquare(file, rank), file, 7 - rank);
                        boardView.add(piece, file, 7 - rank);
                        piece.setOnMouseClicked(event -> input(piece));
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
    
                return new ImageView(new Image(new FileInputStream(squareFile)));
            }
    }
