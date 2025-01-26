package com.mahiman.journalapp.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mahiman.journalapp.entity.JournalEntry;
import com.mahiman.journalapp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired  // this is dependency injection we are injecting journalEntryRepository in this service 
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        // here we are calling the save method of the repository to save the entry in the database
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        // here we are calling the findAll method of the repository to get all the entries from the database
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        // here we are calling the findById method of the repository to get the entry by id from the
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        // here we are calling the deleteById method of the repository to delete the entry from the database
        journalEntryRepository.deleteById(id);
    }

}
// controller calls--> service calls --> repository
