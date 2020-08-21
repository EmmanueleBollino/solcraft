package com.github.EmmanueleBollino.solcraft.soliditycomponents;

import java.nio.file.Path;

public class Import implements SolidityComponent {
    private final String path;

    public Import(String path) {
        this.path = path;
    }

    public Import(Path path) {
        this(path.toString());
    }

    public String getPath() {
        return path;
    }

    @Override
    public String print() {
        return "import \"" + path + "\";";
    }
}
