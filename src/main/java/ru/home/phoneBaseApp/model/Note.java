package ru.home.phoneBaseApp.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.beans.ConstructorProperties;

@NamedQueries({
        @NamedQuery(name = Note.ALL_SORTED, query = "SELECT m FROM Note m WHERE m.user.id=:userId ORDER BY m.name DESC"),
        @NamedQuery(name = Note.BY_NUMBER, query = "SELECT m FROM Note m WHERE m.user.id=:userId AND m.number=?1"),
        @NamedQuery(name = Note.DELETE, query = "DELETE FROM Note m WHERE m.id=:id AND m.user.id=:userId")})
@Entity
@Table(name = "NOTES")
public class Note extends AbstractNamedEntity {
    public static final String ALL_SORTED = "Note.getAll";
    public static final String DELETE = "Note.delete";
    public static final String BY_NUMBER = "Note.getByNumber";

    @Column(name = "number",nullable = false)
    private long number;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    private User user;

    public Note() {
    }
    public Note(String name, long number, String comment){
        this(null,name,number,comment);
    }

   /* @ConstructorProperties({"id","name","number","comment"})*/
    public Note(Integer id, String name, long number, String comment) {
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
