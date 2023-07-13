import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import JoinForm from './pages/JoinForm';
import LoginForm from './pages/LoginForm';
import ItemSaveForm from './pages/item/ItemSaveForm';
import ItemUpdateForm from './pages/item/ItemUpdateForm';
import Test from './pages/Test';
import TestFile from './pages/test/TestFile';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/join" element={<JoinForm />} />
        <Route path="/test" element={<Test/>} />
        <Route path="/ItemSaveForm" element={<ItemSaveForm/>} />
        <Route path="/ItemUpdateForm/:id" element={<ItemUpdateForm/>} />
        <Route path="/testFile" element={<TestFile/>} />
      </Routes>
    </>
  );
}

export default App;
