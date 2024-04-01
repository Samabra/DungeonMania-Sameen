package dungeonmania.goals;

public abstract class CompoundGoal implements Goal {
    private Goal goal1;
    private Goal goal2;

    public CompoundGoal(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public Goal getGoal1() {
        return this.goal1;
    }

    public Goal getGoal2() {
        return this.goal2;
    }
}
