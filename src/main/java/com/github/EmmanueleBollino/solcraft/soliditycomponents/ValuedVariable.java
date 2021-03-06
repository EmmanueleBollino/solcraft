package com.github.EmmanueleBollino.solcraft.soliditycomponents;

public class ValuedVariable extends Variable {
    private Value value;

    public ValuedVariable(String name, Type type, Value value) {
        super(name, type);
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Statement assignment() {
        return super.assignment(this.value);
    }

    @Override
    public String declaration() {
        return (this.getType().print() + " "
                + this.getVisibility().print() + " "
                + (this.getLocation().equals(Location.NONE) ? "" : (getLocation().print() + " "))
                + this.getName()
                + (this.value == null ? "" : (" = " + this.value.print()))
                + ";")
                .replaceAll("  ", " ");
    }
}
