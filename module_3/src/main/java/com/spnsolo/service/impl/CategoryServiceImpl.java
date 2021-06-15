package com.spnsolo.service.impl;

import com.spnsolo.dto.CategoryDto;
import com.spnsolo.entity.Category;
import com.spnsolo.repository.CategoryRepository;
import com.spnsolo.repository.impl.MainRepository;
import com.spnsolo.service.CategoryService;
import org.hibernate.Session;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryRepository repository;

    public CategoryServiceImpl(Session session){repository = new MainRepository(session); }
    @Override
    public List<CategoryDto> getTypedTransactions(Category.Type type) {
        return repository.getTypedTransactions(type);
    }
}
