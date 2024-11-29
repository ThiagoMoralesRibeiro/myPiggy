import "../styles/components/text.css";

function Link({classe, href, target, color, fontsize, decoration, children}){
    return (
        <a className={`link ${classe}`}
        href={href}
        target={target}
        style = {{
            color: color || "black",
            fontSize: fontsize || "12px",
            textDecoration: decoration || "underscore",
        }}>
            {children}
        </a>
    );

}

export default Link;