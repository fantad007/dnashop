package com.dnahealth.service;


import com.dnahealth.dto.BlogDetailDto;
import com.dnahealth.entity.BlogEntity;

import java.util.List;

public interface BlogService {
    List<BlogEntity> getAll();
    BlogEntity findBlogById(long id);
    BlogDetailDto getBlogDetailDto(String id);
}
