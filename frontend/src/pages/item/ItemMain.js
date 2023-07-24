import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import Header from '../../components/Header';
import { Link } from 'react-router-dom'

const ItemMain = () => {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const pageSize = 9;

  useEffect(() => {
    loadItems(); // 페이지 로드시 한 번 실행
    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  const loadItems = async () => {
    if (loading) return;
    setLoading(true);

    try {
      const response = await fetch(`http://localhost:8080/api/items/${page}`);
      const data = await response.json();

      console.log(data.items.content);
      console.log(`http://localhost:8080/api/items?page=${page}`);

      setItems((prevItems) => [...prevItems, ...data.items.content]);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching items:', error);
      setLoading(false);
    }
  };

  useEffect(() => {
    console.log(page); // page 값이 업데이트된 후에 실행됩니다.
    if (page > 0) { // 페이지가 0보다 큰 경우에만 loadItems 함수 호출
      loadItems();
    }
  }, [page]);

  const handleScroll = () => {
    const { scrollHeight, scrollTop, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 200) {
      setPage((prevPage) => prevPage + 1); // 페이지를 올바르게 업데이트합니다.
    }
  };

  const handleImageError = (event) => {
    // 이미지 로드에 실패하면 "No Image"로 대체
    event.target.src = 'http://localhost:8080/no-image.png';
  };

  return (
    <>
      <Header />
      <br />
      <Container>
        <div>
          <Row>
            {items.map((item, index) => (
              <Col key={item.id} md={4} className="mb-4">
                <Link className="nav-link text-light text-nowrap" to={`/itemDetail/` + item.id}>
                  <div className="card">
                    <input type="hidden" value={item.id} />
                    <div style={{ height: '250px', textAlign: 'center', display: 'flex', justifyContent: 'center' }}>
                      <img
                        src={"http://localhost:8080" + item.imgUrl}
                        alt={item.name}
                        style={{ height: '100%', width: '100%', objectFit: 'contain' }}
                        onError={handleImageError} // 이미지 로드 실패 시 onError 이벤트 처리
                      />
                    </div>

                    <div className="mx-4">
                      <div><h5>{item.itemName} </h5></div>
                      <div className="justify-content-end"><b>가격: {item.price}원</b></div>
                    </div>
                  </div>
                </Link>
              </Col>
            ))}
          </Row>
        </div >
      </Container >
    </>
  );
};

export default ItemMain;
