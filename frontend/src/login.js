import React, {useState} from "react";

import Logo from "./components/Logo.js"
import Input from "./components/Input.js";
import SignIn from "./components/SignIn.js";
import Link from "./components/Link.js";
import Subtitle from "./components/Subtitle.js";

import "./styles/pages/login_register.css";
import "./styles/pages/index.css";

function Login(){
    const [email, setEmail] = useState("");
    const [errors, setError] = useState({ email: ""});

    // Função de validação
    const validateInputs = () => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        const emailError = emailRegex.test(email) ? "" : "E-mail inválido!";

        setError({email: emailError});

        return emailError;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateInputs()) {
            alert("E-mail e/ou senha válidos!");
        } else {
            //
        }
    };
   
    return (
        <div className="login">
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

            <Logo></Logo>
            
            <section class="signin_section">
                <form method="get" action="#" onSubmit={handleSubmit}>
                    <Input
                        label="Seu e-mail"
                        type="email"
                        value={email}
                        name="signin_email"
                        maxlength="60"
                        required="true"
                        onChange={(e) => setEmail(e.target.value)}
                    >
                    </Input>
                    {errors.email && <p style={{ color: "red" }}>{errors.email}</p>}

                    <Input
                        label="Sua senha"
                        type="password"
                        name="signin_password"
                        maxlength="60"
                        required="true"
                    >
                    </Input>
                    <button>
                        <SignIn
                        href="#"
                        >
                            LOGIN
                        </SignIn>
                    </button>
                </form>
            </section>

            <section class="links_section">
                <Link
                    href="#"
                    color="white"
                    fontsize="18px" 
                >
                    Esqueceu sua senha?
                </Link>
            </section>

            <div class="newhere">
                <Subtitle
                fontsize="14px">
                    É novo por aqui?
                </Subtitle>
                <Link
                    href="/register"
                    fontsize="14px"
                    color="white"
                >
                    Cadastre-se!
                </Link>
            </div>
        </div>
    );
}

export default Login;