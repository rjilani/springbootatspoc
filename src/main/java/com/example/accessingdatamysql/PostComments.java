package com.example.accessingdatamysql;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.Objects;

@Entity(name = "PostComment")
@Table(name = "post_comment")
public class PostComments {

    @Id
    @GeneratedValue
    private Long id;

    private String review;

    public PostComments(String review) {
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostComments)) return false;
        PostComments that = (PostComments) o;
        return Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review);
    }
}