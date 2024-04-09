package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.BattleItem;
public class MidnightArmour extends Buildable implements BattleItem{
    private double attack;
    private double defence;
    private int durability;

    public MidnightArmour(double attack, double defence) {
        super(null);
        this.attack = attack;
        this.defence = defence;
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
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, attack, defence, 0, 0));
    }

    @Override
    public int getDurability() {
        return durability;
    }
}
