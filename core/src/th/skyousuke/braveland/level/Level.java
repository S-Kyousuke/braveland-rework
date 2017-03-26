package th.skyousuke.braveland.level;

/**
 * Created by Bill on 24/3/2560.
 */
public enum Level {

    INTRO(new Intro()),
    TOWN(new Town()),
    ONE(new One()),
    TWO(new Two()),
    THREE(new Three()),
    FOUR(new Four()),
    FIVE(new Five()),
    SIX(new Six()),
    BOSS(new Boss());

    private AbstractLevel level;

    private static Level[] values = Level.values();

    Level(AbstractLevel level) {
        this.level = level;
    }

    public AbstractLevel getLevel() {
        return level;
    }

    public Level getNext() {
        return values[(this.ordinal() + 1) % values.length];
    }
}
