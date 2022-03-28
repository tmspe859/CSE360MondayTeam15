import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<MenuItem>();
    }

    public Boolean addItem(MenuItem newItem) {
        return this.menuItems.add(newItem);
    }

    public ArrayList<MenuItem> filterMenu(String searchTerm, Double maxPrice, ArrayList<String> ingredients, ArrayList<Boolean> tags) {
        ArrayList<MenuItem> filteredItems = new ArrayList<MenuItem>();

        for (MenuItem item : filteredItems)
        {
            Boolean rightPrice = item.getPrice() <= maxPrice;
            Boolean rightName = (item.getName().toLowerCase().indexOf(searchTerm.toLowerCase()) != -1);
            Boolean rightIngredients = Menu.ingredientsMatch(item, ingredients);
            Boolean rightTags = Menu.tagsMatch(item, tags);
            if (rightPrice && rightName && rightIngredients && rightTags)
                filteredItems.add(item);
        }  

        return filteredItems;
    }

    private static Boolean ingredientsMatch(MenuItem item, ArrayList<String> searchIngredients) {
        ArrayList<String> itemIngredients = item.getIngredients();
        for (String ingredient : searchIngredients) {
            if (!itemIngredients.contains(ingredient)) return false;
        }
        return true;
    }

    private static Boolean tagsMatch(MenuItem item, ArrayList<Boolean> searchTags) {
        ArrayList<Boolean> itemTags = item.getTags();
        for (int i = 0; i < itemTags.size(); i++) {
            if (itemTags.get(i) != searchTags.get(i)) return false;
        }
        return true;
    }

}
