import React, {useState} from "react";

import Logo from "./components/Logo.js"
import Input from "./components/Input.js";
import SignIn from "./components/SignIn.js";
import Link from "./components/Link.js";
import Subtitle from "./components/Subtitle.js";

import "./styles/pages/login_register.css";
import "./styles/pages/index.css";

function Login(){
        const [formData, setFormData] = useState({
            signin_email: "",
            signin_password: "",
        });

        const [errors, setErrors] = useState({});

        const validateEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
        const validatePassword = (password) => password.length >= 8;

        const handleChange = (e) => {
            const { name, value } = e.target;
            setFormData({ ...formData, [name]: value });
        };

        const handleSubmit = (e) => {
            e.preventDefault();
            const newErrors = {};

            if (!validateEmail(formData.signin_email)) {newErrors.email = "E-mail inválido."};
            if (!validatePassword(formData.signin_password)){newErrors.password = "Senha deve ter pelo menos 8 caracteres.";}

            setErrors(newErrors);

            if (Object.keys(newErrors).length !== 0) {
                alert("Corrija os campos!");
            } else {
                alert("Login realizado com sucesso!");
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
                        name="signin_email"
                        maxlength="60"
                        required="true"
                        onChange={handleChange}
                    >
                    </Input>
                    {errors.email && <span className="error">{errors.email}</span>}

                    <Input
                        label="Sua senha"
                        type="password"
                        name="signin_password"
                        maxlength="60"
                        required="true"
                        onChange={handleChange}
                    >
                    </Input>
                    {errors.password && <span className="error">{errors.password}</span>}

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