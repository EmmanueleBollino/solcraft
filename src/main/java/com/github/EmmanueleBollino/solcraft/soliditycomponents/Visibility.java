package com.github.EmmanueleBollino.solcraft.soliditycomponents;

public enum Visibility implements SolidityComponent {
    NONE, PUBLIC, PRIVATE, EXTERNAL, INTERNAL;

    public String print() {
        return this.equals(Visibility.NONE) ? "" : this.name().toLowerCase();
    }
}
