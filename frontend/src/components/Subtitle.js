import "../styles/components/text.css";

function Subtitle({children, color, fontsize}){
    return (
        <h4
        style={{
            color: color || "white",
            fontSize: fontsize || "18px"
        }}>
            {children}
        </h4>
    );

}

export default Subtitle;
