package net.engineeringdigest.journalApp.entity;



import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection= "users")
public class User{
    @Id
  
    private ObjectId id;

  @Indexed(unique = true)
  @NonNull
  private String userName;
  @NonNull
  private String password;

  @DBRef
  private List<JournalEntry> journalEntry=new ArrayList<>();


  private List<String> roles;

    public List<String> getRoles() {
    return roles;
}

public void setRoles(List<String> roles) {
    this.roles = roles;
}

    public void setid(ObjectId id){
        this.id=id;
    }

    public ObjectId getid(){
        return this.id;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntry;
    }

    public void setJournalEntries(List<JournalEntry> journalEntry) {
        this.journalEntry = journalEntry;
    }
    
}
