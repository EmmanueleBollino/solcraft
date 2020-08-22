package com.github.EmmanueleBollino.solcraft.soliditycomponents;

/**
 * Represents a condition in Solidity.
 */
public class Condition implements SolidityComponent {
    private String condition;

    public Condition(String condition) {
        this.condition = condition.trim();

        //simplify ==/!= true/false conditions
        if (condition.split(" ").length == 3) {
            String[] parts = condition.split(" ");
            if (parts[1].equals("==")) {
                if (parts[2].equals("true"))
                    this.condition = parts[0];
                else if (parts[2].equals("false"))
                    this.condition = "!" + parts[0];
            } else if (parts[1].equals("!=")) {
                if (parts[2].equals("true"))
                    this.condition = "!" + parts[0];
                else if (parts[2].equals("false"))
                    this.condition = parts[0];
            }
        }
    }

    /**
     * Creates a unary condition with the operator following the operand.
     *
     * @param condition operand that can be a condition
     * @param operator  operator
     */
    public Condition(Condition condition, String operator) {
        this(condition.print() + operator);
    }

    /**
     * Creates a unary condition with the operand following the operator.
     *
     * @param condition operand that can be a condition
     * @param operator  operator
     */
    public Condition(String operator, Condition condition) {
        this(operator + condition.print());
    }

    /**
     * Constructs a binary condition.
     *
     * @param firstCondition  first operand that can be a condition
     * @param operator        operator
     * @param secondCondition second operand that can be a condition
     */
    public Condition(Condition firstCondition, String operator, Condition secondCondition) {
        this(firstCondition.print() + " " + operator + " " + secondCondition.print());
    }

    @Override
    public String print() {
        return this.condition;
    }
}
