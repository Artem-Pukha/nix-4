package com.spnsolo.library.util;

import com.spnsolo.library.entity.AbstractEntity;
import com.spnsolo.library.entity.Author;
import com.spnsolo.library.entity.AuthorAndBook;
import com.spnsolo.library.entity.Book;
import org.apache.commons.lang3.ArrayUtils;

public class EntityToString {
    private static String[] abstractEntityToString(AbstractEntity entity){
        String[] converted = new String[2];
        converted[0]=String.valueOf(entity.getId());
        converted[1]=String.valueOf(entity.isAvailable());
        return converted;
    }
    public static String[] authorToString(Author author){
        String[] converted = new String[2];
        converted[0] = author.getFirstName();
        converted[1] = author.getSecondName();
        return ArrayUtils.addAll(converted,abstractEntityToString(author));
    }
    public static String[]bookToString(Book book){
        String[] converted = new String[2];
        converted[0] = book.getName();
        converted[1] = String.valueOf(book.getCountOfPage());
        return ArrayUtils.addAll(converted,abstractEntityToString(book));
    }
    public static String[] authorAndBookToString(AuthorAndBook authorAndBook){
        String [] converted = new String[2];
        converted[0] = String.valueOf(authorAndBook.getAuthorId());
        converted[1] = String.valueOf(authorAndBook.getBookId());
        return ArrayUtils.addAll(converted,abstractEntityToString(authorAndBook));
    }

}
