package com.dao;

import com.domain.classes.User;
import com.domain.id.UserID;
import com.helper.Operator;
import com.helper.Serializer;
import com.helper.filters.SimpleFilter;
import com.mongo.MongoOperations;
import org.bson.Document;

import java.util.Iterator;

public class UserDAO {
    private final MongoOperations mongoOperations;
    private final Serializer serializer;

    public UserDAO(MongoOperations mongoOperations, Serializer serializer) {
        this.mongoOperations = mongoOperations;
        this.serializer = serializer;
    }

    public User getUserFromUserID(UserID userID) {
        SimpleFilter simpleFilter = new SimpleFilter(Operator.EQ, "userID.userID", userID.getUserId());
        Iterator<Document> documentIterator = mongoOperations.get("Users", simpleFilter);
        User user = null;
        if (documentIterator.hasNext()) {
            user = (User) serializer.deserialize(serializer.serialize(documentIterator.next()), User.class);
        }
        return user;
    }

    public boolean createUser(User user) {
        return mongoOperations.create(serializer.serialize(user), "Users");
    }
}
