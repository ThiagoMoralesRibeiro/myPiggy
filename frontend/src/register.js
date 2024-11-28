import React, {useState} from "react";

import Logo from "./components/Logo.js"
import Input from "./components/Input.js";
import SignIn from "./components/SignIn.js";
import Subtitle from "./components/Subtitle.js"
import Link from "./components/Link.js";

import "./styles/pages/login_register.css";
import "./styles/pages/index.css";

function Register(){
    return (
        <div className="register">
            <div class="back">
                <Link
                    href="/"
                    color="white"
                    fontsize="22px"
                    decoration="none"
                >
                ← Voltar
            </Link>
            </div>

            <Logo width="150px" height="150px"></Logo>
            
            <section class="register_section">
                <form method="get" action="">
                    <Input
                        label="Seu melhor e-mail"
                        type="email"
                        name="signup_email"
                        maxlength="60"
                        required="true"
                    >
                    </Input>

                    <Input
                        label="Seu CPF"
                        type="text"
                        name="signup_cpf"
                        maxlength=""
                        required="true"
                    >
                    </Input>

                    <Input
                        label="Seu número de celular"
                        type="text"
                        name="signup_phone"
                        maxlength=""
                        required="true"
                    >
                    </Input>

                    <Input
                        label="Sua melhor senha"
                        type="password"
                        name="signup_password"
                        maxlength="60"
                        required="true"
                    >
                    </Input>

                    <Input
                        label="Confirme a senha"
                        type="password"
                        name="signup_password_confirm"
                        maxlength="60"
                        required="true"
                    >
                    </Input>

                    <button>
                        <SignIn
                        href="#"
                        >
                            CADASTRAR
                        </SignIn>
                    </button>

                </form>
            </section>

            <section class="links_section">
                <Subtitle>Já possui uma conta?</Subtitle>
                <Link
                    href="/login"
                    color="white"
                    fontsize="18px" 
                >
                    Conecte-se
                </Link>
            </section>
        </div>
    );
}

export default Register;