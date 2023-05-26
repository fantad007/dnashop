package com.dnahealth.service.impl;

import com.dnahealth.dto.BlogDetailDto;
import com.dnahealth.entity.BlogEntity;
import com.dnahealth.repository.BlogRepository;
import com.dnahealth.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    @Override
    public List<BlogEntity> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public BlogEntity findBlogById(long id) {
        if (blogRepository.findById(id).isPresent()) {
            return blogRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public BlogDetailDto getBlogDetailDto(String id) {
        BlogDetailDto blogDetailDto = new BlogDetailDto();
        try {
            long blogId = Long.parseLong(id);
            BlogEntity blog = findBlogById(blogId);
            if (blog != null) {
                blogDetailDto.setId(blog.getId());
                blogDetailDto.setTitle(blog.getTitle());
                blogDetailDto.setFullDesc(blog.getFullDesc());
                return blogDetailDto;
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
