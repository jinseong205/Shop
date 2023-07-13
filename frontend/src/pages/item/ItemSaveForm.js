import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import { Form, Row, Col, Container } from "react-bootstrap";
import { useParams } from "react-router";
import Header from '../../components/Header';
import Footer from '../../components/Footer';

const ItemSaveForm = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [itemFormDto, setItemFormDto] = useState({
    itemName: "",
    price: "",
    stockNum: "",
    itemDetail: "",
    itemSellStatus: "SELL",
    itemImgDtoList: [],
    itemImgIds: []
  });

  const changeItemValue = (e) => {
    let value = e.target.value;
  
    if (e.target.name === "price" || e.target.name === "stockNum") {
      value = value.replace(/\D/g, ""); 
      document.getElementById(e.target.name).value = value;
    }
  
    setItemFormDto({
      ...itemFormDto,
      [e.target.name]: value,
    });
  };

  const onFileChange = (e, index) => {
    const file = e.target.files[0];
    const reader = new FileReader();

    reader.onloadend = () => {
      const itemImgName = document.getElementById(`itemImgName${index}`);
      if (itemImgName) {
        itemImgName.textContent = file ? file.name : "";
      }
    };

    reader.readAsDataURL(file);
  };

  useEffect(() => {

    if (id) {
      // id 값이 존재하는 경우, 해당 id에 해당하는 상품 정보를 가져온다.
      fetch(`http://localhost:8080/api/item/${id}`)
        .then(res => res.json())
        .then(data => {
          if (data.id != null) {
            //setItem(data); // 가져온 상품 정보로 item 상태를 업데이트한다.
          } else {
            alert(data.message);
          }
        })
        .catch(err => {
          alert("상품 정보를 가져오는 중 오류가 발생했습니다. \n" + err);
        });
    }
  }, [id]);

  const handleItemRegister = (e) => {
    e.preventDefault();
    const formData = new FormData();

    formData.append('itemFormDto', new Blob([JSON.stringify(itemFormDto)], { type: 'application/json' }));

    const fileInputs = document.querySelectorAll('input[type="file"]');

    fileInputs.forEach((input) => {
      const files = input.files;
      if (files.length > 0) {
        const file = files[0];
        formData.append('itemImgFile', file);
      }
    });


    console.log([...formData.keys()]);

    fetch('http://localhost:8080/api/manager/item', {
      method: 'POST',
      headers: {
        'Authorization': localStorage.getItem("token"),
      },
      body: formData,
    })
      .then(res => {
        //console.log(res);  
        return res.json();
      })
      .then(data => {
        if (data.id != null) {
          alert("상품등록에 성공하였습니다.");
          navigate(`/ItemUpdateForm/${data.id}`)
        } else {
          alert(data.message);
        }
      })
      .catch(err => {
        alert("상품등록 중 오류가 발생 하였습니다.");
      });
  };

  const renderFileUploadFields = () => {
    const fileUploadFields = [];

    for (let i = 1; i <= 5; i++) {
      fileUploadFields.push(
        <Form.Group key={i}>
          <input type="hidden" name="itemImgId" id={`itemImgId${i}`} />
          <Row className="align-items-center">
            <Col xs="auto">
              <label className="input-group-text file-label" htmlFor={`itemImgFile${i}`}>
                파일선택 {i}
                <input
                  type="file"
                  id={`itemImgFile${i}`}
                  name={`itemImgFile`}
                  style={{ display: "none" }}
                  onChange={(e) => onFileChange(e, i)}
                />
              </label>
            </Col>
            <Col>
              <div style={{ fontSize: "12px" }} id={`itemImgName${i}`}></div>
            </Col>
          </Row>
        </Form.Group>
      );
    }

    return fileUploadFields;
  };


  return (
    <>
      <Header />
      <Container>
        <div className="col-md-12">
          <h1>상품 등록</h1>
          <br />
          <form>
            <select className="form-select" name="itemSellStatus" onChange={changeItemValue}>
              <option value="SELL">판매중</option>
              <option value="SOLD_OUT" >품절</option>
            </select>

            <div className="form-group">
              <label htmlFor="itemName">상품명</label>
              <input
                type="text"
                className="form-control"
                id="itemName"
                name="itemName"
                onChange={changeItemValue}
              />
            </div>

            <div className="form-group">
              <label htmlFor="price">상품 가격</label>
              <input
                type="text"
                className="form-control"
                id="price"
                name="price"
                onChange={changeItemValue}
              />
            </div>

            <div className="form-group">
              <label htmlFor="stockNum">재고</label>
              <input
                type="text"
                className="form-control"
                id="stockNum"
                name="stockNum"
                onChange={changeItemValue}
              />
            </div>

            <div className="form-group">
              <label htmlFor="itemDetail">상품 상세 내용</label>
              <input
                type="text"
                className="form-control"
                id="itemDetail"
                name="itemDetail"
                onChange={changeItemValue}
              />
            </div>

            <br/>
            {renderFileUploadFields()}
            <br/>

            <div className="form-group mt-1">
              <button className="btn btn-secondary btn-block" onClick={handleItemRegister}>상품 등록</button>
            </div>


          </form>
        </div>
      </Container>
      <Footer />
    </>
  );
};

export default ItemSaveForm;