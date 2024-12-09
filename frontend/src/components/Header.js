import React, { useState } from "react";

import Logo from "./Logo";
import Link from "./Link";
import Title from "./Title";
import Navbar from "./Navbar";

import config from "../imgs/icons/config.png"

import "../styles/components/header.css";

function Header(){
    const months = [ "Janeiro", "Fevereiro", "Março", "Abril", 
                    "Maio", "Junho", "Julho", "Agosto",
                    "Setembro", "Outubro", "Novembro", "Dezembro"
                ];

    const currentDate = new Date();
    const currentMonth = currentDate.getMonth();

    const toggleNavbar = () => {
        var navbar = document.querySelector(".navbar");
        navbar.classList.remove("hidden");
        document.body.style.overflow = "hidden";
        window.scrollTo({ top: 0, behavior: "smooth" })
    };

    return (
        <div className="header">
            <Logo
                width="80px"
                height="80px"
                border="7px solid white"
            >

            </Logo>

            <div class="month">
                <button class="prev_month">
                    -
                </button>
                <span>
                    <Title
                        color="gray"
                    >
                        {months[currentMonth]}
                    </Title>
                </span>
                <button class="next_month">
                    +
                </button>
            </div>

            <div class="config"
                onClick={toggleNavbar}
            >
                <Link
                    href="#"
                >
                    <img src={config} alt="Configs" />
                </Link>
            </div>
        </div>
    );
}

export default Header;