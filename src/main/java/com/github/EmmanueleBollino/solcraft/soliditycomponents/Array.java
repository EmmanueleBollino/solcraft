package com.github.EmmanueleBollino.solcraft.soliditycomponents;

import java.util.stream.Stream;

public class Array extends Type {
    private final Integer[] dimensions;

    public Array(Type type, Integer... dimensions) {
        super(type.print());
        this.dimensions = dimensions;
    }

    @Override
    public String print() {
        StringBuilder typeName = new StringBuilder(super.print());
        Stream.of(dimensions)
                .map(el -> "[" + (el == null ? "" : el) + "]")
                .forEach(typeName::append);

        return typeName.toString();
    }
}
