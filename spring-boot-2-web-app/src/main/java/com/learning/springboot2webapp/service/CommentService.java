package com.learning.springboot2webapp.service;

import com.learning.springboot2webapp.model.Comment;
import com.learning.springboot2webapp.repo.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Comment> saveAll(List<Comment> comments) {
        LOGGER.info("Saving {}", comments);
        return commentRepository.saveAll(comments);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Comment> getAllCommentsFortoday() {
        LocalDate now = LocalDate.now();
        return commentRepository.findbyCreatedYearAndMonthAndDay(
                now.getYear(), now.getMonth().getValue(), now.getDayOfMonth()
        );
    }
}
