package com.spnsolo.library.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Author extends AbstractEntity{
    private String firstName;
    private String secondName;

    @Override
    public String toString() {
        return "Author{" +
                " First name='" + firstName +
                ", Second name='" + secondName + '\'' +
                "ID=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return getFirstName().equals(author.getFirstName()) && getSecondName().equals(author.getSecondName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getSecondName());
    }
}
