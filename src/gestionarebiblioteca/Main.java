package gestionarebiblioteca;



public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.initData();

        library.runMenu();
    }
}
