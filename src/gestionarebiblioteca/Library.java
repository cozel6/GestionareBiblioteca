package gestionarebiblioteca;

import java.util.*;

public class Library {

    private final Map<String, Book> books;
    private final Map<String, Person> people;

    private final Map<Person, List<Book>> borrowedBooks;

    private final Scanner scanner;

    public Library() {
        books = new HashMap<>();
        people = new HashMap<>();
        borrowedBooks = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void initData() {
        
        Book b1 = new Book("The Great Gatsby", "Science", 3);
        Book b2 = new Book("To Kill a Mockingbird", "Horror", 2);
        Book b3 = new Book("1984", "Novel", 4);
        Book b4 = new Book("Pride and Prejudice", "Romance", 5);
        Book b5 = new Book("Moby Dick", "Adventure", 3);
        Book b6 = new Book("War and Peace", "Historical", 2);
        Book b7 = new Book("Hamlet", "Tragedy", 4);
        Book b8 = new Book("The Catcher in the Rye", "Novel", 3);
        Book b9 = new Book("The Hobbit", "Fantasy", 6);
        Book b10 = new Book("Fahrenheit 451", "Dystopia", 4);
        Book b11 = new Book("Brave New World", "Dystopia", 3);
        Book b12 = new Book("The Odyssey", "Epic", 5);
        Book b13 = new Book("Crime and Punishment", "Psychological", 2);
        Book b14 = new Book("The Brothers Karamazov", "Philosophical", 3);
        Book b15 = new Book("Don Quixote", "Adventure", 4);
    
        books.put(b1.getTitle(), b1);
        books.put(b2.getTitle(), b2);
        books.put(b3.getTitle(), b3);
        books.put(b4.getTitle(), b4);
        books.put(b5.getTitle(), b5);
        books.put(b6.getTitle(), b6);
        books.put(b7.getTitle(), b7);
        books.put(b8.getTitle(), b8);
        books.put(b9.getTitle(), b9);
        books.put(b10.getTitle(), b10);
        books.put(b11.getTitle(), b11);
        books.put(b12.getTitle(), b12);
        books.put(b13.getTitle(), b13);
        books.put(b14.getTitle(), b14);
        books.put(b15.getTitle(), b15);
    
        Person p1 = new Person("Marian Andrei");
        Person p2 = new Person("Marius Costin");
        Person p3 = new Person("Alex Dumitru");
        Person p4 = new Person("Ana Popescu");
        Person p5 = new Person("Ioana Ionescu");
        Person p6 = new Person("Cristina Georgescu");
        Person p7 = new Person("AndFlrei Marinescu");
        Person p8 = new Person("Florin Dumitru");
        Person p9 = new Person("Raluca Mihai");
        Person p10 = new Person("Sorin Pavel");
        Person p11 = new Person("Elena Constantinescu");
        Person p12 = new Person("Vlad Petrescu");
        Person p13 = new Person("Diana Stan");
        Person p14 = new Person("Radu Pop");
        Person p15 = new Person("Gabriela Iacob");
    
        people.put(p1.getName(), p1);
        people.put(p2.getName(), p2);
        people.put(p3.getName(), p3);
        people.put(p4.getName(), p4);
        people.put(p5.getName(), p5);
        people.put(p6.getName(), p6);
        people.put(p7.getName(), p7);
        people.put(p8.getName(), p8);
        people.put(p9.getName(), p9);
        people.put(p10.getName(), p10);
        people.put(p11.getName(), p11);
        people.put(p12.getName(), p12);
        people.put(p13.getName(), p13);
        people.put(p14.getName(), p14);
        people.put(p15.getName(), p15);
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
                case 1:
                    displayBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    listBorrowers();
                    break;
                case 6:
                    searchBooks();
                    break;
                case 7:
                    listByCategory();
                    break;
                case 8:
                    transferBook();
                    break;
                case 9:
                    System.out.println("La revedere!");
                    return;
                default:
                    System.out.println("Opțiune invalidă!");
                    break;
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
        System.out.println("Lista persoanelor și împrumuturile lor:");
        for(Person p : people.values()){
            List<Book> borrowed = borrowedBooks.get(p);
            if(borrowed != null && !borrowed.isEmpty()){
                System.out.println("- " + p.getName() + " are imprumutate: ");
                for(Book b : borrowed){
                    System.out.print(b.getTitle() + ", ");
                }
                System.out.println();
            }else {
                System.out.println("- " + p.getName() + " nu are nicio carte împrumutată.");
            }
        }
    }
    private void searchBooks() {
        System.out.print("Introduceți începutul titlului: ");
        String prefix = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().startsWith(prefix)) {
                System.out.println("- " + b.getTitle() + " (" + b.getCopies() 
                                   + " exemplare, categoria: " + b.getCategory() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Nu am găsit nicio carte care să înceapă cu \"" + prefix + "\".");
        }
    }

    private void listByCategory() {
        Map<String, List<Book>> catMap = new HashMap<>();
        for (Book b : books.values()) {
            catMap.putIfAbsent(b.getCategory(), new ArrayList<>());
            catMap.get(b.getCategory()).add(b);
        }
        for (String cat : catMap.keySet()) {
            System.out.println("Categoria: " + cat);
            for (Book b : catMap.get(cat)) {
                System.out.println("   - " + b.getTitle() + " (" + b.getCopies() + " exemplare)");
            }
        }
    }

    private void transferBook() {
        System.out.print("Titlul cărții de mutat: ");
        String title = scanner.nextLine();
        if (!books.containsKey(title)) {
            System.out.println("Cartea nu există în sistem.");
            return;
        }
        Book book = books.get(title);

        System.out.print("Noua categorie: ");
        String newCat = scanner.nextLine();
        String oldCat = book.getCategory();
        book.setCategory(newCat);

        System.out.println("Cartea \"" + title + "\" a fost mutată din " 
                           + oldCat + " în " + newCat + ".");
    }
}