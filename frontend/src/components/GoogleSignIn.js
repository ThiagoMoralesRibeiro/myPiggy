import google from "../imgs/icons/google.png";
import "../styles/components/signin.css";

function GoogleSignIn({href, target, width, height, children}){
    return (
        <a 
        className="google"
        href={href}
        target={target}
        style = {{
            width: width || "350px",
            height: height || "50px"
        }}>
            <img 
            src={google} 
            alt="Google - Logo"
            />
            <span>
                {children}
            </span>
        </a>
    );

}

export default GoogleSignIn;