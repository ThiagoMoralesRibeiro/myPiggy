
import React from "react";

import Subtitle from "./components/Subtitle.js";
import Link from "./components/Link.js";
import Input from "./components/Input.js";

import "./styles/pages/moneyinput.css";

import saida from "./imgs/icons/saida.png"
import calendar from "./imgs/icons/calendar.png"
import wallet from "./imgs/icons/wallet.png"
import folder from "./imgs/icons/folder.png"
import description from "./imgs/icons/description.png"


function Saida(){

    

    return (
        <div className="MoneyInput">
           <main>
               <Link
                    href="/home"
                    color="white"
                    fontsize="28px"
                    decoration="none"
                >
                    ← Nova saída
                </Link>
                <form method="post" action="#">
                    <div class="value">
                        <Subtitle
                            color="white"
                            fontsize="18px"
                        >
                            Valor da Saida
                        </Subtitle>
                        <Input
                            label="R$"
                            type="text"
                            name="input_money"
                            max=""
                            min=""
                            placeholder="0,00"
                            required="true"
                        >
                        </Input>
                    </div>
                        <div>
                        </div>
                    <div class="info">
                        <label>
                            <img src={folder} alt="Folder" />
                            <select id="category" name="input_category" required>
                                <option value="" disabled selected>Categoria</option>
                                <option value="salary">Salário</option>
                                <option value="gift">Presente</option>
                                <option value="investment">Investimento</option>
                                <option value="other">Outro</option>
                            </select>
                        </label>
                        <Input
                            label={<img src={description} alt='Description'/>}
                            type="text"
                            name="input_desc"
                            maxlength=""
                            placeholder="Descrição"
                        >
                        </Input>
                        <label>
                            <img src={wallet} alt="Account"/>
                            <select id="account" name="input_account" required>
                                <option value="" disabled selected>Conta</option>
                                <option value="wallet">Carteira</option>
                                <option value="salary">Conta salário</option>
                            </select>
                        </label>
                        <Input
                            label={<img src={calendar} alt='Calendar'/>}
                            type="date"
                            name="input_date"
                            placeholder="Data"
                            required="true"
                        >
                        </Input>
               
                        <button type="submit">
                            <img src={saida} alt="Confirm" />
                        </button>
                    </div>
                </form>
           </main>
        </div>
    );
}

export default Saida;