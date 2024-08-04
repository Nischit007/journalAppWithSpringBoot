package net.engineeringdigest.journalApp.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{

    User findByUserName(String name);
     
}