import React, { useEffect, useState } from 'react';

const ItemDetail = () => {
  const [count, setCount] = useState(1);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    calculateTotalPrice();
  }, [count]);

  function calculateTotalPrice() {
    const price = parseInt(document.getElementById('price').value, 10);
    const totalCount = parseInt(count, 10);
    const calculatedTotalPrice = price * totalCount;
    setTotalPrice(calculatedTotalPrice);
  }

  function handleCountChange(event) {
    setCount(event.target.value);
  }

  function order() {
    // 주문 처리 로직 (React 기반으로 변경 필요)
    // ...
  }

  function addCart() {
    // 장바구니 추가 처리 로직 (React 기반으로 변경 필요)
    // ...
  }

  return (
    <>
      <Header />
      <Container>
        <div style={{ marginLeft: '25%', marginRight: '25%' }}>
          <input type="hidden" id="itemId" value="${item.id}" />

          {/* 기존의 HTML 코드들을 React 컴포넌트로 대체 */}
          {/* ... */}

          <div className="text-right mgt-50">
            <h5>결제 금액</h5>
            <h3 name="totalPrice" id="totalPrice" className="font-weight-bold">
              {totalPrice}원
            </h3>
          </div>
          <div className="text-right">
            {item.itemSellStatus === 'SELL' ? (
              <>
                <button
                  type="button"
                  className="btn btn-light border border-primary btn-lg"
                  onClick={addCart}
                >
                  장바구니 담기
                </button>
                <button
                  type="button"
                  className="btn btn-primary btn-lg"
                  onClick={order}
                >
                  주문하기
                </button>
              </>
            ) : (
              <button type="button" className="btn btn-danger btn-lg">
                품절
              </button>
            )}
          </div>
        </div>
      </Container >
    </>
  );
};

export default ItemDetail;