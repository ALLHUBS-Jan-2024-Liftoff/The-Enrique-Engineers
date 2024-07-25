// axiosInstance.js

import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080/api/LoginRegistration'  // Adjust base URL as per your backend API
});

export default instance;
;