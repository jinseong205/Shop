import React, { useEffect, useState } from 'react';
import { Container } from "react-bootstrap";
import Header from '../components/Header';
import Footer from '../components/Footer';
import { json } from 'react-router-dom';


const Test = () => {

    // const [books, setBooks] = useState([]);

    //함수 실행시 최초 한번 실행되는것
     useEffect(() =>{
         //data 요청 -> promise -> data 다운 완료
         fetch("http://localhost:8080/api/test/ex0",{})
         .then(res=>res.json())
         .then(res=>{
              alert("로그인 실패 : " + res);
              console.log(res)}
            );

     },[])

    return (
        <>
            <Header />
            <Container>
                <br/>
                <h1>Test</h1>
                {/* {books.map((book) => (<BookItem key = {book.id} book={book}/>)) } */}
            </Container>
            <Footer />
        </>
    );
};

export default Test;