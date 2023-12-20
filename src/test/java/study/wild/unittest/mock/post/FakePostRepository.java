package study.wild.unittest.mock.post;

import study.wild.common.domain.ResourceNotFoundException;
import study.wild.post.domain.Post;
import study.wild.post.service.port.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class FakePostRepository implements PostRepository {
    private Long id = 0L;
    private final List<Post> data = new ArrayList<>();


    @Override
    public Post getById(Long id) {
        return data.stream().
                filter(item -> item.getId().equals(id) && item.getDeletedDate() == null).findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Post", id));
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null || post.getId() == 0) {
            Post newPost = Post.builder()
                    .id(++id)
                    .view(post.getView())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .deletedDate(post.getDeletedDate())
                    .category(post.getCategory())
                    .comments(post.getComments())
                    .build();
            data.add(newPost);
            return newPost;
        } else {
            return post;
        }
    }

    @Override
    public Post update(Long id, Post post) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}