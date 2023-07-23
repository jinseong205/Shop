// "ItemsTable.js" 파일을 생성합니다.
import React, { useEffect, useState } from 'react';

const ItemManage = () => {
  const [items, setItems] = useState([]);

  // 서버에서 데이터를 가져오는 AJAX 요청을 실행합니다.
  useEffect(() => {
    fetch('/api/admin/items') // 실제 API 엔드포인트로 대체해야 합니다.
      .then(response => response.json())
      .then(data => setItems(data));
  }, []);

  return (
    <>
      <Header />
      <Container>
        <div>
          <table className="table">
            <thead>
              <tr>
                <td>상품아이디</td>
                <td>상품명</td>
                <td>상태</td>
                <td>등록자</td>
                <td>등록일</td>
              </tr>
            </thead>
            <tbody>
              {items.map(item => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>
                    <a href={`/admin/item/${item.id}`}>{item.itemNm}</a>
                  </td>
                  <td>{item.itemSellStatus === 'SELL' ? '판매중' : '품절'}</td>
                  <td>{item.createdBy}</td>
                  <td>{item.regTime}</td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="form-inline justify-content-center" th: object="${itemSearchDto}">
            <select name="searchDateType" class="form-control" style="width:auto;">
              <option value="all">전체기간</option>
              <option value="1d">1일</option>
              <option value="1w">1주</option>
              <option value="1m">1개월</option>
              <option value="6m">6개월</option>
            </select>
            <select name="searchSellStatus" class="form-control" style="width:auto;">
              <option value="">판매상태(전체)</option>
              <option value="SELL">판매</option>
              <option value="SOLD_OUT">품절</option>
            </select>
            <select name="searchBy" class="form-control" style="width:auto;">
              <option value="itemNm">상품명</option>
              <option value="createdBy">등록자</option>
            </select>
            <input type="text" name="searchQuery" class="form-control" placeholder="검색어를 입력해주세요" />
            <button id="searchBtn" type="submit" class="btn btn-primary">검색</button>
          </div>
        </div>
      </Container>
    </> 
  );
};

export default ItemManage; 