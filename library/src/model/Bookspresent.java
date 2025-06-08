package model;

public class Bookspresent implements Comparable<Bookspresent> {
    private String bookid;
   private  String bookname;
    private String authorname;
    private String publishername;
    private boolean isIssued;



    public Bookspresent(String bookid, String bookname, String authorname, String publishername, boolean isIssued) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.authorname = authorname;
        this.publishername = publishername;
        this.isIssued = isIssued;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }


    @Override
    public int compareTo(Bookspresent other) {
        return this.bookname.compareToIgnoreCase(other.bookname);
    }

    @Override
    public String toString() {
        return "Bookspresent{" +
                "bookid='" + bookid + '\'' +
                ", bookname='" + bookname + '\'' +
                ", authorname='" + authorname + '\'' +
                ", publishername='" + publishername + '\'' +
                ", isIssued=" + isIssued +
                '}';
    }
}
