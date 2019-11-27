package ru.home.phoneBaseApp.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractNamedEntity extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "surname")
    private String surname;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(Integer id, String name, String lastname, String surname) {
        super(id);
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
    }
    public AbstractNamedEntity(String name, String lastname, String surname) {
        this.name = name;
        this.lastname = lastname;
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + lastname + ')'+ '(' + name + ')'+ '(' + surname + ')';
    }
    public boolean isNew() {
        return this.id == null;
    }

}