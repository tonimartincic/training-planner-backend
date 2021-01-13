package hr.fer.trainingplanner.enumeration;

public enum WorkoutType {

    AMRAP(1L, "amrap"),
    EMOM(2L, "emom"),
    FOR_TIME(3L, "for time"),
    NORMAL(4L, "normal"),
    TABATA(5L, "tabata");

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
            case "amrap":
                return WorkoutType.AMRAP;
            case "emom":
                return WorkoutType.EMOM;
            case "forTime":
                return WorkoutType.FOR_TIME;
            case "normal":
                return WorkoutType.NORMAL;
            default:
                return WorkoutType.TABATA;
        }
    }
}
