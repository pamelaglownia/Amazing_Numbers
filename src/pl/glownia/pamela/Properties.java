package pl.glownia.pamela;

public enum Properties {
    BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SQUARE, SUNNY;

    boolean equals(String userProperty) {
        return this.name().equalsIgnoreCase(userProperty);
    }

    static boolean checkIfContainsProps(String userProperty) {
        int counter = 0;
        for (Properties props : Properties.values()) {
            if (!props.equals(userProperty)) {
                counter += 1;
            }
        }
        if (counter == Properties.values().length) {
            return false;
        } else {
            return true;
        }
    }
}
