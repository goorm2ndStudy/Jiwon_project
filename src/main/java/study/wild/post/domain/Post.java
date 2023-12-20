package study.wild.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import study.wild.category.domain.Category;
import study.wild.comment.infrastructure.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Post {
    private final Long id;
    private final String title;
    private final String content;
    private final Long view;
    private final List<Comment> comments;
    private final Category category;
    private final LocalDateTime deletedDate;


    @Builder
    public Post(Long id, String title, String content, Long view, List<Comment> comments, Category category, LocalDateTime deletedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view = view;
        this.comments = comments;
        this.category = category;
        this.deletedDate = deletedDate;
    }

    public static Post fromCreate(Category category, PostCreate postCreate) {
        return Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .view(0L)
                .category(category)
                .build();
    }
}
