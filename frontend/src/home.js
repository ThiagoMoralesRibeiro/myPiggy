
import Header from "./components/Header.js";
import Navbar from "./components/Navbar.js";
import Title from "./components/Title.js";
import Balance from "./components/Balance.js";
import Transference from "./components/Transference.js";
import Account from "./components/Account.js"
import Footer from "./components/Footer.js";


import "./styles/pages/home.css";

function Home(){
    return (
        <div className="home">
           <main>
                <div class="filter"></div>
                <Header></Header>
                <Navbar></Navbar>
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
                {/* <MainButton></MainButton> */}
                <Footer></Footer>
            </main>
        </div>
    );
}

export default Home;