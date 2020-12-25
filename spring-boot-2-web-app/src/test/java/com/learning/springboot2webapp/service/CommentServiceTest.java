package com.learning.springboot2webapp.service;

import com.learning.springboot2webapp.model.Comment;
import com.learning.springboot2webapp.model.CommentType;
import com.learning.springboot2webapp.repo.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class CommentServiceTest {

    @MockBean
    private CommentRepository commentRepository;

    private CommentService commentService;

    @Before
    public void init() {
        commentService = new CommentService(commentRepository);
    }

    @Test
    public void getAllCommentsForToday_HappyPath_ShouldReturn1Comment() {
        Comment comment = new Comment();
        comment.setComment("test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        List<Comment> comments = Arrays.asList(comment);

        LocalDate now = LocalDate.now();

        when(commentRepository.findbyCreatedYearAndMonthAndDay(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth()))
                .thenReturn(comments);
        List<Comment> actualComments = commentService.getAllCommentsFortoday();
        verify(commentRepository, times(1))
                .findbyCreatedYearAndMonthAndDay(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());
        assertThat(comments).isEqualTo(actualComments);
    }

    @Test
    public void saveAll_HappyPath_ShouldSave2Comments() {
        Comment comment = new Comment();
        comment.setComment("test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        Comment comment1 = new Comment();
        comment1.setComment("test star");
        comment1.setType(CommentType.STAR);
        comment1.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        List<Comment> comments = Arrays.asList(comment, comment1);

        when(commentRepository.saveAll(comments)).thenReturn(comments);

        List<Comment> saved = commentService.saveAll(comments);

        assertThat(saved).isNotEmpty();
        verify(commentRepository, times(1)).saveAll(comments);
    }
}