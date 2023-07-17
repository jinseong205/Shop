import React, { useEffect, useState } from 'react';
import { Container } from "react-bootstrap";
import Header from '../components/Header';
import Footer from '../components/Footer';


const Home = () => {

    return (
        <>
            <Header />
            <Container>
                <br/>
                <h2>Home</h2>
                {/* {books.map((book) => (<BookItem key = {book.id} book={book}/>)) } */}
            </Container>
        </>
    );
};

export default Home;