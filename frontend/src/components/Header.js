
import Logo from "./Logo";
import Link from "./Link";
import Title from "./Title";

import config from "../imgs/icons/config.png"

import "../styles/components/header.css";

function Header({}){
    const months = [ "Janeiro", "Fevereiro", "Março", "Abril", 
                    "Maio", "Junho", "Julho", "Agosto",
                    "Setembro", "Outubro", "Novembro", "Dezembro"
                ];

    const currentDate = new Date();
    const currentMonth = currentDate.getMonth();

    return (
        <div class="header">
            <Logo
                width="80px"
                height="80px"
                border="7px solid white"
            >

            </Logo>

            <div class="month">
                <button class="prev_month">
                    -
                </button>
                <span>
                    <Title
                        color="gray"
                    >
                        {months[currentMonth]}
                    </Title>
                </span>
                <button class="next_month">
                    +
                </button>
            </div>

            <div class="config">
                <Link
                    href="#"
                >
                    <img src={config} alt="Configs" />
                </Link>
            </div>
        </div>
    );
}

export default Header;