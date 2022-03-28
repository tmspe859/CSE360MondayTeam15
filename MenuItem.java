import java.util.ArrayList;

public class MenuItem {
    private String name;
    private Double price;
    private String desc;
    private ArrayList<String> ingredients;
    private ArrayList<Boolean> tags;
    private String imgPath;

    public MenuItem(String name, Double price, String desc, ArrayList<String> ingredients, ArrayList<Boolean> tags, String imgPath) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.ingredients = ingredients;
        this.tags = tags;
        this.imgPath = imgPath;
    }

    public void editItem() {}
    public void viewItem() {}
    public void display() {}

    public String getName() { return this.name; }
    public Double getPrice() { return this.price; }
    public String getDesc() { return this.desc; }
    public ArrayList<String> getIngredients() { return this.ingredients; }
    public ArrayList<Boolean> getTags() { return this.tags; }
    public String getImgPath() { return this.imgPath; }

}
