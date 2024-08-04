// package net.engineeringdigest.journalApp.controller;

// import net.engineeringdigest.journalApp.entity.JournalEntry;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/_journal")
// public class JournalEntryController {

//     private Map<Long,JournalEntry> journalEntries=new HashMap<>();

//     @GetMapping
//     public List<JournalEntry> getAll(){
//         return new ArrayList<>(journalEntries.values());
//     }
    
//     @PostMapping
//     public boolean createEntry(@RequestBody JournalEntry myentry){
//         journalEntries.put(myentry.getid(),myentry);
//         return true;
//     }

//     @GetMapping("/id/{myid}")
//     public JournalEntry getJournalEntryById(@PathVariable Long myid){
//         return journalEntries.get(myid);

//     }

//     @DeleteMapping("/id/{myid}")
//     public JournalEntry deleteJournalEntryById(@PathVariable Long myid){
//        return journalEntries.remove(myid);
        
//     }

//     @PutMapping("/id/{myid}")
//     public JournalEntry updateJournalEntryByid(@PathVariable long myid,@RequestBody JournalEntry myentry){
//         return journalEntries.put(myid,myentry);

//     }
// }
