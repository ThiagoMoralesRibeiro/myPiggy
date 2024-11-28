
import Logo from "./components/Logo.js"
import Title from "./components/Title.js";
import Subtitle from "./components/Subtitle.js";
import GoogleSignIn from "./components/GoogleSignIn.js";
import SignIn from "./components/SignIn.js";
import Link from "./components/Link.js";

import "./styles/pages/login_option.css";
import "./styles/pages/index.css";

function LoginOption(){
    return (
        <div className="LoginOption">
            <Logo></Logo>
            <section class="text_section">
                <Title>Bem vindo(a) de volta!</Title>
                <Subtitle>O piggy sentiu sua falta...</Subtitle>
            </section>
            <section class="signin_section">
                <GoogleSignIn
                    href="#"
                    target=""
                >
                    Conectar com Google
                </GoogleSignIn>
                <div class="or">
                    ou
                </div>
                <SignIn
                    href="/login"
                    target=""
                >
                    LOGIN
                </SignIn>
            </section>
            <section class="register_section">
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
            </section>
        </div>
    );
}

export default LoginOption;