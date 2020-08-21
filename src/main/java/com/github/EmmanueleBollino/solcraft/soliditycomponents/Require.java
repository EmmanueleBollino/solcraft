package com.github.EmmanueleBollino.solcraft.soliditycomponents;

public class Require extends Statement {
    private final Condition condition;
    private final String errorMessage;

    public Require(Condition condition, String errorMessage) {
        super("require(" + condition.print() + ", \"" + errorMessage + "\");");
        this.condition = condition;
        this.errorMessage = errorMessage;
    }

    public Require(Condition condition) {
        this(condition, "Error!");
    }

    public Condition getCondition() {
        return condition;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
