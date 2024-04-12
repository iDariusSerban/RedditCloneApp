package RedditClone.DTOs.Mappers;

import RedditClone.DTOs.SubredditRequestDTO;
import RedditClone.Model.Subreddit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class SubredditMapper {


    @Mapping(target = "id", ignore=true)
    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    public abstract Subreddit mapSubredditRequestDTOtoSubreddit(SubredditRequestDTO subredditRequestDTO);

}
