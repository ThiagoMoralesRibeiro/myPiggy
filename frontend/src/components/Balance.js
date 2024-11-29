
import React, { useState } from "react";

import Title from "./Title";

import show from "../imgs/icons/show.png";
import hide from "../imgs/icons/hide.png";

import "../styles/components/balance.css";

function Balance({balance}){
    const balanceValue = {balance}.balance;

    const [showActions, setShowActions] = useState(false);

    const toggleBalance = (e) => {
        setShowActions(!showActions);
    };
   
    return (
        <div className="balance">
            <Title
                color="gray"
                fontsize="40px"
            >
                MEU SALDO
            </Title>
            
            <div class={`value ${showActions ? "hidden" : "visible"}`}>
                <Title
                    color="black"
                    fontsize="60px"
                >
                    R$ 2232{balanceValue}
                </Title>

                <span class={`${showActions ? "visible" : "hidden"}`}>
                    R$ ---
                </span>

                <button onClick={toggleBalance}>
                    <img src={showActions ? show : hide} alt={showActions ? "Show Balance" : "Hide Balance"}/>
                </button>
            </div>
        </div>
    );
}

export default Balance;