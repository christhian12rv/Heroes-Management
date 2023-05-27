package br.com.gubee.interview.enums;

public enum Race {
    HUMAN("HUMAN"),
    ALIEN("ALIEN"),
    DIVINE("DIVINE"),
    CYBORG("CYBORG");

    private String value;

    Race(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
