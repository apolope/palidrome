package br.com.a3sitsolutions.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Converter
public class MatrixConverter implements AttributeConverter<List<List<Character>>, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<List<Character>> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter a lista para JSON", e);
        }
    }

    @Override
    public List<List<Character>> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<List<Character>>>(){});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para lista", e);
        }
    }
}

