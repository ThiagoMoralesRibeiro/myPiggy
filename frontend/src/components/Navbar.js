import React, { useState } from "react";

import Link from "./Link";

import signout from "../imgs/icons/signout.png";
import close from "../imgs/icons/close.png";

import "../styles/components/navbar.css";

function Navbar(){

    const toggleNavbar = () => {
        var navbar = document.querySelector(".navbar");
        navbar.classList.add("hidden");
        document.body.style.overflow = "auto";
    }

    return (
        <div className="navbar hidden">
            <button onClick={toggleNavbar}>
                <img src={close} alt="Close Navbar"/>
            </button>

            <div class="content">
                <Link
                    href="#signout"
                    color="#d63333"
                    fontsize="22px"
                    decoration="none"
                >
                    <img src={signout} alt="Signout"/>
                    Sign Out
                </Link>
            </div>
        </div>
    );

}

export default Navbar;

