import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080/api', // Your backend base URL
  timeout: 1000,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true // Include credentials if necessary
});

export default instance;