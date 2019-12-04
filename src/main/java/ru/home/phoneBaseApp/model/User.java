package ru.home.phoneBaseApp.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_NAME, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.name=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name"),
})
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";
    public static final String BY_NAME = "User.getByName";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @OrderBy("name DESC")
    private Set<Note> notes;

    public User() {
    }

    public User(User u){
        this(u.getId(),u.getName(),u.getRoles());
    }
    public User(Integer id, String name, Collection<Role> roles) {
        super(id, name);
        setRoles(roles);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public Set<Note> getNotes() {
        return notes;
    }

}
