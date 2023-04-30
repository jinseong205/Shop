import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import authService from "../services/auth.service";

const JoinForm = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [successful, setSuccessful] = useState(false);
    const [message, setMessage] = useState("");

    const handleSignup = (e : any) => {
        e.preventDefault();
        authService.register(email, password).then(
            (response) => {
                setMessage(response.data.message);
                setSuccessful(true);
                navigate("/login");
            },
            (error) => {
                setMessage(error.response.data.message);
                setSuccessful(false);
            }
        );
    };

    return (
        <>        <div className="col-md-12">
            <div className="card card-container">
                <Link to="/" style={{ textDecoration: "none" }}><h1>JWT Sample</h1></Link>
                <form onSubmit={handleSignup}>
                    <div className="form-group">
                        <label htmlFor="email">E-Mail</label>
                        &nbsp;
                        <input
                            type="text"
                            className="form-control"
                            id="email"
                            required
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            name="email"
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        &nbsp;
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            required
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            name="password"
                        />
                    </div>

                    <div className="form-group">
                        <button className="btn btn-primary btn-block">Sign Up</button>
                    </div>

                    {message && (
                        <div className="form-group">
                            <div
                                className={
                                    successful ? "alert alert-success" : "alert alert-danger"
                                }
                                role="alert"
                            >
                                {message}
                            </div>
                        </div>
                    )}
                </form>
            </div>
        </div>
        </>

    );
};

export default JoinForm;