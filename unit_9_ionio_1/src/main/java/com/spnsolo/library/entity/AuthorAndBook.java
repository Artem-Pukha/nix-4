package com.spnsolo.library.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class AuthorAndBook extends AbstractEntity{
    private Integer authorId;
    private Integer bookId;

    @Override
    public String toString() {
        return "AuthorAndBook{" +
                "id=" + id +
                ", available=" + available +
                ", authorId=" + authorId +
                ", bookId=" + bookId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorAndBook)) return false;
        AuthorAndBook that = (AuthorAndBook) o;
        return getAuthorId().equals(that.getAuthorId()) && getBookId().equals(that.getBookId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorId(), getBookId());
    }
}
