package com.manager;

import com.helper.DishPreference;
import com.helper.Ingredient;
import com.helper.Operator;
import com.helper.filters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DishFilterManager {
    public Filter getPreferencesFilter(DishPreference dishPreference) {
        List<Filter> filters = new ArrayList<>();
        List<Filter> preferredIngredientsFilter = getSimpleFilter(dishPreference.getPreferredIngredients(), true);
        List<Filter> nonPreferredIngredientsFilter = getSimpleFilter(dishPreference.getNotPreferredIngredients(), false);

        if (preferredIngredientsFilter != null && preferredIngredientsFilter.size() > 1) {
            filters.add(new AndFilter(preferredIngredientsFilter));
        }
        else {
            if (preferredIngredientsFilter != null && preferredIngredientsFilter.size() != 0) {
                filters.addAll(preferredIngredientsFilter);
            }
        }
        if (nonPreferredIngredientsFilter != null && nonPreferredIngredientsFilter.size() > 1) {
            filters.add(new AndFilter(nonPreferredIngredientsFilter));
        }
        else {
            if (nonPreferredIngredientsFilter != null && nonPreferredIngredientsFilter.size() != 0) {
                filters.addAll(nonPreferredIngredientsFilter);
            }
        }
        //filters.removeIf(filter -> ((OrFilter) filter).getFilters() == null);
        if (filters.size() > 1) {
            return new AndFilter(filters);
        }
        else {
            return filters.get(0);
        }
    }

    private List<Filter> getSimpleFilter(Set<Ingredient> ingredients, boolean equals) {
        if (ingredients != null && ingredients.size() != 0) {
            List<Filter> simpleFilters = new ArrayList<>();
            Operator operator = Operator.NIN;
            if (equals) {
                operator = Operator.IN;
            }
            for (Ingredient ingredient : ingredients) {
                simpleFilters.add(new SimpleFilter(operator, "dishRecipe.ingredientSet", ingredient.getString()));
            }
            return simpleFilters;
        }
        return null;
    }

    public Filter getFilter(Operator operator, String fieldPath, Object value) {
        return new SimpleFilter(operator, fieldPath, value);
    }
}
