import React, { useState } from 'react';
import axios from 'axios';
import './signupForm.css'

const LoginForm = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [userData, setUserData] = useState({
    username: '',
    password: '',
    confirmPassword: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData({
      ...userData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isLogin) {
      try {
        const response = await axios.post('/api/login', {
          username: userData.username,
          password: userData.password
        });
        console.log('Login successful:', response.data);
        // Handle successful login (e.g., redirect to dashboard)
      } catch (error) {
        console.error('Login error:', error);
        // Handle login error (e.g., show error message)
      }
    } else {
      try {
        const response = await axios.post('/api/register', {
          username: userData.username,
          password: userData.password
        });
        console.log('Registration successful:', response.data);
        // Handle successful registration (e.g., show success message)
      } catch (error) {
        console.error('Registration error:', error);
        // Handle registration error (e.g., show error message)
      }
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
            <label htmlFor="confirmPassword">Confirm Password:</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={userData.confirmPassword}
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
