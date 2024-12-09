
import Link from "./Link";
import Subtitle from "./Subtitle";

import action from "../imgs/icons/action.png";
import action_selected from "../imgs/icons/action_selected.png";
import home from "../imgs/icons/home.png";
import home_selected from "../imgs/icons/home_selected.png";
import plan from "../imgs/icons/plan.png";
import plan_selected from "../imgs/icons/plan_selected.png";
import transaction from "../imgs/icons/transaction.png";
import transaction_selected from "../imgs/icons/transaction_selected.png";

import "../styles/components/section.css";

function Section({is_selected, icon, onClick}){
    const icons = {
        action: { default: action, selected: action_selected, label: "Ações" },
        home: { default: home, selected: home_selected, label: "Home" },
        plan: { default: plan, selected: plan_selected, label: "Planos" },
        transaction: { default: transaction, selected: transaction_selected, label: "Transações" },
    };
    
    const img = is_selected ? icons[icon].selected : icons[icon].default;
    const label = icons[icon].label;

    return (
        <div className={`section ${is_selected ? "selected" : ""}`}
            onClick={onClick}
        >
            <Link href={`#${label}`}
                color={is_selected ? "var(--c1)" : "#545454"}
                decoration="none"
            >
                <img src={img} alt={label} />
                <Subtitle 
                    color={is_selected ? "var(--c1)" : "#545454"} 
                    fontsize="18px"
                >
                    {label}
                </Subtitle>
            </Link>
        </div>
    );
}

export default Section;