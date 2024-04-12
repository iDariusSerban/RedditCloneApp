package RedditClone.Repositories;

import RedditClone.Model.Post;
import RedditClone.Model.User;
import RedditClone.Model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByPost_IdAndUser_Id(Long postId, Long userId);

    Vote findByPostAndUser(Post post, User user);


}
