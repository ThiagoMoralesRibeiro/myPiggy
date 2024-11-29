import React from "react";
// import "react-router-dom";
import { Route, Routes, BrowserRouter } from "react-router";
import LoginOption from "./loginOption";
import Login from "./login";
import Register from "./register";
import Home from "./home";
import Entrada from "./entrada";
import Saida from "./saida";
import Transferencia from "./transferencia";

import "./styles/pages/index.css";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginOption/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/home" element={<Home/>}/>
          <Route path="/entrada" element={<Entrada/>}/>
          <Route path="/saida" element={<Saida/>}/>
          <Route path="/transferencia" element={<Transferencia/>}/>

        </Routes>
      </BrowserRouter>
    </div>

  );
}

export default App;
