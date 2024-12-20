package com.fayupable.userservice.repository;

import com.fayupable.userservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<User, Long> {

    Optional<Object> findById(String id);

    void deleteById(String id);
}
