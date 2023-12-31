package study.wild.post.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long>, PostQueryRepository {
    Optional<PostEntity> findByIdAndDeletedDateIsNull(Long id);

    List<PostEntity> findByIdIn(List<Long> ids);
}
