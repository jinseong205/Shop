
import React, { useState, useEffect } from 'react';

const ItemMain = () => {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const pageSize = 9; // 한 번에 보여줄 아이템 갯수 (3x3 그리드라면 9개씩)

  useEffect(() => {
    loadItems(); // 페이지가 로드될 때 아이템을 초기화하고, 추가로 가져옵니다.
    window.addEventListener('scroll', handleScroll); // 스크롤 이벤트 리스너 등록
    return () => window.removeEventListener('scroll', handleScroll); // 컴포넌트가 언마운트될 때 리스너 제거
  }, []);

  // 아이템들을 추가로 로드하는 함수
  const loadItems = async () => {
    if (loading) return; // 로딩 중일 때는 추가 요청을 방지합니다.
    setLoading(true);

    // 서버에서 새로운 페이지의 아이템 데이터를 가져옵니다. (Ajax 요청 또는 fetch 등을 사용)
    // fetch(`/api/items?page=${page}&pageSize=${pageSize}`)
    //   .then((response) => response.json())
    //   .then((data) => {
    //     setItems((prevItems) => [...prevItems, ...data]); // 기존 아이템들과 새로운 데이터를 병합합니다.
    //     setPage((prevPage) => prevPage + 1); // 페이지 번호를 증가시킵니다.
    //     setLoading(false);
    //   })
    //   .catch((error) => {
    //     console.error('Error fetching items:', error);
    //     setLoading(false);
    //   });

    // 위의 주석처리된 부분은 실제 서버와 통신하여 데이터를 가져오는 로직입니다.
    // 페이지 로드를 시뮬레이션하기 위해 아래와 같이 임시 데이터를 사용합니다.
    const dummyData = Array.from({ length: pageSize }, (_, index) => ({
      id: page * pageSize + index + 1,
      name: `Item ${page * pageSize + index + 1}`,
    }));

    setTimeout(() => {
      setItems((prevItems) => [...prevItems, ...dummyData]);
      setPage((prevPage) => prevPage + 1);
      setLoading(false);
    }, 1000); // 1초 뒤에 데이터를 추가로 로드하여 시뮬레이션합니다.
  };

  // 스크롤 이벤트 핸들러
  const handleScroll = () => {
    const { scrollHeight, scrollTop, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 200) {
      // 스크롤이 맨 아래로 내려갔을 때 추가로 아이템을 로드합니다.
      loadItems();
    }
  };

  return (
    <>
      <Header />
      <Container>
        <div>
          <div className="row">
            {items.map((item) => (
              <div key={item.id} className="col-md-4 margin">
                <div className="card">
                  <h4>{item.name}</h4>
                  {/* 여기에 각 아이템의 추가 정보 및 이미지 등을 추가하면 됩니다. */}
                  <img src={item.imageUrl} alt={item.name} />
                  <p>{item.description}</p>
                  <h5>가격: {item.price}원</h5>
                  {/* ... */}
                </div>
              </div>
            ))}
          </div>
        </div>
      </Container >
    </>
  );
};

export default ItemMain;