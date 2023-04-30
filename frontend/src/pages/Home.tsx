import React, { useEffect, useState } from 'react';
import { Container } from "react-bootstrap";
import Header from '../components/Header';


const Home = () => {

    // const [books, setBooks] = useState([]);

    //함수 실행시 최초 한번 실행되는것
     useEffect(() =>{
         //data 요청 -> promise -> data 다운 완료
         fetch("http://localhost:8080/api/test",{})
         .then(res => res.json())
         .then(res=>{
             console.log(res)
         });
     },[])

    return (
        <>
            <Header />
            <Container>
                <br/>
                <h1>Home</h1>
                {/* {books.map((book) => (<BookItem key = {book.id} book={book}/>)) } */}
            </Container>
        </>
    );
};

export default Home;