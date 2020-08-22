package com.github.EmmanueleBollino.solcraft.standardizedcomponents;

import com.github.EmmanueleBollino.solcraft.soliditycomponents.*;

import java.util.List;

/**
 * Represents the most commonly used for cycle. It starts from 0 and goes to <i>n-1</i> with a unitary increment.
 */
public class PredefinedFor extends For {
    public PredefinedFor(Value to) {
        this(to, List.of());
    }

    public PredefinedFor(Value to, List<Statement> statements) {
        this("i", to, statements);
    }

    public PredefinedFor(String iteratorVariableName, Value to, List<Statement> statements) {
        super(
                new ValuedVariable(iteratorVariableName, new Type(Type.BaseTypes.UINT), new Value("0")),
                new Condition(iteratorVariableName + " < " + to.print()),
                new Statement(iteratorVariableName + "++"),
                statements
        );
    }
}
