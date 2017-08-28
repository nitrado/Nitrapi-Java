package net.nitrado.api.common;

public class Value {
    private String value;

    public Value(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Value && value.equals(((Value) obj).getValue());
    }
}
