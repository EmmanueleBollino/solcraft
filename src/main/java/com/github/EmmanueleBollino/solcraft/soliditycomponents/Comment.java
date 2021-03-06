package com.github.EmmanueleBollino.solcraft.soliditycomponents;

import java.util.Objects;

/**
 * Represents a comment in Solidity.
 * A comment can be single line, multi line and documentation.
 */
public class Comment extends Statement {
    private String comment;
    private boolean documentation;

    public Comment(String comment, boolean documentation) {
        super(comment);
        Objects.requireNonNull(comment);
        this.comment = comment.trim();
        this.documentation = documentation;
    }

    public Comment(String comment) {
        this(comment, false);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDocumentation() {
        return documentation;
    }

    public void setDocumentation(boolean documentation) {
        this.documentation = documentation;
    }

    public void append(String comment) {
        this.comment = this.comment + comment.trim();
    }

    public void appendLine(String comment) {
        this.append("\n" + comment);
    }

    public boolean isSingleLine() {
        return !this.comment.contains("\n") && !isDocumentation();
    }

    public void transformInSingleLine() {
        this.documentation = false;
        this.comment = this.comment.replace("\n", " ");
    }

    @Override
    public String print() {
        if (this.documentation)
            return "/**\n * "
                    + this.comment
                    .replace("*/", "")
                    .replace("\n", "\n * ")
                    + "\n */";

        if (!this.comment.contains("\n"))
            return "// " + this.comment;

        return "/* " + this.comment.replace("*/", "") + " */";
    }
}
