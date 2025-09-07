package com.rbhagat32.blog.repositories;

import com.rbhagat32.blog.models.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, ObjectId> {
    UserModel findByUsername(String username);
}