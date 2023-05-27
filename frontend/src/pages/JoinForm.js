import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Container } from "react-bootstrap";
import Header from "../components/Header";
import Footer from "../components/Footer";

const JoinForm = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [user, setUser] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
        addr: ""
    });

    const changeValue = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value,
        });
    }

    const handleSignup = (e) => {
        e.preventDefault();


        fetch("http://localhost:8080/api/join", {
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          body: JSON.stringify(user)
    
        })
          .then(res => {
            if (res.status === 200) {
                alert("회원가입이 완료되었습니다.");
                navigate("/login");
            } else {
              return res.json();
            }
          })
          .then(data => {
            if(data != null){
                console.log(data);
                alert(data.errorMessage);
              }
          })
          .catch(err => {
            alert("회원가입 중 오류가 발생 하였습니다.");
          });
    };

    return (
        <>
            <Header />
            <Container>
                <div className="col-md-12">
                    <h1>회원가입</h1>
                    <form onSubmit={handleSignup}>
                        <div className="form-group">
                            <label htmlFor="username">ID</label>
                            &nbsp;
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                onChange={changeValue}
                                name="username"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            &nbsp;
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                onChange={changeValue}
                                name="password"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="name">이름</label>
                            &nbsp;
                            <input
                                type="text"
                                className="form-control"
                                name="name"
                                onChange={changeValue}
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="email">E-Mail</label>
                            &nbsp;
                            <input
                                type="email"
                                className="form-control"
                                name="email"
                                onChange={changeValue}
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="addr">주소</label>
                            &nbsp;
                            <input
                                type="text"
                                className="form-control"
                                name="addr"
                                onChange={changeValue}
                            />
                        </div>


                        <div className="form-group mt-1">
                            <button className="btn btn-secondary btn-block">회원가입</button>
                        </div>

                    </form>
                </div>
            </Container>
            <Footer />
        </>


    );
};

export default JoinForm;