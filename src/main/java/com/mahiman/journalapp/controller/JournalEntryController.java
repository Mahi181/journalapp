package com.mahiman.journalapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahiman.journalapp.entity.JournalEntry;
import com.mahiman.journalapp.services.JournalEntryService;

@RestController
@RequestMapping("/journal")  //this add mapping to the below entire class we can call any method by hitting this endpoint
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    //this is a dependency injection where we are injecting the service class into the controller class
    @GetMapping
    public List<JournalEntry> getAll() {  //localhost:3000/journal  with this url if we do GET we come here 
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntries(@RequestBody JournalEntry myEntry) { //localhost:3000/journal  with this url if we do POST we come here with same URL
        // for (JournalEntry entry : myEntry) {
        //     journalEntryService.saveEntry(entry);
        // }
        // return true;
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;

    }

    @GetMapping("/id/{myId}")
    public JournalEntry getEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
     JournalEntry old= journalEntryService.findById(myId).orElse(null);
     if(old!=null) {
        old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("" ) ? newEntry.getTitle():old.getTitle());
        old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("" ) ? newEntry.getContent():old.getContent());
        
    }
        journalEntryService.saveEntry(old);
        return old;
    }

}
