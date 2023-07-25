import React, { useState } from 'react';

function OrderItem({ order }) {
  return (
    <div className="card d-flex">
      {order.orderItemDtoList.map((orderItem) => (
        <div key={orderItem.itemId} className="d-flex mb-3">
          <div className="repImgDiv">
            <img
              src={orderItem.imgUrl}
              className="rounded repImg"
              alt={orderItem.itemNm}
            />
          </div>
          <div className="align-self-center w-75">
            <span className="fs24 font-weight-bold">{orderItem.itemNm}</span>
            <div className="fs18 font-weight-light">
              <span>{orderItem.orderPrice}원</span>
              <span>{orderItem.count}개</span>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

function OrderHist({ orders }) {
  const [currentPage, setCurrentPage] = useState(0);

  const cancelOrder = (orderId) => {
    // Your AJAX request code goes here
    // Make sure to use fetch or any AJAX library compatible with React
    // For example, you can use the `axios` library for AJAX requests
  };

  const handlePageChange = (page) => {
    setCurrentPage(page);
    // You can also update the data here if needed
  };

  return (
    <div className="content-mg">
      <h2 className="mb-4">구매 이력</h2>
      {orders.content.map((order) => (
        <div key={order.orderId}>
          <div className="d-flex mb-3 align-self-center">
            <h4>
              {order.orderDate} 주문
            </h4>
            <div className="ml-3">
              {order.orderStatus === 'ORDER' ? (
                <button
                  type="button"
                  className="btn btn-outline-secondary"
                  onClick={() => cancelOrder(order.orderId)}
                >
                  주문취소
                </button>
              ) : (
                <h4>(취소 완료)</h4>
              )}
            </div>
          </div>
          <OrderItem order={order} />
        </div>
      ))}
      <div>
        <ul className="pagination justify-content-center">
          <li className={`page-item ${currentPage === 0 ? 'disabled' : ''}`}>
            <button
              className="page-link"
              onClick={() => handlePageChange(currentPage - 1)}
              disabled={currentPage === 0}
            >
              Previous
            </button>
          </li>
          {Array.from({ length: orders.totalPages }).map((_, index) => (
            <li
              key={index}
              className={`page-item ${currentPage === index ? 'active' : ''}`}
            >
              <button
                className="page-link"
                onClick={() => handlePageChange(index)}
              >
                {index + 1}
              </button>
            </li>
          ))}
          <li
            className={`page-item ${
              currentPage + 1 >= orders.totalPages ? 'disabled' : ''
            }`}
          >
            <button
              className="page-link"
              onClick={() => handlePageChange(currentPage + 1)}
              disabled={currentPage + 1 >= orders.totalPages}
            >
              Next
            </button>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default OrderHist;
