package com.github.EmmanueleBollino.solcraft.soliditycomponents;

public class SPDXLicense extends Comment {
    private static final String IDENTIFIER = "SPDX-License-Identifier:";

    public SPDXLicense(Licenses license) {
        super(IDENTIFIER + " " + license.print());
    }

    public SPDXLicense() {
        this(Licenses.UNLICENSED);
    }

    public enum Licenses implements SolidityComponent {
        UNLICENSED,
        MIT;

        @Override
        public String print() {
            return this.name();
        }
    }
}
