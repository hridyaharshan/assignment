package model;

public class Member_details {
    private String member_id;
    private String name;
    private String email;
    private long phone_number;


    public Member_details(String member_id, String name, String email, long phone_number) {
        this.member_id = member_id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Member_details{" +
                "member_id='" + member_id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number=" + phone_number +
                '}';
    }
}
