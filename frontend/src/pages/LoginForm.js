import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Container } from "react-bootstrap";
import Header from '../components/Header';
import Footer from '../components/Footer';
import Form from 'react-bootstrap/Form';

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();


  const Login = async (e) => {
    e.preventDefault();

    try {
      fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify({
          username: username,
          password: password
        })

      }).then((res) => {
        //console.log(res)
        if (res.status === 200) {
          console.log(res.headers.get("Authorization"));
          if (res.headers.get("Authorization")) {
            localStorage.setItem('user', res.headers.get("Authorization"));
          }
          return res.headers.get("Authorization");
        } else {
          return null;
        }
      }
      ).then((res) => {
        navigate("/");
      });

    } catch (error) {
      const resMessage =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        error.message ||
        error.toString();

      alert("로그인 실패 : " + resMessage);
    }


  }

  return (
    <>
      <Header/>
      <Container>
        <div className="col-md-12">

            <h1>로그인</h1>
            <Form onSubmit={Login}>
              <div className="form-group">
                <label htmlFor="username">ID</label>
                &nbsp;
                <input
                  type="text"
                  className="form-control"
                  name="username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
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
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
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
      <Footer/>
    </>
  );
};

export default LoginForm;