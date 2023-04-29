package chess.chess;

public enum Color
    {
        WHITE,
        BLACK;
        
        public Color opposite ()
            {
                switch (this)
                    {
                        case WHITE ->
                            {
                                return BLACK;
                            }
                        case BLACK ->
                            {
                                return WHITE;
                            }
                        default ->
                            {
                                throw new AssertionError("Unknown direction: " + this);
                            }
                    }
            }
    }
