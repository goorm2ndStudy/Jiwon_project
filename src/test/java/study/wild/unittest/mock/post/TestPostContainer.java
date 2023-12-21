package study.wild.unittest.mock.post;

import lombok.Builder;
import study.wild.category.service.port.CategoryRepository;
import study.wild.common.service.DatetimeHolder;
import study.wild.post.controller.PostController;
import study.wild.post.controller.port.PostService;
import study.wild.post.service.PostServiceImpl;
import study.wild.post.service.port.PostRepository;
import study.wild.unittest.mock.category.FakeCategoryRepository;


public class TestPostContainer {
    public final PostRepository postRepository;
    public final PostService postService;
    public final PostController postController;

    public final CategoryRepository categoryRepository;

    @Builder
    public TestPostContainer(DatetimeHolder datetimeHolder) {
        this.categoryRepository = new FakeCategoryRepository();
        this.postRepository = new FakePostRepository();
        this.postService = PostServiceImpl.builder()
                .categoryRepository(this.categoryRepository)
                .postRepository(this.postRepository)
                .datetimeHolder(datetimeHolder)
                .build();
        this.postController = PostController.builder()
                .postService(this.postService)
                .build();
    }
}
