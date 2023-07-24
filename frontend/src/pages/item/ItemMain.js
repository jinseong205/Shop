import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import Header from '../../components/Header';
import { Link, useLocation, useNavigate } from 'react-router-dom';

const ItemMain = () => {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const [itemSearchDto, setItemSearchDto] = useState(null);
  const pageSize = 9;

  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    setItemSearchDto(location.state?.itemSearchDto || null);
  }, [location.state]);

  useEffect(() => {
    setPage(0);
    loadItems();
  }, [itemSearchDto]);

  useEffect(() => {
    loadItems();
    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  const loadItems = async () => {
    if (loading) return;
    setLoading(true);

    var queryString;
    if (itemSearchDto != null) {
      queryString = Object.keys(itemSearchDto)
        .map((key) => {
          if (key !== 'items') {
            return `${encodeURIComponent(key)}=${encodeURIComponent(itemSearchDto[key])}`;
          }
        })
        .join('&');
    }

    try {
      const response = await fetch(`http://localhost:8080/api/items?page=${page}&${queryString}`);
      const data = await response.json();

      console.log(`http://localhost:8080/api/items?page=${page}&${queryString}`);
      console.log(data.items.content);

      if (page === 0) {
        setItems(data.items.content);
      } else {
        setItems((prevItems) => [...prevItems, ...data.items.content]);
      }

      setLoading(false);
    } catch (error) {
      console.error('Error fetching items:', error);
      setLoading(false);
    }
  };

  useEffect(() => {
    if (page > 0) {
      loadItems();
    }
  }, [page]);

  const handleScroll = () => {
    const { scrollHeight, scrollTop, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 200) {
      setPage((prevPage) => prevPage + 1);
    }
  };

  const handleImageError = (event) => {
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
                <Link className="nav-link text-light text-nowrap" to={`/itemDetail/${item.id}`}>
                  <div className="card">
                    <input type="hidden" value={item.id} />
                    <div style={{ height: '250px', textAlign: 'center', display: 'flex', justifyContent: 'center' }}>
                      <img
                        src={"http://localhost:8080" + item.imgUrl}
                        alt={item.name}
                        style={{ height: '100%', width: '100%', objectFit: 'contain' }}
                        onError={handleImageError}
                      />
                    </div>

                    <div className="mx-4">
                      <div><h5>{item.itemName}</h5></div>
                      <div className="justify-content-end"><b>가격: {item.price}원</b></div>
                    </div>
                  </div>
                </Link>
              </Col>
            ))}
          </Row>
        </div>
      </Container>
    </>
  );
};

export default ItemMain;
