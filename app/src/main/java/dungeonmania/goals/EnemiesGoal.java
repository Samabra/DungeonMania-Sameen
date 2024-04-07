package dungeonmania.goals;

import dungeonmania.Game;

public class EnemiesGoal implements Goal {
    private int target;

    public EnemiesGoal(int target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        // no player then return false
        if (game.getPlayer() == null)
            return false;

        // if there is no target then assume it is achieved
        if (target == 0)
            return true;

        return game.getEnemiesKilledCount() >= target && game.getSpawnerCount() == 0;
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":enemies";
    }
}
