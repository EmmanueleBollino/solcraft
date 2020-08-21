package com.github.EmmanueleBollino.solcraft.soliditycomponents;

/**
 * General interface for a Solidity component.
 * A component is a class that can be printed.
 */
public interface SolidityComponent {
    /**
     * Default indentation string in Solidity.
     */
    String DEFAULT_INDENTATION = "    ";

    /**
     * Prints the solidity component.
     *
     * @return a string representing the printed solidity component
     */
    String print();

    /**
     * Prints the solidity component with indentation.
     *
     * @param indentationLevel indentation level to print the component with
     * @return a string representing the printed solidity component with the given indentation level
     * @throws IllegalArgumentException if the given indentation level is negative
     */
    default String printWithIndentation(int indentationLevel) {
        if (indentationLevel < 0) throw new IllegalArgumentException("Indentation level can't be negative");

        StringBuilder toPrint = new StringBuilder(this.print());
        StringBuilder indentation = new StringBuilder();

        for (int i = 0; i < indentationLevel; i++)
            indentation.append(DEFAULT_INDENTATION);

        toPrint.insert(0, indentation.toString());
        return toPrint.toString().replace("\n", "\n" + indentation.toString());
    }
}
