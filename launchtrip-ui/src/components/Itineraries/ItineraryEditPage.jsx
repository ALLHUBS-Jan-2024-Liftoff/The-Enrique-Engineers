import { useParams } from "react-router-dom"

export function ItineraryEditPage() {
  const { itineraryId } = useParams();
  
  return (
        <div className="mt-5 container">
          <div className="card">
            <div className="card-header">Edit Your Itinerary</div>
            <div className="card-body">
                <h1>{"You are editing itinerary " + itineraryId}</h1>
            </div>
          </div>
        </div>
      );
}