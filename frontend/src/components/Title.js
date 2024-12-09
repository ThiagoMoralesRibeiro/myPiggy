import "../styles/components/text.css";

function Title({children, color, fontsize}){
    return (
        <h1
        className="title"
        style={{
            color: color || "white",
            fontSize: fontsize || "34px"
        }}>
            {children}
        </h1>
    );

}

export default Title;
