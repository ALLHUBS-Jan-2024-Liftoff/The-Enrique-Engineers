import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
  const [logoutMessage, setLogoutMessage] = useState('');
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      
      const response = await fetch('http://localhost:8080/api/auth/logout', {
        method: 'POST',
        credentials: 'include', // Ensure cookies are sent
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        // Clear client-side tokens or session data
        localStorage.clear(); // Clears all data in localStorage
        sessionStorage.clear(); // Clears all data in sessionStorage

        
        document.cookie.split(';').forEach((c) => {
          document.cookie = c.trim().split('=')[0] + '=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/';
        });

        // Show a success message
        setLogoutMessage('You have been logged out successfully.');


        setTimeout(() => {
          setLogoutMessage(''); 
          navigate('/'); 
        }, 2000); 
      } else {
        // Handle any errors from the backend
        setLogoutMessage('Failed to log out. Please try again.');
      }
    } catch (error) {
      // Handle any errors that occur during the fetch
      console.error('Error during logout:', error);
      setLogoutMessage('An error occurred. Please try again.');
    }
  };

  return (
    <div>
      {/* Logout button */}
      <button 
        onClick={handleLogout} 
        style={{ 
          padding: '10px 20px', 
          backgroundColor: '#f44336', 
          color: 'white', 
          border: 'none', 
          borderRadius: '5px', 
          cursor: 'pointer' 
        }}
      >
        Logout
      </button>

      {/* Show the logout message if it exists */}
      {logoutMessage && <p>{logoutMessage}</p>}
    </div>
  );
};

export default Logout;
