import React, {useState, useEffect} from "react";

export const SearchBar = ({ onSearch }) => {
    const [searchQuery, setSearchQuery] = useState(""); 

    const handleSearch = () => {
        onSearch(searchQuery);
    };

    return (
        <div className="search">
            <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Search by city"
                className="form-control"
            />
            <button onClick={handleSearch} className="btn btn-primary mt-2">
                Search
            </button>
        </div>
    )
}; 