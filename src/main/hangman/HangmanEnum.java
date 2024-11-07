package main.hangman;

public enum HangmanEnum {
    BASE("base.txt"),
    HEAD("head.txt"),
    BODY("body.txt"),
    LEFT_HAND("left_hand.txt"),
    RIGHT_HAND("right_hand.txt"),
    LEFT_LEG("left_leg.txt"),
    RIGHT_LEG("right_leg.txt");

    private final String filename;

    HangmanEnum(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public HangmanEnum getNextStage() {
        int nextIndex = this.ordinal() + 1;
        HangmanEnum[] stages = HangmanEnum.values();
        return stages[nextIndex];
    }
}
