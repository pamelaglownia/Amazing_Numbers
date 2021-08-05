package pl.glownia.pamela;

public enum Properties {
    BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY, JUMPING;

    boolean equals(String userProperty) {
        return this.name().equalsIgnoreCase(userProperty);
    }
}
