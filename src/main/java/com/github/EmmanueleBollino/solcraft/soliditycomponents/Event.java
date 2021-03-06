package com.github.EmmanueleBollino.solcraft.soliditycomponents;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Event implements SolidityComponent, Invokable {
    private final List<Variable> parameters;
    private String name;
    private Comment comment;

    public Event(String name, List<Variable> parameters) {
        this.name = name;
        this.parameters = new LinkedList<>(parameters);
        this.parameters.forEach(el -> el.setName("_" + el.getName()));
    }

    public Event(String name) {
        this(name, List.of());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Variable> getParameters() {
        return parameters;
    }

    public void addParameter(Variable parameter) {
        this.parameters.add(parameter);
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    protected String declaration() {
        StringBuilder toPrint = new StringBuilder("event " + this.name + "(");
        this.parameters.forEach(el -> toPrint.append(el.getType().print() + " " + el.getName() + ", "));
        if (this.parameters.size() > 0)
            toPrint.setLength(toPrint.length() - 2);
        toPrint.append(");");

        return toPrint.toString();
    }

    @Override
    public String print() {
        String toPrint = declaration();
        if (this.comment != null) {
            if (this.comment.isSingleLine())
                toPrint = toPrint + "\t" + this.comment.print();
            else
                toPrint = this.comment.print() + "\n" + toPrint;
        }

        return toPrint;
    }

    @Override
    public String invocation(Value... values) {
        StringBuilder toPrint = new StringBuilder("emit " + this.name + "(");
        Stream.of(values).forEach(el -> toPrint.append(el.print() + ", "));
        if (values.length > 0)
            toPrint.setLength(toPrint.length() - 2);
        toPrint.append(");");

        return toPrint.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return parameters.equals(event.parameters) &&
                name.equals(event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters, name);
    }
}