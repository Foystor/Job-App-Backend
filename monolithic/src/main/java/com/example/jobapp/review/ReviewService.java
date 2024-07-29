package com.example.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long companyId);

    boolean createReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
