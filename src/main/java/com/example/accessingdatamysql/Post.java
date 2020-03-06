package com.example.accessingdatamysql;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "Post")
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "post_id")
    private List<PostComments> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PostComments> getComments() {
        return comments;
    }

    public void setComments(List<PostComments> comments) {
        this.comments = comments;
    }

    public Post(String title, List<PostComments> comments) {
        this.title = title;
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title) &&
                Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, comments);
    }
}

