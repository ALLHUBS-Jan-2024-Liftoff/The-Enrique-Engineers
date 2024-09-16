import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Login.css';

const LoginForm = ({ setAuthenticated }) => {
  const [loginData, setLoginData] = useState({
    username: '',
    password: ''
  });

  const [loginErrors, setLoginErrors] = useState({
    username: '',
    password: '',
    general: ''
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoginData({ ...loginData, [name]: value });
  };

  const validateLoginForm = () => {
    let valid = true;
    let errors = { username: '', password: '' };

    if (!loginData.username) {
      errors.username = 'Username is required';
      valid = false;
    }

    if (!loginData.password) {
      errors.password = 'Password is required';
      valid = false;
    }

    setLoginErrors(errors);
    return valid;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (validateLoginForm()) {
      try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(loginData),
          credentials: 'include',
        });

        const data = await response.json();

        if (!response.ok) {
          if (response.status === 404) {
            setLoginErrors((prevErrors) => ({
              ...prevErrors,
              username: 'The given username does not exist',
            }));
          } else if (response.status === 401) {
            setLoginErrors((prevErrors) => ({
              ...prevErrors,
              password: 'Invalid password',
            }));
          } else {
            setLoginErrors((prevErrors) => ({
              ...prevErrors,
              general: 'Login failed. Please try again.',
            }));
          }
        } else {
          localStorage.setItem('username', loginData.username);
          setAuthenticated(true);
          navigate('/ItinerariesPage');
        }
      } catch (error) {
        console.error('Error logging in:', error);
        setLoginErrors((prevErrors) => ({
          ...prevErrors,
          general: 'An error occurred. Please try again.',
        }));
      }
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit} className="login-form">
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            name="username"
            className="form-control"
            value={loginData.username}
            onChange={handleChange}
          />
          {loginErrors.username && <p className="error">{loginErrors.username}</p>}
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            name="password"
            className="form-control"
            value={loginData.password}
            onChange={handleChange}
          />
          {loginErrors.password && <p className="error">{loginErrors.password}</p>}
        </div>
        {loginErrors.general && <p className="error">{loginErrors.general}</p>}
        <button type="submit" className="btn btn-primary">Login</button>
        <div className="form-links">
          <Link to="/forgot-password">Forgot Password?</Link>
          <span> | </span>
          <Link to="/register">Register Here?</Link>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
