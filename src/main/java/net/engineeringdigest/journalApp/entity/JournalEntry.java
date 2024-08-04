package net.engineeringdigest.journalApp.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection= "Journal_Entries")
public class JournalEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private  String content;
    private LocalDateTime date;

    public void setDate(LocalDateTime date){
        this.date=date;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public void setid(ObjectId id){
        this.id=id;
    }

    public ObjectId getid(){
        return this.id;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setContent(String content){
        this.content=content;
    }

    public String getContent(){
        return this.content;
    }
}
