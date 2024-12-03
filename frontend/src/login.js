import React, { useState } from "react";

import Logo from "./components/Logo.js"
import Input from "./components/Input.js";
import SignIn from "./components/SignIn.js";
import Link from "./components/Link.js";
import Subtitle from "./components/Subtitle.js";

import "./styles/pages/login_register.css";
import "./styles/pages/index.css";

function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({});

  const validateEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  const validatePassword = (password) => password.length >= 8;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const newErrors = {};

    if (!validateEmail(formData.email)) { newErrors.email = "E-mail inválido." };
    if (!validatePassword(formData.password)) { newErrors.password = "Senha deve ter pelo menos 8 caracteres."; }

    setErrors(newErrors);

    if (Object.keys(newErrors).length !== 0) {
      alert("Corrija os campos");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
        body: new URLSearchParams(formData).toString(),
        credentials: "include",
      });
      if (response.ok) {
        window.location.href = 'http://localhost:3000/home';
      } else {
        alert("Erro: Credenciais invalidas");
      }
    } catch (error) {
      console.error("Erro ao fazer login", error);
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
        <form method="post" onSubmit={handleSubmit}>
          <Input
            label="Seu e-mail"
            type="email"
            name="email"
            maxlength="60"
            required="true"
            onChange={handleChange}
          >
          </Input>
          {errors.email && <span className="error">{errors.email}</span>}

          <Input
            label="Sua senha"
            type="password"
            name="password"
            maxlength="60"
            required="true"
            onChange={handleChange}
          >
          </Input>
          {errors.password && <span className="error">{errors.password}</span>}

          <button type="submit">
            <SignIn>
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
