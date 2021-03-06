package com.github.EmmanueleBollino.solcraft.soliditycomponents;

import java.util.*;

public class Contract implements Extendable {
    private String name;
    private Constructor constructor;
    private final Map<Extendable, List<Value>> extendeds;
    private final Set<Variable> attributes;
    private final Set<Function> functions;
    private final Set<Modifier> modifiers;
    private final Set<Event> events;
    private final Set<Declarable> declarations;
    private boolean isAbstract;
    private Comment comment;

    public Contract(String name, Constructor constructor, Collection<Variable> attributes,
                    Collection<Function> functions, Collection<Modifier> modifiers, Collection<Event> events, Collection<Declarable> declarations) {
        this.name = name;
        this.constructor = constructor;
        this.attributes = new LinkedHashSet<>(attributes);
        this.functions = new LinkedHashSet<>(functions);
        this.modifiers = new LinkedHashSet<>(modifiers);
        this.events = new LinkedHashSet<>(events);
        this.declarations = new LinkedHashSet<>(declarations);
        this.extendeds = new LinkedHashMap<>();
    }

    public Contract(String name) {
        this(name, null, Set.of(), Set.of(), Set.of(), Set.of(), Set.of());
    }

    public boolean addAttribute(Variable attribute) {
        return attributes.add(attribute);
    }

    public boolean addFunction(Function function) {
        return functions.add(function);
    }

    public boolean addModifier(Modifier modifier) {
        return modifiers.add(modifier);
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }

    public boolean addDeclaration(Declarable declaration) {
        return declarations.add(declaration);
    }

    public void addExtended(Extendable toExtend, Value... variables) {
        extendeds.put(toExtend, List.of(variables));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public Map<Extendable, List<Value>> getExtendeds() {
        return extendeds;
    }

    public Collection<Function> getFunctions() {
        return functions;
    }

    public Collection<Modifier> getModifiers() {
        return modifiers;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public Collection<Declarable> getDeclarations() {
        return this.declarations;
    }

    public Collection<Variable> getAttributes() {
        return attributes;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String print() {
        StringBuilder toPrint = new StringBuilder();
        if (this.comment != null) toPrint.append(this.comment.print() + "\n");
        if (this.isAbstract) toPrint.append("abstract ");
        toPrint.append("contract " + this.name);
        if (!this.extendeds.isEmpty()) {
            toPrint.append(" is ");
            this.extendeds.forEach((key, value) -> toPrint.append(key.invocation(value.toArray(new Value[0])) + ", "));
            toPrint.setLength(toPrint.length() - 2);
        }
        toPrint.append(" {\n");
        this.declarations.forEach(el -> toPrint.append(el.declarationWithIndentation(1) + "\n\n"));
        this.attributes.forEach(el -> toPrint.append(el.printWithIndentation(1) + "\n"));
        if (!this.attributes.isEmpty()) toPrint.append("\n");
        this.events.forEach(el -> toPrint.append(el.printWithIndentation(1) + "\n"));
        if (!this.events.isEmpty()) toPrint.append("\n");
        if (this.constructor != null) toPrint.append(this.constructor.printWithIndentation(1) + "\n\n");
        this.modifiers.forEach(el -> toPrint.append(el.printWithIndentation(1) + "\n\n"));
        if (!this.modifiers.isEmpty()) toPrint.append("\n");
        this.functions.forEach(el -> toPrint.append(el.printWithIndentation(1) + "\n\n"));

        return toPrint.toString().trim() + "\n}";
    }

    @Override
    public String invocation(Value... values) {
        return this.constructor == null ? this.name : this.constructor.invocation(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return name.equals(contract.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
