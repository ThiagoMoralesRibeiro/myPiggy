import React, { useState } from "react";

import Input from "./components/Input.js";
import SignIn from "./components/SignIn.js";
import Subtitle from "./components/Subtitle.js"
import Link from "./components/Link.js";

import "./styles/pages/login_register.css";
import "./styles/pages/index.css";

function Register() {
  const [formData, setFormData] = useState({
    signup_name: "",
    signup_email: "",
    signup_cpf: "",
    signup_cep: "",
    signup_phone: "",
    signup_password: "",
    signup_password_confirm: ""
  });

  const [errors, setErrors] = useState({});

  const validateEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  const validateCPF = (cpf) => /^\d{11}$/.test(cpf);
  const validateCEP = (cep) => /^\d{8}$/.test(cep);
  const validatePhone = (phone) => /^\d{10,11}$/.test(phone);
  const validatePassword = (password) => password.length >= 8;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newErrors = {};

    if (!validateEmail(formData.signup_email)) { newErrors.email = "E-mail inválido." };
    if (!validateCPF(formData.signup_cpf)) { newErrors.cpf = "CPF inválido (11 dígitos)." };
    if (!validateCEP(formData.signup_cep)) {newErrors.cep = "CEP inválido (8 dígitos)."};
    if (!validatePhone(formData.signup_phone)) { newErrors.phone = "Telefone inválido." };
    if (!validatePassword(formData.signup_password)) { newErrors.password = "Senha deve ter pelo menos 8 caracteres."; }
    if (formData.signup_password !== formData.signup_password_confirm) { newErrors.password_confirm = "Senhas não coincidem."; }

    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      const requestData = {
        name: formData.signup_name,
        email: formData.signup_email,
        password: formData.signup_password,
        birthDate: {
          year: new Date().getFullYear(),
          month: 5,
          day: 21,
        },
        phoneNumber: formData.signup_phone,
        cpf: formData.signup_cpf,
        cep: formData.signup_cep,
      };

      try {
        const response = await fetch("http://localhost:8080/user", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(requestData),
        });

        if (response.ok) {
          alert("Cadastro realizado com sucesso!");
          window.location.href = 'http://localhost:3000/login';
        } else {
          const errorData = await response.json();
          alert("Erro: " + errorData.message);
        }
      } catch (error) {
        console.error("Erro ao cadastrar:", error);
        alert("Erro ao tentar cadastrar. Tente novamente mais tarde.");
      }
    }
  }; return (
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

      <section class="register_section">
        <form method="get" action="" onSubmit={handleSubmit}>
          <Input
            label="Seu nome"
            type="text"
            name="signup_name"
            maxlength="60"
            required="true"
            onChange={handleChange}
          >
          </Input>

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
            label="Seu CEP (apenas dígitos)"
            type="text"
            name="signup_cep"
            maxlength="8"
            required="true"
            onChange={handleChange}
          >
          </Input>
          {errors.cep && <span className="error">{errors.cep}</span>}
         
          <Input
            label="Seu número de celular (com DDD)"
            type="text"
            name="signup_phone"
            maxlength="11"
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
            <SignIn>
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