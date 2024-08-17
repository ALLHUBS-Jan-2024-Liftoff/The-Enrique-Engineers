import React, { useState, useEffect } from "react";
import { Route, Routes, Link } from "react-router-dom"
import { useParams } from "react-router-dom";
import {
  getItineraryName,
  getReviewsForItinerary,
} from "../../services/itineraryService";
import { ItineraryReviewSubmission } from "./ItineraryReviewSubmission";

export const ItineraryReviewPage = () => {
  const { itineraryId } = useParams();
  const [itineraryName, setItineraryName] = useState([]);
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    getItineraryName(itineraryId)
      .then(setItineraryName)
      .catch((error) => {
        console.error("There was an error fetching the itinerary name!", error);
      });
      console.log("userEffect getName triggered")
  });

  useEffect(() => {
    getReviewsForItinerary(itineraryId)
    .then((reviews) => setReviews(reviews))
    .catch((error) => {
      console.error("There was an error fetching reviews!", error)
    });
    console.log("userEffect getReviews triggered")
  }, [itineraryId]);

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">Review Itinerary</div>
        <div className="card-body">
          <h1>{itineraryName}</h1>
        </div>
      <div className="card-body">
        <button className="btn btn-primary"><Link to={"/ItineraryEditPage/" + itineraryId} target="_blank">View Itinerary Details</Link></button>
      </div>
        <div className="card-body">
          {reviews.length > 0 ? (
            reviews.map(review => (
              <div key={review.id} className="mb-3">
                <strong>Rating:</strong> {review.starRating} / 5
                <p>{review.reviewText}</p>
              </div>
            ))
          ) : (
            <p>No reviews yet. Log your review of this trip here!</p>
          )}
        </div>
        <div className="card-body">
          <ItineraryReviewSubmission />
        </div>
      </div>
    </div>
  );
};
