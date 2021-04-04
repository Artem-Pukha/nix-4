package com.spnsolo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Author extends BaseEntity{
    private String firstName;
    private String secondName;
    private Set<Integer> books = new HashSet<>();

    @Override
    public String toString() {
        String bookId;
        if(books.isEmpty()){
            bookId = "Unknown";
        }else{
            bookId = books.toString();
        }
        return "Author{" +
                "id='"+ id +'\''+
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", books id=" + bookId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return firstName.equals(author.firstName) && secondName.equals(author.secondName);
    }
}
