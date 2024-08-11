import React, { useState } from 'react';
import axios from 'axios';

const Register = () => {
    const [formData, setFormData] = useState({ username: '', password: '' });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/api/register', formData)
            .then(response => {
                alert('Registration successful!');
            })
            .catch(error => {
                console.error('There was an error!', error);
                alert('There was an error!');
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Register</h2>
            <div>
                <label>Username:</label>
                <input type="text" name="username" onChange={handleChange} required />
            </div>
            <div>
                <label>Password:</label>
                <input type="password" name="password" onChange={handleChange} required />
            </div>
            <button type="submit">Register</button>
        </form>
    );
};

export default Register;
