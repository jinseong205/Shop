import { Routes, Route } from 'react-router-dom';
import Home from "./pages/Home";
import JoinForm from "./pages/JoinForm";
import LoginForm from "./pages/LoginForm";
import Test from './pages/Test';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/join" element={<JoinForm />} />
        <Route path="/test" element={<Test />} />
      </Routes>
    </>
  );
}

export default App;
