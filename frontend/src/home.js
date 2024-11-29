
import Header from "./components/Header.js";
import Balance from "./components/Balance.js";
import Transference from "./components/Transference.js";
import Account from "./components/Account.js"
import MainButton from "./components/MainButton.js";

import "./styles/pages/home.css";
import Title from "./components/Title.js";

function Home(){
    return (
        <div className="home">
           <Header></Header>
            <Balance
                balance=""
            >
            </Balance>

            <section class="section_transfer">
                <Transference
                    type="entradas"
                    title="Entradas"
                    value=""
                >
                </Transference>
                

                <Transference
                    type="saidas"
                    title="Saídas"
                    value=""
                >

                </Transference>
            </section>
            <hr/>

            <section class="section_accounts">
                <Account
                    type="wallet"
                    name="Carteira"
                    balance="121"
                >
                </Account>
                <Account
                    type="bank"
                    name="Conta Salário"
                    balance="123"
                >
                </Account>
                <Account
                    type="bank"
                    name="Conta Benefício"
                    balance="123"
                >
                </Account>

                <hr/>

                <div class="total">
                    <Title
                        color="black"
                        fontsize=""
                    >
                        TOTAL
                    </Title>

                    <Title
                        color="green"
                        fontsize=""
                    >
                       R$ 123
                    </Title>
                </div>
            </section>
      
            <MainButton></MainButton>
        </div>
    );
}

export default Home;