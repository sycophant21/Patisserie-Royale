package com.dao;

import com.domain.classes.Dish;
import com.domain.id.DishID;
import com.domain.id.UserID;
import com.helper.DishPreference;
import com.helper.Operator;
import com.helper.Serializer;
import com.helper.filters.Filter;
import com.manager.DishFilterManager;
import com.mongo.MongoOperations;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DishDAO {
    private final MongoOperations mongoOperations;
    private final Serializer serializer;
    private final DishFilterManager dishFilterManager;

    public DishDAO(MongoOperations mongoOperations, Serializer serializer, DishFilterManager dishFilterManager) {
        this.mongoOperations = mongoOperations;
        this.serializer = serializer;
        this.dishFilterManager = dishFilterManager;
    }

    public List<Dish> getDishesFromIngredients(DishPreference dishPreference) {
        Filter filter = dishFilterManager.getPreferencesFilter(dishPreference);
        return getDishes(filter);

    }

    public List<Dish> getDishesFromName(String dishName) {
        Filter filter = dishFilterManager.getFilter(Operator.EQ, "dishName", dishName);
        return getDishes(filter);
    }

    private List<Dish> getDishes(Filter filter) {
        Iterator<Document> documentIterator = mongoOperations.get("Dishes", filter);
        List<Dish> dishes = new ArrayList<>();
        while (documentIterator.hasNext()) {
            Document document = documentIterator.next();
            document.remove("_id");
            dishes.add((Dish) serializer.deserialize(serializer.serialize(document), Dish.class));
        }
        return dishes;
    }

    public Dish getDishesFromDishID(DishID dishID) {
        Filter filter = dishFilterManager.getFilter(Operator.EQ, "dishID.dishID", dishID.getDishId());
        Iterator<Document> documentIterator = mongoOperations.get("Dishes", filter);
        return (Dish) serializer.deserialize(serializer.serialize(documentIterator.next()), Dish.class);
    }

    public List<Dish> getDishesFromUserID(UserID userID) {
        Filter filter = dishFilterManager.getFilter(Operator.EQ, "uploadedBy.userID", userID.getUserId());
        return getDishes(filter);
    }

    public boolean createDish(Dish dish) {
        return mongoOperations.create(serializer.serialize(dish), "Dishes");
    }

}
