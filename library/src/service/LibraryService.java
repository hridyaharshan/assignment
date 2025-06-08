package service;


import model.*;

import exception.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import repository.Main_repository;



import model.*;
import repository.Main_repository;
import exception.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private final Main_repository<Bookspresent> bookRepo;
    private final Main_repository<Member_details> memberRepo;
    private final Main_repository<Book_Lending_record> lendingRepo;
    private int recordCounter = 1;

    public LibraryService(Main_repository<Bookspresent> bookRepo,
                          Main_repository<Member_details> memberRepo,
                          Main_repository<Book_Lending_record> lendingRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
        this.lendingRepo = lendingRepo;
    }

    public void addBook(String id, String name, String author, String publisher) {
        bookRepo.add(id, new Bookspresent(id, name, author, publisher, false));
        System.out.println("New Book is  added successfully.");
    }

    public void addMember(String id, String name, String email, long phone) {
        memberRepo.add(id, new Member_details(id, name, email, phone));
        System.out.println("New Member added to the library successfully.");
    }

    public void issueBook(String bookId, String memberId) throws Exception {
        Bookspresent book = bookRepo.get_id(bookId);
        //if book not there
        if (book == null)
            throw new Book_not_available("Book not found.");
        //book is already issued
        if (book.isIssued())
            throw new Book_not_available("Book is already issued.");

        Member_details member = memberRepo.get_id(memberId);
        //member is not present
        if (member == null)
            throw new MemberNotFoundException("Member not found.");


        //there is a overdue present checking
        //anymatch-used when to check any of them are correctly matched
        //the book is not returned and the due date is fininshed
        boolean hasOverdue = lendingRepo.get_all_data().stream()
                .filter(r -> r.getMember_id().equals(memberId))
                .anyMatch(r -> r.getReturn_date() == null && r.getDue_date().isBefore(LocalDate.now()));

        if (hasOverdue)
            throw new OverdueBookException("Member has overdue books.");

        book.setIssued(true);
        Book_Lending_record record = new Book_Lending_record(recordCounter++, bookId, memberId, LocalDate.now(), LocalDate.now().plusDays(14));
        lendingRepo.add(String.valueOf(record.getRecord_id()), record);
        System.out.println("Book issued to " + member.getName());
    }

    public void returnBook(String recordId) {
        Book_Lending_record record = lendingRepo.get_id(recordId);
        if (record != null && record.getReturn_date() == null) {
            record.setReturn_date(LocalDate.now());

            Bookspresent book = bookRepo.get_id(record.getBook_id());
            if (book != null) {
                book.setIssued(false);
            }

            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Invalid record or already returned.");
        }
    }

    public void searchBooksByTitle(String keyword) {
        List<Bookspresent> matches = bookRepo.search(b -> b.getBookname().contains(keyword));
        if (matches.isEmpty()) {
            System.out.println("No books found.");
        } else {
            matches.forEach(System.out::println);
        }
    }

    public void searchBooksByAuthor(String keyword) {
        List<Bookspresent> matches = bookRepo.search(b -> b.getAuthorname().toLowerCase().contains(keyword.toLowerCase()));
        if (matches.isEmpty()) {
            System.out.println("No books found.");
        } else {
            matches.forEach(System.out::println);
        }
    }

    public void searchMembersByNameOrEmail(String query) {
        List<Member_details> matches = memberRepo.search(m ->
                m.getName().toLowerCase().contains(query.toLowerCase()) ||
                        m.getEmail().toLowerCase().contains(query.toLowerCase()));
        if (matches.isEmpty()) {
            System.out.println("No members found.");
        } else {
            matches.forEach(System.out::println);
        }
    }

    //sort the objects based on the consition that is the given sort in basis of that
    public void sortBooksByTitle() {
        List<Bookspresent> sorted = bookRepo.get_all_data().stream()
                .sorted(Comparator.comparing(Bookspresent::getBookname, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    public void sortBooksByAuthor() {
        List<Bookspresent> sorted = bookRepo.get_all_data().stream()
                .sorted(Comparator.comparing(Bookspresent::getAuthorname, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    public void listAvailableBooks() {
        List<Bookspresent> available = bookRepo.search(b -> !b.isIssued());
        available.forEach(System.out::println);
    }

    public void listMembersWithBorrowedBooks() {
        Set<String> memberIds = lendingRepo.get_all_data().stream()
                .filter(r -> r.getReturn_date() == null)
                .map(Book_Lending_record::getMember_id)
                .collect(Collectors.toSet());

        List<Member_details> members = memberRepo.get_all_data().stream()
                .filter(m -> memberIds.contains(m.getMember_id()))
                .collect(Collectors.toList());
        members.forEach(System.out::println);
    }

    public void reportOverdueBooks() {
        List<Book_Lending_record> overdue = lendingRepo.get_all_data().stream()
                .filter(r -> r.getReturn_date() == null && r.getDue_date().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        overdue.forEach(System.out::println);
    }
}

