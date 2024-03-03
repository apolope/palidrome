package br.com.a3sitsolutions.models;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "palindrome")
public class Palindrome {

    public ObjectId id;
    private String palindrome;
    private ObjectId matrix;
}
