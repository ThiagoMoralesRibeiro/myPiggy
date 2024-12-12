// import React, { useState } from "react";

// import Link from "./Link";

import signout from "../imgs/icons/signout.png";
import close from "../imgs/icons/close.png";

import "../styles/components/navbar.css";

const handleSubmit = async (e) => {
  e.preventDefault();

  // const newErrors = {};

  try {
    const response = await fetch("http://localhost:8080/logout", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    });
    if(response.ok) {
      window.location.href = 'http://localhost:3000/login';
    }else{
      alert("Não foi possível efetuar o LOGOUT");
    }
  }catch(error){
    console.error("Erro ao fazer login", error);
  }
}

function Navbar(){

    const toggleNavbar = () => {
        var navbar = document.querySelector(".navbar");
        navbar.classList.add("hidden");
        document.body.style.overflow = "auto";
    }

    return (
        <div className="navbar hidden">
            <button onClick={toggleNavbar} class="close">
                <img src={close} alt="Close Navbar"/>
            </button>

            <div class="content">
                <button
                    class="signout"
                    onClick={handleSubmit}
                >
                    <img src={signout} alt="Signout"/>
                    Sign Out
                </button>
            </div>
        </div>
    );

}

export default Navbar;

