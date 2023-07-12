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
    itemSellStatus: "",
    itemImgIds: []
  });

  const [itemImgFileList, setItemImgFileList] = useState([]);

  const changeItemValue = (e) => {
    setItem({
      ...item,
      [e.target.name]: e.target.value,
    });
  };

  const handleFileUpload = (event) => {
    const files = event.target.files;
    const updatedFileList = Array.from(files);
    setItemImgFileList((prevFileList) => [...prevFileList, ...updatedFileList]); // 파일 리스트에 새로운 파일들을 추가하여 업데이트합니다.
  };

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
          alert("상품 정보를 가져오는 중 오류가 발생했습니다. \n" + err);
        });
    }
  }, [id]);

  const handleItemRegister = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('itemFormDto', new Blob([JSON.stringify(item)], { type: 'application/json' }));
    
    // 이미지 파일들을 추가
    // for (let i = 0; i < file.length; i++) {
    //   formData.append('itemImgFile', file[i]);
    // }
    
    itemImgFileList.forEach((file, index) => {
      formData.append('itemImgFile', file); // 파일을 FormData에 리스트 형태로 추가합니다.
    });    
    console.log([...formData.keys()]);

    fetch('http://localhost:8080/api/manager/item/new', {
      method: 'POST',
      headers :{
        'Authorization' : localStorage.getItem("token"),
      },
      body: formData,
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
          alert(data.message);
        }
      })
      .catch(err => {
        alert("상품등록 중 오류가 발생 하였습니다.");
      });
  };
  const renderFileUploadFields = () => {
    const fileUploadFields = [];

    for (let i = 0; i < 5; i++) {
      fileUploadFields.push(
        <div key={i} className="form-group">
          <label htmlFor={`itemImgFile${i}`}>상품 이미지 {i + 1}</label>
          <input
            type="file"
            className="form-control"
            id={`itemImgFile${i}`}
            name={`itemImgFile${i}`} // 각 파일 입력 필드에 고유한 이름을 할당합니다.
            onChange={handleFileUpload}
          />
        </div>
      );
    }

    return fileUploadFields;
  };

  return (
    <>
      <Header />
      <Container>
        <div className="col-md-12">
          <h1>상품등록</h1>
          <form>
            <select className="form-select" aria-label="Default select example" name="itemSellStatus" onChange={changeItemValue}>
              <option value="SELL">판매 중</option>
              <option value="SOLD_OUT">품절</option>
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

            {renderFileUploadFields()}

            {!id && (
              <div className="form-group mt-1">
                <button className="btn btn-secondary btn-block" onClick={handleItemRegister}>상품 등록</button>
              </div>
            )}
            {id && (
              <div className="form-group mt-1">
                <button className="btn btn-secondary btn-block" >상품 수정</button>
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