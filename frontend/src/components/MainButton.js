
import React, { useState } from "react";
import Link from "./Link";

import plus from "../imgs/icons/plus.png";
import entrada from "../imgs/icons/arrow_entrada.png";
import saida from "../imgs/icons/arrow_saida.png";
import transferencia from "../imgs/icons/arrow_transferencia.png";

import "../styles/components/main_button.css";


    
function MainButton(){    
    const [showActions, setShowActions] = useState(false);
    
    const toggleActions = () => {
        setShowActions(!showActions);
        var filter = document.querySelector(".filter");
        filter.classList.toggle("blurred")
    };
    

    return (
        <button class="main_button_container">
            <button class={`main_button ${showActions ? "open" : "closed"}`} onClick={toggleActions}>
                <img src={plus} alt=""/>
            </button>

            <div class={`action_buttons ${showActions ? "visible" : "hidden"}`}>
                <Link
                    classe="entrada"
                    href="/entrada"
                >
                    <img src={entrada} alt="Entradas"/>
                </Link>
                <Link
                    classe="saida"
                    href="/saida"
                >
                    <img src={saida} alt="Saídas"/>
                </Link>
                <Link
                    classe="transferencia"
                    href="/transferencia"
                >
                    <img src={transferencia} alt="Transferências"/>
                </Link>
            </div>
        </button>
    );

}

export default MainButton;
