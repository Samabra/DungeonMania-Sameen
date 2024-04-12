package dungeonmania.entities.logic.activateStrategy;

public class ActivateFactory {
    public static ActivateStrategy factory(String logic) {
        switch (logic) {
        case "or":
            return new OrActivate();
        case "and":
            return new AndActivate();
        case "xor":
            return new XorActivate();
        case "co_and":
            return new CoandActivate();
        default:
            return null;
        }
    }
}
