package fr.blockincraft.blocksbazaar.mech;

public abstract class Action<T extends ActionData> {
    public static final Action<ActionData> GET_ACTIVE_ORDERS = new Action<>("GET ACTIVE ORDERS ACTION") {
        @Override
        public boolean tick(ActionData data) {
            return false;
        }
    };

    private final String name;

    private Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean tick(T data);
}
