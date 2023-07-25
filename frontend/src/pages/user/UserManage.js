import React, { useEffect, useState } from 'react';

const UserManage = () => {
  const [users, setUsers] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const itemsPerPage = 10; // 필요에 따라 변경하세요 (한 페이지당 표시할 사용자 수)

  useEffect(() => {
    fetchUsers();
  }, [currentPage]);

  // 사용자 목록을 서버에서 가져오는 함수
  const fetchUsers = async () => {
    try {
      const response = await fetch(`/api/users?page=${currentPage}&size=${itemsPerPage}`);
      const data = await response.json();
      setUsers(data.content);
    } catch (error) {
      console.error('사용자 정보를 가져오는 도중 오류 발생:', error);
    }
  };

  // 사용자 역할을 변경하는 함수
  const handleRoleChange = async (userId, newRole) => {
    try {
      // 로컬 스토리지에서 토큰을 가져옵니다.
      const token = localStorage.getItem('token');
      if (!token) {
        console.error('로컬 스토리지에서 토큰을 찾을 수 없습니다.');
        return;
      }

      // 헤더를 설정합니다. (인증 토큰과 콘텐트 타입 설정)
      const headers = new Headers({
        Authorization: `Bearer ${token}`,
        'Content-Type': 'application/json; charset=utf-8',
      });

      // 변경할 역할 정보를 JSON 문자열로 변환합니다.
      const body = JSON.stringify({ newRole });

      // POST 요청을 통해 사용자 역할을 변경합니다.
      const response = await fetch(`/api/users/${userId}/role`, {
        method: 'POST',
        headers,
        body,
      });

      // 성공 메시지를 보여주거나 사용자 목록을 업데이트할 수 있습니다.
    } catch (error) {
      console.error('사용자 역할 변경 도중 오류 발생:', error);
    }
  };

  // 페이지 변경 시 호출되는 함수
  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  return (
    <div>
      <h2>사용자 관리</h2>
      <table>
        <thead>
          <tr>
            <th>이름</th>
            <th>사용자명</th>
            <th>이메일</th>
            <th>역할</th>
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
                {user.roles !== 'ADMIN' && (
                  <button onClick={() => handleRoleChange(user.id, 'ADMIN')}>
                    관리자로 변경
                  </button>
                )}
                {user.roles !== 'MANAGER' && (
                  <button onClick={() => handleRoleChange(user.id, 'MANAGER')}>
                    매니저로 변경
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
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
  );
};

export default UserManage;
