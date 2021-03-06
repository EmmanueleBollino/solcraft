package com.github.EmmanueleBollino.solcraft.soliditycomponents;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Function extends Operation implements StatementContainer {
    private final List<Variable> returned;
    private final Map<Modifier, List<Value>> modifiers;
    private Markers marker;
    private Comment comment;
    //TODO add override

    public Function(String name, Visibility visibility, List<Variable> parameters, List<Statement> statements, boolean isAbstract, List<Variable> returned, Markers marker) {
        super(name, visibility, parameters, statements, isAbstract);
        this.returned = new LinkedList<>(returned);
        this.marker = marker;
        this.modifiers = new LinkedHashMap<>();
    }

    public Function(String name) {
        super(name);
        this.returned = new LinkedList<>();
        this.modifiers = new LinkedHashMap<>();
    }

    public void addModifier(Modifier modifier, Value... values) {
        this.modifiers.put(modifier, List.of(values));
    }

    public void addModifier(Modifier modifier, List values) {
        this.modifiers.put(modifier, new LinkedList<>(values));
    }

    public void addReturned(Variable returned) {
        this.returned.add(returned);
    }

    public List<Variable> getReturned() {
        return returned;
    }

    public Map<Modifier, List<Value>> getModifiers() {
        return modifiers;
    }

    public Markers getMarker() {
        return marker;
    }

    public void setMarker(Markers marker) {
        this.marker = marker;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String print() {
        StringBuilder toPrint = new StringBuilder();
        //comment
        if (this.comment != null) toPrint.append(this.comment.print() + "\n");
        //declaration
        toPrint.append("function " + this.getName() + "(");
        //parameters
        this.getParameters().forEach(el -> toPrint.append(el.getType().print() + " " + (el.getLocation().equals(Variable.Location.NONE) ? "" : (el.getLocation().print() + " ")) + el.getName() + ", "));
        if (this.getParameters().size() > 0)
            toPrint.setLength(toPrint.length() - 2);
        //special modifiers
        toPrint
                .append(") ")
                .append(this.getVisibility().print())
                .append(this.isAbstract() ? " virtual " : " ")
                .append(this.marker == null ? "" : (this.marker.print() + " "));
        //modifiers
        this.modifiers.entrySet()
                .forEach(el -> toPrint.append(el.getKey().invocation(el.getValue().toArray(new Value[0])) + " "));
        //returned values
        if (this.returned.size() > 0) {
            toPrint.append("returns(");
            this.returned.forEach(el -> toPrint.append(el.getType().print() + " " + (el.getLocation().equals(Variable.Location.NONE) ? "" : (el.getLocation().print() + " ")) + el.getName() + ", "));
            toPrint.setLength(toPrint.length() - 2);
            toPrint.append(") ");
        }
        toPrint.append("{\n");
        //statements body
        this.getStatements().forEach(el -> toPrint.append(el.printWithIndentation(1) + "\n"));
        toPrint.append("}");

        return toPrint.toString();
    }

    @Override
    public String invocation(Value... values) {
        StringBuilder toPrint = new StringBuilder(this.getName() + "(");
        Stream.of(values).forEach(el -> toPrint.append(el.print() + ", "));
        if (values.length > 0)
            toPrint.setLength(toPrint.length() - 2);
        toPrint.append(");");
        return toPrint.toString();
    }

    public enum Markers implements SolidityComponent {
        PURE,
        VIEW,
        PAYABLE;

        @Override
        public String print() {
            return this.name().toLowerCase();
        }
    }
}