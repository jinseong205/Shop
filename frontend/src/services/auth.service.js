import axios from 'axios';

const API_URL = 'http://localhost:8080/';

const register = (email , password) => {
  const user = {
    email: email,
    password: password
  };
  
  return axios.post(API_URL + 'join', user);
};

const login = (username, password) => {
  const user = {
    username: username,
    password: password
  };

      

  return axios.post(API_URL + 'login', user)
    .then((response) => {
      console.log(response.headers.get("Authorization"));
      alert("response :" + response.data);
      if (response.data.accessToken) {
        localStorage.setItem('user', JSON.stringify(response.data));
      }
      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem('user');
};

const getCurrentUser = () => {
  return JSON.parse( localStorage.getItem('user'));
};

export default {
  register,
  login,
  logout,
  getCurrentUser
};  