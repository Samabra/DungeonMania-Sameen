package dungeonmania.entities.buildables;

public class Sceptre extends Buildable {
    private int duration;

    public Sceptre(int duration) {
        super(null);
        this.duration = duration;
    }

    public int getMindControlDuration() {
        return duration;
    }
}
