import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login, register } from  "../../services/loginService"; // Import the service functions
import './signupForm.css';

const LoginForm = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [userData, setUserData] = useState({
    username: '',
    password: '',
    verifyPassword: '' // Use verifyPassword here
  });
  const navigate = useNavigate(); // Correctly get the navigate function

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData({
      ...userData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isLogin) {
        const response = await login(userData.username, userData.password);
        console.log('Login successful:', response);
        
      } else {
        if (userData.password !== userData.verifyPassword) {
          console.error('Passwords do not match.');
          return;
        }
        const response = await register(userData.username, userData.password);
        console.log('Registration successful:', response);
        
      }
    } catch (error) {
      console.error('Authentication error:', error);
      // Optionally display an error message to the user
    }
  };

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit}>
        <div>
          <h1 className="form-header">Login or Register Here!</h1>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={userData.username}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={userData.password}
            onChange={handleChange}
          />
        </div>
        {!isLogin && (
          <div>
            <label htmlFor="verifyPassword">Verify Password:</label>
            <input
              type="password"
              id="verifyPassword"
              name="verifyPassword" // Use verifyPassword here
              value={userData.verifyPassword}
              onChange={handleChange}
            />
          </div>
        )}
        <button type="submit">{isLogin ? 'Login' : 'Register'}</button>
        <button type="button" onClick={() => setIsLogin(!isLogin)}>
          {isLogin ? 'Switch to Register' : 'Switch to Login'}
        </button>
      </form>
    </div>
  );
};

export default LoginForm;