import React from "react";
// import "react-router-dom";
import { Route, Routes, BrowserRouter } from "react-router";
import RequireAuth from "./components/RequireAuth";
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
          <Route path="/home" element={<RequireAuth><Home/></RequireAuth>}/>
          <Route path="/entrada" element={<RequireAuth><Entrada/></RequireAuth>}/>
          <Route path="/saida" element={<RequireAuth><Saida/></RequireAuth>}/>
          <Route path="/transferencia" element={<RequireAuth><Transferencia/></RequireAuth>}/>

        </Routes>
      </BrowserRouter>
    </div>

  );
}

export default App;
