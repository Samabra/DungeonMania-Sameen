package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.logic.Switch;

public class BouldersGoal implements Goal {
    public BouldersGoal() {
    }

    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        return game.getMap().getEntities(Switch.class).stream().allMatch(s -> s.isActivated());

    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":boulders";
    };
}
