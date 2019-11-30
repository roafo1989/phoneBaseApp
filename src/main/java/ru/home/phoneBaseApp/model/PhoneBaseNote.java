package ru.home.phoneBaseApp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(name = PhoneBaseNote.ALL_SORTED, query = "SELECT m FROM PhoneBaseNote m WHERE m.user.id=:userId ORDER BY m.name DESC"),
        @NamedQuery(name = PhoneBaseNote.BY_NUMBER, query = "SELECT m FROM PhoneBaseNote m WHERE m.user.id=:userId AND m.number=?1"),
        @NamedQuery(name = PhoneBaseNote.DELETE, query = "DELETE FROM PhoneBaseNote m WHERE m.id=:id AND m.user.id=:userId")})
@Entity
@Table(name = "NOTES")
public class PhoneBaseNote extends AbstractNamedEntity {
    public static final String ALL_SORTED = "PhoneBaseNote.getAll";
    public static final String DELETE = "PhoneBaseNote.delete";
    public static final String BY_NUMBER = "PhoneBaseNote.getByNumber";

    @Column(name = "number",nullable = false)
    private long number;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    public PhoneBaseNote() {
    }
    public PhoneBaseNote(String name, long number, String comment){
        this(null,name,number,comment);
    }


    public PhoneBaseNote(Integer id, String name, long number, String comment) {
        super(id, name);
        this.number = number;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
