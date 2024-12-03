import { useState, useEffect } from "react";
import { useLocation, Navigate } from "react-router-dom";
import Header from "./components/Header.js";
import Balance from "./components/Balance.js";
import Transference from "./components/Transference.js";
import Account from "./components/Account.js";
import MainButton from "./components/MainButton.js";
import RequireAuth from "./components/RequireAuth.js";
import "./styles/pages/home.css";
import Title from "./components/Title.js";

function Home() {
  const [totalEntradas, setTotalEntradas] = useState(0);
  const [totalSaidas, setTotalSaidas] = useState(0);
  const [accountId, setAccountId] = useState(null);
 
  useEffect(() => {
    const checkSession = async () => {
      try {
        const response = await fetch("http://localhost:8080/session-info", {
          method: "GET",
          credentials: "include",
        });

        if (response.ok) {
          const sessionData = await response.json();
          if (sessionData.accountId) {
            setAccountId(sessionData.accountId);
          } else {
            console.log("AccountId não encontrado na sessão.");
          }
        } else {
          console.error("Erro ao obter as informações da sessão.");
        }
      } catch (error) {
        console.error("Erro ao verificar a sessão", error);
      }
    };

    checkSession();
  }, []);

  useEffect(() => {
    if (accountId !== null) {
      fetch(`http://localhost:8080/transaction-summary/${accountId}`)
        .then(response => response.json())
        .then(data => {
          setTotalEntradas(data.totalCredit || 0);
          setTotalSaidas(data.totalDebit || 0);
        })
        .catch(error => {
          console.error("Erro ao buscar transferências:", error);
        });
    }
  }, [accountId]); 
  return (
    <RequireAuth>
      <div className="home">
        <Header />
        <Balance balance="" />

        <section className="section_transfer">
          <Transference type="entradas" title="Entradas" value={totalEntradas} />
          <Transference type="saidas" title="Saídas" value={totalSaidas} />
        </section>

        <hr />

        <section className="section_accounts">
          <Account type="wallet" name="Carteira" balance="121" />
          <Account type="bank" name="Conta Salário" balance="123" />
          <Account type="bank" name="Conta Benefício" balance="123" />

          <hr />

          <div className="total">
            <Title color="black" fontsize="">
              TOTAL
            </Title>
            <Title color="green" fontsize="">
              R$ {totalEntradas - totalSaidas}
            </Title>
          </div>
        </section>

        <MainButton />
      </div>
    </RequireAuth>
  );
}

export default Home;

