import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';


const RegistrationPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [verifyPassword, setVerifyPassword] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();

    if (password !== verifyPassword) {
      setError('Passwords do not match');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password, verifyPassword }),
        credentials: 'include', // Include cookies for session handling
      });

      if (response.ok) {
        navigate('/'); 
      } else {
        const result = await response.json();
        setError(result.message || 'Registration failed');
      }
    } catch (error) {
      setError('An error occurred');
    }
  };

  return (
    <div className="Registration-page">
      <div className="register-container">
        <h2>Register Here!</h2>
        <form onSubmit={handleRegister}>
          <div>
            <label>Username</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div>
            <label>Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div>
            <label>Verify Password</label>
            <input
              type="password"
              value={verifyPassword}
              onChange={(e) => setVerifyPassword(e.target.value)}
              required
            />
          </div>
          {error && <p className="error">{error}</p>}
          <button type="submit">Register</button>
        </form>
        <div className="links">
          <p>
            Have an account already? <Link to="/">Return to Login Here!</Link>
          </p>
          <p>
            <Link to="/">Back to Home</Link>
          </p>


        </div>
      </div>
    </div>
  );
};

export default RegistrationPage;