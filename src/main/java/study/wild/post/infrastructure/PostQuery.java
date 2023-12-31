package study.wild.post.infrastructure;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import study.wild.category.domain.Category;
import study.wild.comment.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostQuery {

    private final Long id;
    private final String title;
    private final String content;
    private final Long view;
    private final LocalDateTime createdDate;
    private List<Comment> comments;
    private final Category category;

    @Builder
    @QueryProjection
    public PostQuery(Long id, String title, String content, Long view, LocalDateTime createdDate, Long categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdDate = createdDate;
        this.category = Category.builder()
                .id(categoryId)
                .name(categoryName)
                .build();
    }

}