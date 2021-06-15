package com.spnsolo.service;

import com.spnsolo.dto.CategoryDto;
import com.spnsolo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getTypedTransactions(Category.Type type);
}
