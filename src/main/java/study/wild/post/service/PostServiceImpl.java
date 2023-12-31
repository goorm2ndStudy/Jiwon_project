package study.wild.post.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.wild.category.domain.Category;
import study.wild.category.service.port.CategoryRepository;
import study.wild.common.service.DatetimeHolder;
import study.wild.post.controller.port.PostService;
import study.wild.post.controller.response.PostListResponse;
import study.wild.post.domain.Post;
import study.wild.post.domain.PostCreate;
import study.wild.post.domain.PostUpdate;
import study.wild.post.service.port.PostRepository;

import java.util.List;

@Builder
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final DatetimeHolder datetimeHolder;

    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    public Page<PostListResponse> getByCategoryId(Long categoryId, Long totalCount, Pageable pageable) {
        List<PostListResponse> posts = postRepository.getByCategoryId(categoryId, pageable);
        Long count = countByCategoryId(categoryId, totalCount);
        return new PageImpl<>(posts, pageable, count);
    }

    private Long countByCategoryId(Long categoryId, Long totalCount) {
        if (totalCount == null) {
            return postRepository.countByCategoryId(categoryId);
        }
        return totalCount;
    }

    @Transactional
    public Post create(PostCreate postCreate) {
        Category category = categoryRepository.getById(postCreate.getCategoryId());
        return postRepository.save(Post.fromCreate(category, postCreate));
    }

    @Transactional
    public Post update(Long id, PostUpdate postUpdate) {
        Post findPost = postRepository.getById(id);
        Post updatedPost = findPost.update(id, postUpdate);
        return postRepository.save(updatedPost);
    }

    @Transactional
    public Long delete(Long id) {
        Post findPost = getById(id);
        Post deletedPost = findPost.delete(datetimeHolder.now());
        return postRepository.save(deletedPost).getId();
    }


}
