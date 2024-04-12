package RedditClone.Services;

import RedditClone.DTOs.PostRequestDTO;
import RedditClone.DTOs.PostResponseDTO;
import RedditClone.Model.Post;
import RedditClone.Model.Vote;
import RedditClone.Repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PostMapperService {
    private VoteRepository voteRepository;
    private UserService userService;
    @Autowired
    public PostMapperService(VoteRepository voteRepository, UserService userService) {
        this.voteRepository = voteRepository;
        this.userService = userService;
    }

    @Transactional
    public Post mapPostRequestDTOtoPost(PostRequestDTO postRequestDTO){
        Post post = new Post();
        post.setVoteCount(0l);
        post.setCreatedDate(LocalDateTime.now());
        return post;

    }

    @Transactional
    public  PostResponseDTO mapPostToDto(Post post){
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setUserName(post.getUser().getName());
        postResponseDTO.setSubredditName(post.getSubreddit().getName());
        postResponseDTO.setCommentCount(post.getComments().size());
        postResponseDTO.setDuration(ChronoUnit.HOURS.between(post.getCreatedDate(), java.time.LocalDateTime.now()));
        postResponseDTO.setUpVoted(setUpVote(post));
        postResponseDTO.setDownVoted(setDownVote(post));
        return postResponseDTO;
    }

    @Transactional
    public boolean setUpVote(Post post) {
        Vote foundVote = voteRepository.findByPostAndUser(post, userService.findLoggedInUser());
        if (foundVote == null) {
            return false;
        } else if (foundVote.getVoteType().getValue() == -1) {
            return false;
        } else {
            return true;
        }
    }
    @Transactional
    public boolean setDownVote(Post post) {
        Vote foundVote = voteRepository.findByPostAndUser(post, userService.findLoggedInUser());
        if (foundVote == null) {
            return false;
        } else if (foundVote.getVoteType().getValue() == -1) {
            return true;
        } else {
            return false;
        }
    }
}
