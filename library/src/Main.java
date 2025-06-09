import exception.Book_not_available;
import exception.MemberNotFoundException;
import exception.OverdueBookException;
import model.Bookspresent;
import model.Member_details;
import model.Book_Lending_record;
import repository.Main_repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import model.*;
import repository.Main_repository;
import service.LibraryService;
import exception.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Main_repository<Bookspresent> bookRepo = new Main_repository<>();
        Main_repository<Member_details> memberRepo = new Main_repository<>();
        Main_repository<Book_Lending_record> lendingRepo = new Main_repository<>();

        LibraryService service = new LibraryService(bookRepo, memberRepo, lendingRepo);

        boolean running = true;

        while (running) {
            System.out.println("\n📚 Library Management Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books by Title");
            System.out.println("6. Search Books by Author");
            System.out.println("7. Search members by name or email");
            System.out.println("8. Sort books by title");
            System.out.println("9. Sort books by author");
            System.out.println("10. List available books");
            System.out.println("11. List members with borrowed books");
            System.out.println("12. Report overdue books");
            System.out.println("13. Exit");
            System.out.print("👉 Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        String bookId = scanner.nextLine();
                        System.out.print("Enter Book Name: ");
                        String bookName = scanner.nextLine();
                        System.out.print("Enter Author Name: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Publisher Name: ");
                        String publisher = scanner.nextLine();

                        service.addBook(bookId, bookName, author, publisher);
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        long phone = scanner.nextLong();
                        scanner.nextLine(); // consume newline

                        service.addMember(memberId, name, email, phone);
                        break;

                    case 3:
                        System.out.print("Enter Book ID to issue: ");
                        String issueBookId = scanner.nextLine();
                        System.out.print("Enter Member ID: ");
                        String issueMemberId = scanner.nextLine();

                        service.issueBook(issueBookId, issueMemberId);
                        break;

                    case 4:
                        System.out.print("Enter Lending Record ID to return: ");
                        String returnRecordId = scanner.nextLine();

                        service.returnBook(returnRecordId);
                        break;

                    case 5:
                        System.out.print("Enter title keyword: ");
                        String titleKeyword = scanner.nextLine();
                        service.searchBooksByTitle(titleKeyword);
                        break;

                    case 6:
                        System.out.print("Enter author name: ");
                        String authorSearch = scanner.nextLine();
                        service.searchBooksByAuthor(authorSearch);
                        break;

                    case 7:
                        System.out.print("Enter name or email to search members: ");
                        String memberQuery = scanner.nextLine();
                        service.searchMembersByNameOrEmail(memberQuery);
                        break;

                    case 8:
                        System.out.println("Books sorted by title:");
                        service.sortBooksByTitle();
                        break;

                    case 9:
                        System.out.println("Books sorted by author:");
                        service.sortBooksByAuthor();
                        break;

                    case 10:
                        System.out.println("📖 Available books:");
                        service.listAvailableBooks();
                        break;

                    case 11:
                        System.out.println("Members with borrowed books:");
                        service.listMembersWithBorrowedBooks();
                        break;

                    case 12:
                        System.out.println("Overdue Book Records:");
                        service.reportOverdueBooks();
                        break;

                    case 13:
                        running = false;
                        System.out.println("Exiting Library System. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}


