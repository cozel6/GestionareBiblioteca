package gestionarebiblioteca;

import java.util.*;

public class Library {

    private Map<String, Book> books;
    private Map<String, Person> people;

    private Map<Person, List<Book>> borrowedBooks;

    private Scanner scanner;

    public Library() {
        books = new HashMap<>();
        people = new HashMap<>();
        borrowedBooks = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void initData() {

        //books
        Book b1 = new Book("The Great Gatsby", "Sience", 3);
        Book b2 = new Book("To Kill a Mockingbird", "Horror", 2);
        Book b3 = new Book("1984", "Novel", 4);

        books.put(b1.getTitle(), b1);
        books.put(b2.getTitle(), b2);
        books.put(b3.getTitle(), b3);

        //Persons 
        Person p1 = new Person("Marian Andrei");
        Person p2 = new Person("Marius Costin");
        Person p3 = new Person("Alex Dumitru");

        people.put(p1.getName(), p1);
        people.put(p2.getName(), p2);
        people.put(p3.getName(), p3);

    }

    public void runMenu() {
        while (true) {
            System.out.println("\n=== Meniu Biblioteca ===");
            System.out.println("1. Afișează cărțile disponibile");
            System.out.println("2. Adaugă o carte nouă");
            System.out.println("3. Împrumută o carte");
            System.out.println("4. Returnează o carte");
            System.out.println("5. Listează persoanele și împrumuturile");
            System.out.println("6. Caută o carte după titlu");
            System.out.println("7. Listează cărțile pe categorii");
            System.out.println("8. Transferă o carte în altă categorie");
            System.out.println("9. Ieșire");
            System.out.print("Alege o opțiune: ");

            int option;

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Te rog introdu un număr valid!");
                continue;
            }
            switch (option) {
                case 1 ->
                    displayBooks();
                case 2 ->
                    addBook();
                case 3 ->
                    borrowBook();
                case 4 ->
                    returnBook();
                case 5 ->
                    listBorrowers();
                case 6 ->
                    searchBooks();
                case 7 ->
                    listByCategory();
                case 8 ->
                    transferBook();
                case 9 -> {
                    System.out.println("La revedere!");
                    return;
                }
                default ->
                    System.out.println("Opțiune invalidă!");
            }
        }
    }

    private void displayBooks() {
        System.out.println("Cărți disponibile:");
        for (Book book : books.values()) {
            if (book.getCopies() > 0) {
                System.out.println("- " + book.getTitle() + " (" + book.getCopies()
                        + " exemplare, categoria: " + book.getCategory() + ")");
            }
        }
    }

    private void addBook() {
        System.out.print("Titlul cărții: ");
        String title = scanner.nextLine();
        System.out.println("Categoria ");
        String category = scanner.nextLine();
        System.out.print("Număr de exemplare: ");
        int copies = 1;

        try {
            copies = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Numar invalid, se va folosi 1");
        }
        if (books.containsKey(title)) {

            Book existing = books.get(title);
            existing.setCopies(existing.getCopies() + copies);
            System.out.println("Cartea exista deja, i-am mărit numărul de exemplare.");

        } else {

            Book newBook = new Book(title, category, copies);
            books.put(title, newBook);
            System.out.println("Cartea a fost adăugată în bibliotecă.");
        }
    }

    private void borrowBook() {
        System.out.println("Titlul cartii imprumutate: ");
        String title = scanner.nextLine();
        if (!books.containsKey(title)) {
            System.out.println("Aceasta carte nu exista in biblioteca");
        }
        Book book = books.get(title);
        if (book.getCopies() <= 0) {
            System.out.println("Aceasta carte nu mai are exemplare disponibile");
        }

        System.out.print("Numele persoanei: ");
        String personName = scanner.nextLine();
        Person person = people.get(personName);
        if (person == null) {
            person = new Person(personName);
            people.put(personName, person);
        }
        book.setCopies(book.getCopies() - 1);

        borrowedBooks.putIfAbsent(person, new ArrayList<>());
        borrowedBooks.get(person).add(book);

        System.out.println("Cartea \"" + title + "\" a fost împrumutată de " + personName + ".");

    }

    private void returnBook() {
        System.out.println("Titlul cartii de returnat");
        String title = scanner.nextLine();
        if (!books.containsKey(title)) {
            System.out.println("Acesta carte nu exista in sistem");
            return;
        }

        Book book = books.get(title);
        Person foundPerson = null;
        for (Map.Entry<Person, List<Book>> entry : borrowedBooks.entrySet()) {
            if (entry.getValue().contains(book)) {
                foundPerson = entry.getKey();
                break;
            }
        }
        if (foundPerson == null) {
            System.out.println("Nu am găsit nicio persoană care să aibă această carte împrumutată.");
            return;
        }
        borrowedBooks.get(foundPerson).remove(book);
        book.setCopies(book.getCopies() + 1);

        System.out.println("Cartea \"" + title + "\" a fost returnată de " + foundPerson.getName() + ".");
    }


    private void listBorrowers() {
    }

    private void searchBooks() {
    }

    private void listByCategory() {
    }

    private void transferBook() {
    }
}
