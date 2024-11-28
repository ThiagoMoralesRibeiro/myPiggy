import "../styles/components/signin.css";

function SignIn({href, target, width, height, type, children}){
    return (
        <a 
        type={type}
        class="signin"
        href={href}
        target={target}
        style = {{
            width: width || "350px",
            height: height || "50px"
        }}>
            <span>
                {children}
            </span>
        </a>
    );

}

export default SignIn;