import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Ensure you import useNavigate
import './Google.css'; // Ensure you import the CSS file

// Google OAuth 2.0 login component
const GoogleLogin = ({ onSuccess }) => {
  const YOUR_CLIENT_ID = '562269835682-vjv4akf84462tffrat30qqniqedoel02.apps.googleusercontent.com';
  const YOUR_REDIRECT_URI = 'http://localhost:5173'; // Ensure this matches your configuration
  const navigate = useNavigate(); // Initialize useNavigate

  // Function to generate a random state value
  const generateCryptoRandomState = () => {
    const randomValues = new Uint32Array(2);
    window.crypto.getRandomValues(randomValues);

    // Encode as UTF-8 and Base64
    const utf8Encoder = new TextEncoder();
    const utf8Array = utf8Encoder.encode(String.fromCharCode(...randomValues));
    return btoa(String.fromCharCode(...utf8Array))
      .replace(/\+/g, '-')
      .replace(/\//g, '_')
      .replace(/=+$/, '');
  };

  // Function to initiate OAuth2 sign-in
  const oauth2SignIn = () => {
    const state = generateCryptoRandomState();
    localStorage.setItem('state', state);

    const oauth2Endpoint = 'https://accounts.google.com/o/oauth2/v2/auth';
    const params = new URLSearchParams({
      client_id: YOUR_CLIENT_ID,
      redirect_uri: YOUR_REDIRECT_URI,
      scope: 'https://www.googleapis.com/auth/drive.metadata.readonly',
      state: state,
      include_granted_scopes: 'true',
      response_type: 'token',
    });

    // Redirect to Google's OAuth 2.0 server
    window.location.href = `${oauth2Endpoint}?${params.toString()}`;
  };

  // Handle token extraction and redirection on component mount
  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.hash.replace('#', '?'));
    const accessToken = urlParams.get('access_token');
    const state = urlParams.get('state');

    const storedState = localStorage.getItem('state');
    if (state && state !== storedState) {
      console.error('Invalid state parameter');
      return;
    }

    if (accessToken) {
      localStorage.setItem('access_token', accessToken);
      window.location.hash = ''; // Clear the hash

      // Call the onSuccess callback and redirect to /home
      if (onSuccess) onSuccess();
      navigate('/home');
    } else if (storedState) {
      // Handle case where access token is not present
      console.error('No access token found');
    }
  }, [navigate, onSuccess]);

  return (
    <div className="google-login">
      <p>Sign in with Google</p>
      <button className="google-login-button" onClick={oauth2SignIn}>
        <span>Login with Google</span>
      </button>
    </div>
  );
};

export default GoogleLogin;