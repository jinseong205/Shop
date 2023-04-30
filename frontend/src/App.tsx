import { Routes, Route } from 'react-router-dom';
import Home from "./pages/Home";
import './css/layout.css';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </>
  );
}

export default App;
