package dungeonmania.entities.buildables;

import dungeonmania.entities.BattleItem;
import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Durable;

public class Bow extends Buildable implements BattleItem, Durable {
    private int durability;

    public Bow(int durability) {
        super(null);
        this.durability = durability;
    }

    @Override
    public void use(Game game) {
        durability--;
        if (durability <= 0) {
            game.getPlayer().remove(this);
        }
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }

    @Override
    public int getDurability() {
        return durability;
    }
}
