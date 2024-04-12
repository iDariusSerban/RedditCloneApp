package RedditClone.Services;

import RedditClone.DTOs.PostRequestDTO;
import RedditClone.DTOs.PostResponseDTO;
import RedditClone.Model.Post;
import RedditClone.Model.Subreddit;
import RedditClone.Model.User;
import RedditClone.Repositories.PostRepository;
import RedditClone.Repositories.SubredditRepository;
import RedditClone.Repositories.UserRepository;
import RedditClone.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    private PostMapperService postMapperService;

    private UserRepository userRepository;
    private SubredditRepository subredditRepository;



    @Autowired
    public PostService(PostRepository postRepository, PostMapperService postMapperService, UserRepository userRepository, SubredditRepository subredditRepository) {
        this.postRepository = postRepository;
        this.postMapperService = postMapperService;
        this.userRepository = userRepository;
        this.subredditRepository = subredditRepository;

    }

    @Transactional
    public Post createPost(PostRequestDTO postRequestDTO){

        Post post = postMapperService.mapPostRequestDTOtoPost(postRequestDTO);
        User user = userRepository.findById(postRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
        Subreddit subreddit = subredditRepository.findById(postRequestDTO.getSubredditId()).orElseThrow(() ->new RuntimeException("subreddit not found"));
        post.setSubreddit(subreddit);
        post.setUser(user);

        return postRepository.save(post);

    }

    @Transactional
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> postMapperService.mapPostToDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDTO getPostBy(Long id) {
        Post foundPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));
        //return constructNewPostResponseDTO(foundPost);
        return  postMapperService.mapPostToDto(foundPost);
    }
    @Transactional
    public List<PostResponseDTO> getPostsBySubreddit(Long id) {
        Subreddit foundSubreddit = subredditRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("subreddit not found"));
        return postRepository.findBySubreddit(foundSubreddit).stream()
                .map(post -> postMapperService.mapPostToDto(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PostResponseDTO> getPostsByUser(Long id) {
        User foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return postRepository.findByUser(foundUser).stream()
                .map(post -> postMapperService.mapPostToDto(post))
                .collect(Collectors.toList());
    }
    @Transactional
    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));
    }
    @Transactional
    public Post update(Post post) {
        return postRepository.save(post);
    }


}
