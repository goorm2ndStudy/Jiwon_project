package study.wild.post.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import study.wild.category.domain.Category;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Post {
    private final Long id;
    private final PostTitle title;
    private final PostContent content;
    private final Long view;
    private final Category category;
    private final LocalDateTime deletedDate;

    @Builder
    public Post(Long id, String title, String content, Long view, Category category, LocalDateTime deletedDate) {
        this.id = id;
        this.title = PostTitle.builder()
                .title(title)
                .build();
        this.content = PostContent.builder()
                .content(content)
                .build();
        this.view = view;
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

    public Post update(Long id, PostUpdate postUpdate) {
        return Post.builder()
                .id(id)
                .title(postUpdate.getTitle())
                .content(postUpdate.getContent())
                .view(view)
                .deletedDate(deletedDate)
                .category(category)
                .build();
    }

    public Post delete(LocalDateTime deletedDate) {
        return Post.builder()
                .id(id)
                .title(title.title())
                .content(content.content())
                .view(view)
                .deletedDate(deletedDate)
                .category(category)
                .build();
    }

    public Post updateView(Long view) {
        return Post.builder()
                .id(id)
                .title(title.title())
                .content(content.content())
                .view(view)
                .deletedDate(deletedDate)
                .category(category)
                .build();
    }
}
