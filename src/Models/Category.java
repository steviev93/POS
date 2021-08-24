package Models;

public class Category {
    private String categoryName;


    public Category(String c) {
        this.categoryName = c;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
