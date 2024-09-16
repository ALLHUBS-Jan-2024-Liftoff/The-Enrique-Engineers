// ItineraryReviewSubmission.js
import React, { useState } from "react";
import { submitReview } from "../../services/itineraryService";

export const ItineraryReviewSubmission = ({ itineraryId, userId }) => {
  const [starRating, setRating] = useState(0);
  const [reviewText, setReviewText] = useState("");
  const [submitted, setSubmitted] = useState(false);

  const handleReviewSubmit = async (e) => {
    e.preventDefault();
    const review = {
      starRating,
      reviewText
    };

    try {
      await submitReview(itineraryId, review, userId);
      setSubmitted(true);
    } catch (error) {
      console.error("There was an error submitting the review!");
    }
  };

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">Review Itinerary</div>
        <div className="card-body">
          {submitted ? (
            <p>Thank you for your review!</p>
          ) : (
            <form onSubmit={handleReviewSubmit}>
              <div className="mb-3">
                <label htmlFor="rating" className="form-label">Rating (1-5)</label>
                <input 
                  type="number" 
                  className="form-control" 
                  id="starRating" 
                  value={starRating} 
                  onChange={(e) => setRating(e.target.value)} 
                  min="1" max="5" 
                  required 
                />
              </div>
              <div className="mb-3">
                <label htmlFor="reviewText" className="form-label">Review</label>
                <textarea 
                  className="form-control" 
                  id="reviewText" 
                  value={reviewText} 
                  onChange={(e) => setReviewText(e.target.value)} 
                  rows="3" 
                  required 
                ></textarea>
              </div>
              <button type="submit" className="btn btn-primary">Submit Review</button>
            </form>
          )}
        </div>
      </div>
    </div>
  );
};
