
import Title from "./Title";
import Subtitle from "./Subtitle";

import wallet from "../imgs/icons/wallet_black.png";
import bank from "../imgs/icons/bank_black.png";

import "../styles/components/account.css";

function Account({type, name, balance}){
    const imgSrc = (type === "wallet" ? wallet : bank);
    
    return (
        <div class="account">
            <img src={imgSrc} alt={type === "wallet" ? "Carteira" : "Banco"} />
            
            <div class="account_info">
                <Title
                    color="black"
                    fontsize="28px"
                >
                    {name}
                </Title>
    
                <Subtitle
                    color="green"
                    fontsize="36px"
                >
                    R$ {balance}
                </Subtitle>
            </div>
        </div>
    );
}

export default Account;
