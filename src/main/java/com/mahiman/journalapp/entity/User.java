package com.mahiman.journalapp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Builder;
import lombok.Data;

@Document (collection="users") 
@Data
@Builder
// need to map this with collection in the database according to ORM so journal entry will be marked a document it will be like row in mongodb.....means journal entry instance will be similar to row
public class User {

   
    @Id
    private ObjectId id;
    @Indexed(unique=true)  //index wont be automatically created so to create automatic we add spring.data.mongodb.auto-index-creation=true 
    @NonNull
    private String username;
    @NonNull
    private String password;

    @DBRef // we are creating reference of journal entries in user collection
    private List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> roles; // we are creating roles for user
}
