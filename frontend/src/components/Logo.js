import logo from "../imgs/logo.png";
import "../styles/components/logo.css";

function Logo({width, height, border}){
    return (
        <div class="logo"
        style = {{
            width: width || "180px",
            height: height || "180px",
            border:  border || "solid 15px white"
        }}>
            <img 
            src={logo} 
            alt="MyPiggy - Logo"
            />
        </div>
    );

}

export default Logo;