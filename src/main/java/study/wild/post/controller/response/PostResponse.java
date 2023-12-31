package study.wild.post.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.wild.category.controller.response.CategoryResponse;
import study.wild.comment.controller.response.CommentListResponse;
import study.wild.comment.domain.Comment;
import study.wild.post.domain.Post;
import study.wild.post.infrastructure.PostQuery;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long view;
    private CommentListResponse comments;
    private CategoryResponse category;

    @Builder
    private PostResponse(Long id, String title, String content, Long view, List<Comment> comments, CategoryResponse category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.view = view;
        this.comments = CommentListResponse.from(comments);
        this.category = category;
    }

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent().content())
                .view(post.getView())
                .title(post.getTitle().title())
                .category(CategoryResponse.from(post.getCategory()))
                .build();
    }

    public static PostResponse from(PostQuery postQuery) {
        return PostResponse.builder()
                .id(postQuery.getId())
                .title(postQuery.getTitle())
                .content(postQuery.getContent())
                .view(postQuery.getView())
                .category(CategoryResponse.from(postQuery.getCategory()))
                .comments(postQuery.getComments())
                .build();
    }

}
