import React, { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";

const TestFile = () => {
  const navigate = useNavigate()
  const [file, setFile] = useState(null);
  const [dto, setDto] = useState({ name: '', age: '' });

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleInputChange = (e) => {
    setDto({ ...dto, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    const formData = new FormData();
    formData.append('file', file);

    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));
    
  
    fetch('http://localhost:8080/api/upload', {
      method: 'POST',
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

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name:</label>
        <input type="text" name="name" value={dto.name} onChange={handleInputChange} />
      </div>
      <div>
        <label>Age:</label>
        <input type="text" name="age" value={dto.age} onChange={handleInputChange} />
      </div>
      <div>
        <label>Image:</label>
        <input type="file" onChange={handleFileChange} />
      </div>
      <button type="submit">Upload</button>
    </form>
  );
};

export default TestFile;