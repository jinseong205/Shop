import React, { useEffect, useState } from 'react';
import { Container } from "react-bootstrap";
import Header from '../../components/Header';
import Footer from '../../components/Footer';


const ProductForm = () => {

    return (
        <>
            <Header />
            <Container>
                <br/>
                <h1>상품 등록</h1>
                {/* {books.map((book) => (<BookItem key = {book.id} book={book}/>)) } */}
            </Container>
            <Footer />
        </>
    );
};

export default ProductForm;