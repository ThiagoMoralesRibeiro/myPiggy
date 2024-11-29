
import Subtitle from "./components/Subtitle.js";
import Link from "./components/Link.js";
import Input from "./components/Input.js";

import "./styles/pages/moneyinput.css";

import transferencia from "./imgs/icons/transferencia.png"
import calendar from "./imgs/icons/calendar.png"
import bank from "./imgs/icons/bank.png"
import description from "./imgs/icons/description.png"


function Transferencia(){

    

    return (
        <div className="MoneyInput">
           <Link
                href="/home"
                color="white"
                fontsize="28px"
                decoration="none"
            >
                ← Nova transferência
            </Link>

            <form method="post" action="#">
                <div class="value">
                    <Subtitle 
                        color="white"
                        fontsize="18px"
                    >
                        Valor da transferencia
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
                        <img src={bank} alt="Account"/>
                        <select id="account" name="input_account1" required>
                            <option value="" disabled selected>De</option>
                            <option value="wallet">Carteira</option>
                            <option value="salary">Conta salário</option>
                        </select>
                    </label>

                    <label>
                        <img src={bank} alt="Account"/>
                        <select id="account" name="input_account2" required>
                            <option value="" disabled selected>Para</option>
                            <option value="wallet">Carteira</option>
                            <option value="salary">Conta salário</option>
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


                    <Input
                        label={<img src={calendar} alt='Calendar'/>}
                        type="date"
                        name="input_date"
                        placeholder="Data"
                        required="true"
                    >
                    </Input>
                    
                    <button type="submit">
                        <img src={transferencia} alt="Confirm" />
                    </button>
                </div>
            </form>
        </div>
    );
}

export default Transferencia;