import React, {useState, useEffect} from "react";

export const SearchBar = ({ onSearch }) => {
    const [searchQuery, setQuery] = useState('');
    const [categories, setCategories] = useState({
        catering: false,
        natural: false,
        entertainment: false,
        accommodation: false,
        tourism: false,
    });

    const handleCheckboxChange = (event) => {
        setCategories({
            ...categories,
            [event.target.name]: event.target.checked,
        });
    };

    const handleSearch = () => {
        // Convert categories object to list of selected category strings
        const extractedCategories = Object.keys(categories).filter(key => categories[key]);
        const selectedCategories = extractedCategories.length > 0 ? extractedCategories.join(',') : '';
        console.log(selectedCategories);
        // Pass categories with searchQuery to search method 
        onSearch(searchQuery, selectedCategories);
    };

    return (
        <div>
            <input
                type="text"
                value={searchQuery}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Search for a city..."
            />
            <div>
                <label>
                    <input
                        type="checkbox"
                        name="catering"
                        checked={categories.catering}
                        onChange={handleCheckboxChange}
                    />
                    Restaurants
                </label>
                <label>
                    <input
                        type="checkbox"
                        name="natural"
                        checked={categories.natural}
                        onChange={handleCheckboxChange}
                    />
                    Natural
                </label>
                <label>
                    <input
                        type="checkbox"
                        name="entertainment"
                        checked={categories.entertainment}
                        onChange={handleCheckboxChange}
                    />
                    Entertainment
                </label>
                <label>
                    <input
                        type="checkbox"
                        name="accommodation"
                        checked={categories.accommodation}
                        onChange={handleCheckboxChange}
                    />
                    Accommodation
                </label>
                <label>
                    <input
                        type="checkbox"
                        name="tourism"
                        checked={categories.tourism}
                        onChange={handleCheckboxChange}
                    />
                    Tourism
                </label>
            </div>
            <button onClick={handleSearch}>Search</button>
        </div>
    );
}; 