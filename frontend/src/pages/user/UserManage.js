import React, { useEffect, useState } from 'react';
import Header from '../../components/Header';
import { Container, Row, Col } from 'react-bootstrap';

const UserManage = () => {
  const [users, setUsers] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);

  useEffect(() => {
    getUsers();
  }, [currentPage]);

  // 사용자 목록을 서버에서 가져오는 함수
  const getUsers = () => {
      fetch(`http://localhost:8080/api/users?page=${currentPage}`, {
        method: "GET",
        headers: {
          'Authorization': localStorage.getItem("token"),
          "Content-Type": "application/json; charset=utf-8"
        }
      })
        .then(res => res.json())
        .then(data => {
          console.log(data);
          if (data.content != null) {
            setUsers(data.content);
          } else {
            alert(data.message);
          }
        })
        .catch((error) => {
          alert('사용자 역할 변경 도중 오류 발생 : ' + error);
        });
  };

  const updateUserRole = (userId, newRole) => {

    // 로컬 스토리지에서 토큰을 가져옵니다.
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('로컬 스토리지에서 토큰을 찾을 수 없습니다.');
      return;
    }


    const userRoles = 'ROLE_' + newRole;
    const user = {
      id: userId,
      roles: userRoles,
    }

    fetch("http://localhost:8080/api/user/roles", {
      method: "PATCH",
      headers: {
        'Authorization': localStorage.getItem("token"),
        "Content-Type": "application/json; charset=utf-8"
      },
      body: JSON.stringify(user)
    })
      .then((res) => {
        if (res.status === 200) {
          alert("권한변경이 완료되었습니다.");
          getUsers();
        } else {
          return res.json();
        }
      })
      .then(data => {
        if (data != null) {
          alert(data.message);
        }
      })
      .catch((error) => {
        alert('사용자 역할 변경 도중 오류 발생 : ' + error);
      });

  };

  // 페이지 변경 시 호출되는 함수
  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  return (
    <>
      <Header />
      <br />
      <Container>
        <div>
          <h2>사용자 관리</h2>
          <br />
          <Row className="justify-content-center">
            <Col xs={10}>
              <table className="table table-bordered text-center">
                <thead>
                  <tr>
                    <th>이름</th>
                    <th>사용자명</th>
                    <th>이메일</th>
                    <th>역할</th>
                    <th>권한 변경</th>
                  </tr>
                </thead>
                <tbody>
                  {users.map((user) => (
                    <tr key={user.id}>
                      <td>{user.name}</td>
                      <td>{user.username}</td>
                      <td>{user.email}</td>
                      <td>{user.roles}</td>
                      <td>
                        {(
                          <button onClick={() => updateUserRole(user.id, 'USER')}>
                            일반
                          </button>
                        )}
                        {(
                          <button onClick={() => updateUserRole(user.id, 'MANAGER')}>
                            매니저
                          </button>
                        )}
                        {(
                          <button onClick={() => updateUserRole(user.id, 'ADMIN')}>
                            관리자
                          </button>
                        )}

                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </Col>
          </Row>
          <div>
            <ul>
              {Array.from({ length: users.totalPages }).map((_, index) => (
                <li key={index}>
                  <button onClick={() => handlePageChange(index)}>
                    {index + 1}
                  </button>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </Container>
    </>
  );
};

export default UserManage;
