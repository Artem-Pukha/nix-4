package com.spnsolo.repository;

import com.spnsolo.dto.CategoryDto;
import com.spnsolo.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<CategoryDto> getTypedTransactions(Category.Type type);
}
