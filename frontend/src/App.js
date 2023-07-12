import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import JoinForm from './pages/JoinForm';
import LoginForm from './pages/LoginForm';
import ItemForm from './pages/item/ItemForm';
import Test from './pages/Test';
import TestFile from './pages/test/TestFile';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/join" element={<JoinForm />} />
        <Route path="/itemForm" element={<ItemForm/>} />
        <Route path="/test" element={<Test/>} />
        <Route path="/itemForm/:id" element={<ItemForm/>} />
        <Route path="/testFile" element={<TestFile/>} />
      </Routes>
    </>
  );
}

export default App;
