package study.wild.comment.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import study.wild.post.domain.Post;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Comment {
    private final Long id;
    private final Post post;
    private final CommentContent content;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;
    private final LocalDateTime deletedDate;

    @Builder
    private Comment(Long id, Post post, String content, LocalDateTime createdDate, LocalDateTime lastModifiedDate, LocalDateTime deletedDate) {
        this.id = id;
        this.post = post;
        this.content = CommentContent.builder()
                .content(content)
                .build();
        this.deletedDate = deletedDate;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static Comment fromCreate(Post post, CommentCreate commentCreate) {
        return Comment.builder()
                .content(commentCreate.getContent())
                .post(post)
                .build();
    }

    public Comment update(Post post, CommentUpdate commentUpdate) {
        return Comment.builder()
                .id(id)
                .content(commentUpdate.content())
                .post(post)
                .build();
    }

    public Comment delete(LocalDateTime deletedDate) {
        return Comment.builder()
                .id(id)
                .content(content.content())
                .post(post)
                .deletedDate(deletedDate)
                .build();
    }
}
