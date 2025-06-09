package service;

import model.Book_Lending_record;
import model.Member_details;
import repository.Main_repository;

import java.time.LocalDate;
import java.util.List;

public class Overdue implements Runnable {
    private final Main_repository<Book_Lending_record> lendingRepo;
    private final Main_repository<Member_details> memberRepo;


    public Overdue(Main_repository<Book_Lending_record> lendingRepo, Main_repository<Member_details> memberRepo) {
        this.lendingRepo = lendingRepo;
        this.memberRepo = memberRepo;
    }

    @Override
    public void run() {
        System.out.println("[OverdueMonitor] Running check for overdue books...");

        List<Book_Lending_record> overdueRecords = lendingRepo.get_all_data().stream()
                .filter(r -> r.getReturn_date() == null && r.getDue_date().isBefore(LocalDate.now()))
                .toList();

        for (Book_Lending_record record : overdueRecords) {
            Member_details member = memberRepo.get_id(record.getMember_id());
            if (member != null) {
                System.out.println(" Overdue Book!");
                System.out.println("   Member: " + member.getName());
                System.out.println("   Email : " + member.getEmail());
                System.out.println("   Book ID: " + record.getBook_id());
                System.out.println("   Due Date: " + record.getDue_date());
            }
        }
    }
}
