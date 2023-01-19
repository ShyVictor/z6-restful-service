package dev.shyauroratime.z6.api.model;

public enum TransactionType {
    SHOP("shopping"),
    ADMIN("admin"),
    CREATED("created"),
    DEPOSIT("deposit");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionType fromValue(String value) {
        for (TransactionType type : TransactionType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TransactionType value: " + value);
    }
}
