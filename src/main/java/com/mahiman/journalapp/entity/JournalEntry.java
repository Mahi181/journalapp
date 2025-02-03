package com.mahiman.journalapp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document (collection="journal_entries") 
@Data
@NoArgsConstructor
// need to map this with collection in the database according to ORM so journal entry will be marked a document it will be like row in mongodb.....means journal entry instance will be similar to row
public class JournalEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    
}
