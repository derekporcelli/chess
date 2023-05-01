package chess.chess;

public enum Color
    {
        EMPTY,
        WHITE,
        BLACK;
        
        public Color opposite ()
            {
                switch (this)
                    {
                        case EMPTY ->
                            {
                                return EMPTY;
                            }
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
                                throw new AssertionError("Unknown color: " + this);
                            }
                    }
            }
        
        public int getPawnMove ()
            {
                switch (this)
                    {
                        case WHITE ->
                            {
                                return 1;
                            }
                        case BLACK ->
                            {
                                return - 1;
                            }
                        default ->
                            {
                                return 0;
                            }
                    }
            }
    }
