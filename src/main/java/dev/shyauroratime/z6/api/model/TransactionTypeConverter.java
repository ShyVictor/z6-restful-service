package dev.shyauroratime.z6.api.model;

import javax.persistence.AttributeConverter;

public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {

    @Override
    public String convertToDatabaseColumn(TransactionType attribute) {
        return attribute.getValue();
    }

    @Override
    public TransactionType convertToEntityAttribute(String dbData) {
        return TransactionType.fromValue(dbData);
    }
}
