package dungeonmania.entities.buildables;

public class Sceptre extends Buildable {
    private static final int MIND_CONTROL_DURATION = 2;

    public Sceptre() {
        super(null);
    }

    public int getMindControlDuration() {
        return MIND_CONTROL_DURATION;
    }
}
