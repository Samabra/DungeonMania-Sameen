package dungeonmania.entities;

import dungeonmania.Game;

public interface Durable {
    public void use(Game game);
    public int getDurability();
}
