import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import JoinForm from './pages/JoinForm';
import LoginForm from './pages/LoginForm';
import ItemSaveForm from './pages/item/ItemSaveForm';
import ItemUpdateForm from './pages/item/ItemUpdateForm';
import ItemMain from './pages/item/ItemMain';
import ItemDetail from './pages/item/ItemDetail';
import ItemManage from './pages/item/ItemManage';




function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<ItemMain />} />
        <Route path="/login" element={<LoginForm />} />
        <Route path="/join" element={<JoinForm />} />

        <Route path="/itemMain" element={<ItemMain />} />
        <Route path="/itemManage" element={<ItemManage/>} />
        <Route path="/itemSaveForm" element={<ItemSaveForm/>} />
        <Route path="/itemUpdateForm/:id" element={<ItemUpdateForm/>} />
        <Route path="/itemDetail/:id" element={<ItemDetail/>} />
      </Routes>
    </>
  );
}

export default App;
