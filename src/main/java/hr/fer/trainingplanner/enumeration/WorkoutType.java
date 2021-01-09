package hr.fer.trainingplanner.enumeration;

public enum WorkoutType {

    AMRAP(1L, "AMRAP"),
    EMOM(2L, "EMOM"),
    FOR_TIME(3L, "For time"),
    NORMAL(4L, "Normal"),
    SETS_AND_REPS(5L, "Sets and reps"),
    TABATA(6L, "Tabata");

    private final Long id;

    private final String name;

    WorkoutType(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public static WorkoutType getByName(final String name) {
        switch (name) {
            case "AMRAP":
                return WorkoutType.AMRAP;
            case "EMOM":
                return WorkoutType.EMOM;
            case "For time":
                return WorkoutType.FOR_TIME;
            case "Normal":
                return WorkoutType.NORMAL;
            case "Sets and reps":
                return WorkoutType.SETS_AND_REPS;
            default:
                return WorkoutType.TABATA;
        }
    }
}
