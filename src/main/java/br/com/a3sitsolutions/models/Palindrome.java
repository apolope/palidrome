package br.com.a3sitsolutions.models;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

@Data
@EqualsAndHashCode(callSuper = false)
@MongoEntity(collection = "palindrome")
public class Palindrome {

    public ObjectId id;
    private String palindrome;
    private ObjectId matrix;
}
