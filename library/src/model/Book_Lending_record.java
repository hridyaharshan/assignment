package model;

import java.time.LocalDate;

public class Book_Lending_record {
    private int record_id;
    private String book_id;
    private String member_id;
    private LocalDate issue_date;
    private LocalDate due_date;
    private LocalDate return_date;
    private int fine_amount;

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public Book_Lending_record(int record_id, String member_id, String issueMemberId, LocalDate issue_date, LocalDate due_date) {
        this.record_id = record_id;
        this.issue_date = issue_date;
        this.due_date = due_date;
        this.member_id=member_id;

    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public int getRecord_id() {
        return record_id;
    }

    public int setRecord_id(int record_id) {
        this.record_id = record_id;
        return record_id;
    }

    public LocalDate getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(LocalDate issue_date) {
        this.issue_date = issue_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public int getFine_amount() {
        return fine_amount;
    }

    public void setFine_amount(int fine_amount) {
        this.fine_amount = fine_amount;
    }

    @Override
    public String toString() {
        return "Book_Lending_record{" +
                "record_id='" + record_id + '\'' +
                ", issue_date=" + issue_date +
                ", due_date=" + due_date +
                ", return_date=" + return_date +
                ", fine_amount=" + fine_amount +
                '}';
    }
}
