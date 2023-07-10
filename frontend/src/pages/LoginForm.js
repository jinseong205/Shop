import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Container } from "react-bootstrap";
import Header from '../components/Header';
import Footer from '../components/Footer';
import Form from 'react-bootstrap/Form';

const LoginForm = () => {


  //const [username, setUsername] = useState("");
  //const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const [user, setUser] = useState({
    username: "",
    password: "",
  });

  const changeValue = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  }

  const login = async (e) => {
    e.preventDefault();

    fetch("http://localhost:8080/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json; charset=utf-8"
      },
      body: JSON.stringify(user)

    })
      .then(res => {
        if (res.status === 200) {
          console.log(res.headers.get("Authorization"));
          if (res.headers.get("Authorization")) {
            localStorage.setItem('token', res.headers.get("Authorization"));
            navigate("/");
          }
        } else {
          return res.json();
        }
      })
      .then(data => {
        if(data != null){
          console.log(data);  
          alert(data.message);
        }
      })
      .catch(err => {
        if(err){
        alert("로그인 중 오류가 발생 하였습니다." + err);
        }
      });
  }

  return (
    <>
      <Header />
      <Container>

        <div className="col-md-12">

          <h1>로그인</h1>
          <Form onSubmit={login}>
            <div className="form-group">
              <label htmlFor="username">ID</label>
              &nbsp;
              <input
                type="text"
                className="form-control"
                name="username"
                onChange={changeValue}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password</label>
              &nbsp;
              <input
                type="password"
                className="form-control"
                name="password"
                onChange={changeValue}
                required
              />
            </div>

            <div className="form-group mt-1">
              <button
                className="btn btn-secondary btn-block"
              >

                <span>Login</span>
              </button>
            </div>


          </Form>
        </div>
      </Container>
      <Footer />
    </>
  );
};

export default LoginForm;