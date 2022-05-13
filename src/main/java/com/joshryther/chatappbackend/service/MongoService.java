package com.joshryther.chatappbackend.service;

import com.joshryther.chatappbackend.model.DBUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MongoService {

    MongoTemplate mongoTemplate;
    String collection = "users";

    @Autowired
    public MongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<DBUser> findAllUsers() {
        Query query = new Query();
        return mongoTemplate.find(query, DBUser.class);
    }

    public DBUser findUserByID(int uid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(uid));
        List<DBUser> list = mongoTemplate.find(query, DBUser.class);
        if (list.size() > 1) {
            throw new IllegalStateException("More than one match found");
        }
        return list.get(0);
    }

    public DBUser findUserByName(String firstName, String lastName) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("firstName").is(firstName),
                Criteria.where("lastName").is(lastName));
        query.addCriteria(criteria);

        List<DBUser> list = mongoTemplate.find(query, DBUser.class);
        if (list.size() > 1) {
            throw new IllegalStateException("More than one match found");
        }
        return list.get(0);
    }

    public DBUser findUserByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<DBUser> list = mongoTemplate.find(query, DBUser.class);
        if (list.size() > 1) {
            throw new IllegalStateException("More than one match found");
        }
        else if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public int findNextUid() {
        List<DBUser> list = mongoTemplate.find(new Query(), DBUser.class);
        int lastUid = list.get(list.size() - 1).getUid() + 1;
        return lastUid;
    }

    //TODO: Implement addUser
    public void addUser(DBUser dbUser) {
        mongoTemplate.insert(dbUser);
    }

    //TODO: Implement deleteUser
    public void deleteUser(DBUser dbUser) {
        mongoTemplate.remove(dbUser, collection);
    }

    public void updateUser(DBUser dbUserOld, DBUser dbUserNew) {
        mongoTemplate.remove(dbUserOld, collection);
        mongoTemplate.insert(dbUserNew, collection);
    }


}
