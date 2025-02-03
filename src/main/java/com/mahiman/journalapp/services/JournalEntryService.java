package com.mahiman.journalapp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mahiman.journalapp.entity.JournalEntry;
import com.mahiman.journalapp.entity.User;
import com.mahiman.journalapp.repository.JournalEntryRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private UserService userService;
    @Autowired  // this is dependency injection we are injecting journalEntryRepository in this service 
    private JournalEntryRepository journalEntryRepository;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        // here we are calling the save method of the repository to save the entry in the database
        try {
            User user = userService.findByUsername(username);
            // here we are getting the user by username
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved); // here we are adding the saved entry to the user's journal entries
           
            userService.saveEntry(user); // here we are saving the user

        } catch (Exception e) {
            log.error("Error while saving journal entry", e);
            throw e;
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
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

    public void deleteById(ObjectId id, String username) {
        // boolean removed = false;
        // here we are calling the deleteById method of the repository to delete the entry from the database
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));

        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}
// controller calls--> service calls --> repository
