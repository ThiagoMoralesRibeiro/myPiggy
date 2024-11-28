
import Subtitle from "./Subtitle";

import arrow_circ from "../imgs/icons/arrow_circular.png"

import "../styles/components/transference.css";

function Transference({type, title, value}){

    const typeClass = `transference ${type}`;

    return (
        <div class={typeClass}>
            <img src={arrow_circ} alt="Arrow Circular"/>

            <div class="text">
                <Subtitle
                    fontsize="22px"
                >
                    {title}
                </Subtitle>

                <span>
                    R$ {value}
                </span>
            </div>
        </div>
    );
}

export default Transference;