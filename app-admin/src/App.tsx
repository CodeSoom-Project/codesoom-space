import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Reservations from "./pages/Reservations";

export default function App() {
  return (
    <Routes>
      <Route path='/' element={<Login/>}/>
      <Route path='reservations' element={<Reservations/>}/>
    </Routes>
  );
}
