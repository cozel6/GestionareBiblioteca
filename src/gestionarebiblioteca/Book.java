package gestionarebiblioteca;

public class Book {
    private String title;
    private String category;
    private int copies;

    public Book(String title, String category, int copies) {
        this.title = title;
        this.category = category;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}