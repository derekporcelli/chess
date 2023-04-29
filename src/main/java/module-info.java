module chess.chess {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires org.controlsfx.controls;
    
    opens chess.chess to javafx.fxml;
    exports chess.chess;
}