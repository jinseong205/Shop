import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import { Container } from "react-bootstrap";
import { useParams } from "react-router";
import Header from '../../components/Header';
import Footer from '../../components/Footer';


const ItemForm = () => {
    const navigate = useNavigate();
    const { id } = useParams();

    const [item, setItem] = useState({
        itemName: "",
        price: "",
        stockNum: "",
        itemDetail: "",
        itemSellStatus: ""
    });

    const changeValue = (e) => {
        setItem({
            ...item,
            [e.target.name]: e.target.value,
        });
        console.log(item);
    }

    useEffect(() => {
        if (id) {
            // id 값이 존재하는 경우, 해당 id에 해당하는 상품 정보를 가져온다.
            fetch(`http://localhost:8080/api/manager/item/${id}`)
                .then(res => res.json())
                .then(data => {
                    if (data != null) {
                        console.log(data);
                        alert("상품 정보를 가져오는 중 오류가 발생했습니다. \n" + data.message);
                    } else {
                        setItem(data); // 가져온 상품 정보로 item 상태를 업데이트한다.
                    }   
                })
                .catch(err => {
                    alert("상품 정보를 가져오는 중 오류가 발생했습니다. \n" +  err);
                });
        }
    }, [id]);

    const handleItemRegister = (e) => {
        e.preventDefault();

        fetch("http://localhost:8080/api/manager/item", {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(item)

        })
            .then(res => {
                if (res.status === 200) {
                    alert("상품등록이 완료되었습니다.");
                    navigate("/");
                } else {
                    return res.json();
                }
            })
            .then(data => {
                if (data != null) {
                    console.log(data);
                    alert(data.errorMessage);
                }
            })
            .catch(err => {
                alert("상품등록 중 오류가 발생 하였습니다.");
            });
    };

    const handleItemUpdate = (e) => {
        console.log(item);
    }

    return (
        <>
            <Header />
            <Container>
                <div className="col-md-12">
                    <h1>상품등록</h1>
                    <form>
                        {/* <form onSubmit={handleItemUp}> */}

                        <select className="form-select" aria-label="Default select example" name="itemSellStatus" onChange={changeValue}>
                            <option value="SELL">판매 중</option>
                            <option value="SOLD_OUT">품절</option>
                        </select>

                        <div className="form-group">
                            <label htmlFor="username">상품명</label>
                            &nbsp;
                            <input
                                type="text"
                                className="form-control"
                                id="itemName"
                                onChange={changeValue}
                                name="itemName"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="username">상품명</label>
                            &nbsp;
                            <input
                                type="text"
                                className="form-control"
                                id="itemName"
                                onChange={changeValue}
                                name="itemName"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="password">상품 가격</label>
                            &nbsp;
                            <input
                                type="number"
                                className="form-control"
                                id="price"
                                onChange={changeValue}
                                name="price"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="email">재고</label>
                            &nbsp;
                            <input
                                type="text"
                                className="form-control"
                                name="stockNuber"
                                onChange={changeValue}
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="name">상품 상세 내용</label>
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
                            <label htmlFor="addr">상품 이미지</label>
                            &nbsp;
                            <input
                                type="file"
                                className="form-control"
                                name="itemImgFile"
                                onChange={changeValue}
                            />
                        </div>


                        {!id && (
                            <div className="form-group mt-1">
                                <button className="btn btn-secondary btn-block" onClick={handleItemRegister}>상품 등록</button>
                            </div>
                        )}
                        {id && (
                            <div className="form-group mt-1">
                                <button className="btn btn-secondary btn-block" onClick={handleItemUpdate}>상품 수정</button>
                            </div>
                        )}
                    </form>
                </div>
            </Container>
            <Footer />
        </>
    );
};

export default ItemForm;