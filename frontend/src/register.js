import React, {useState} from "react";

import Logo from "./components/Logo.js"
import Input from "./components/Input.js";
import SignIn from "./components/SignIn.js";
import Subtitle from "./components/Subtitle.js"
import Link from "./components/Link.js";

import "./styles/pages/login_register.css";
import "./styles/pages/index.css";

function Register(){
    const [formData, setFormData] = useState({
        signup_email: "",
        signup_cpf: "",
        signup_phone: "",
        signup_password: "",
        signup_password_confirm: "",
    });

    const [errors, setErrors] = useState({});

    const validateEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    const validateCPF = (cpf) => /^\d{11}$/.test(cpf);
    const validatePhone = (phone) => /^\d{10,11}$/.test(phone);
    const validatePassword = (password) => password.length >= 8;

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {

        e.preventDefault();
        const newErrors = {};

        if (!validateEmail(formData.signup_email)) {newErrors.email = "E-mail inválido."};
        if (!validateCPF(formData.signup_cpf)) {newErrors.cpf = "CPF inválido (11 dígitos)."};
        if (!validatePhone(formData.signup_phone)) {newErrors.phone = "Telefone inválido."};
        if (!validatePassword(formData.signup_password)){newErrors.password = "Senha deve ter pelo menos 8 caracteres.";}
        if (formData.signup_password !== formData.signup_password_confirm){newErrors.password_confirm = "Senhas não coincidem.";}

        setErrors(newErrors);

        if (Object.keys(newErrors).length === 0) {
            alert("Cadastro realizado com sucesso!");
        }
    };

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

            <Logo width="100px" height="100px" border="solid 10px white"></Logo>
            
            <section class="register_section">
                <form method="get" action="" onSubmit={handleSubmit}>
                    <Input
                        label="Seu melhor e-mail"
                        type="email"
                        name="signup_email"
                        maxlength="60"
                        required="true"
                        onChange={handleChange}
                    >
                    </Input>
                    {errors.email && <span className="error">{errors.email}</span>}

                    <Input
                        label="Seu CPF (apenas dígitos)"
                        type="text"
                        name="signup_cpf"
                        maxlength="11"
                        required="true"
                        onChange={handleChange}
                    >
                    </Input>
                    {errors.cpf && <span className="error">{errors.cpf}</span>}

                    <Input
                        label="Seu número de celular (com DDD)"
                        type="text"
                        name="signup_phone"
                        maxlength=""
                        required="true"
                        onChange={handleChange}
                    >
                    </Input>
                    {errors.phone && <span className="error">{errors.phone}</span>}

                    <Input
                        label="Sua melhor senha"
                        type="password"
                        name="signup_password"
                        maxlength="60"
                        required="true"
                        onChange={handleChange}
                    >
                    </Input>
                    {errors.password && (<span className="error">{errors.password}</span>)}

                    <Input
                        label="Confirme a senha"
                        type="password"
                        name="signup_password_confirm"
                        maxlength="60"
                        required="true"
                        onChange={handleChange}
                        >
                    </Input>
                    {errors.password_confirm && (<span className="error">{errors.password_confirm}</span>)}

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