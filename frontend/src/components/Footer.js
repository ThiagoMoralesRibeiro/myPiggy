
import React, { useState } from "react";

import Section from "./Section";

import "../styles/components/footer.css";
import MainButton from "./MainButton";

function Footer(){
    const [selectedIcon, setSelectedIcon] = useState("home"); 

    const handleClick = (icon) => {
        setSelectedIcon(icon); 
    };

    return (
        <footer className="footer">
            <div class="left">
                <Section
                    icon="home"
                    is_selected={selectedIcon === "home"}
                    onClick={() => handleClick("home")}
                />     
                <Section
                    icon="transaction"
                    is_selected={selectedIcon === "transaction"}
                    onClick={() => handleClick("transaction")}
                />
            </div>
            <MainButton></MainButton>
            <div class="right">
                <Section
                    icon="plan"
                    is_selected={selectedIcon === "plan"}
                    onClick={() => handleClick("plan")}
                />
                <Section
                    icon="action"
                    is_selected={selectedIcon === "action"}
                    onClick={() => handleClick("action")}
                />
            </div>
        </footer>
    );
}

export default Footer;