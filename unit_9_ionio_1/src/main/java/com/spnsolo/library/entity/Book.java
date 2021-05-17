package com.spnsolo.library.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Book extends AbstractEntity{
    private String name;
    private Integer countOfPage;

    @Override
    public String toString() {
        return  " Name='" + name +
                ", count of page=" + countOfPage + '\'' +
                "ID=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getName().equals(book.getName()) && getCountOfPage().equals(book.getCountOfPage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountOfPage());
    }
}
