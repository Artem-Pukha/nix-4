package com.spnsolo.entity;



import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Book extends BaseEntity{
    private String name;
    private Set<Integer> authors = new HashSet<>();

    @Override
    public String toString() {
        String authorId;
        if(authors.isEmpty()){
            authorId = "Unknown";
        }else{
            authorId = authors.toString();
        }
        return "Book{" +
                "id='"+ id +'\''+
                "name='" + name + '\'' +
                ", authors id=" + authorId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name);
    }
}
