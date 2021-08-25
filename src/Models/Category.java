package Models;

public class Category {

    private int idCategory;
    private String categoryName;


    public Category(int id, String c) {
        this.idCategory = id;
        this.categoryName = c;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
