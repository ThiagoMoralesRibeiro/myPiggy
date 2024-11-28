function Input({label, type, name, value, max, min, maxlength, placeholder, required, onChange}){
    return (
        <label>
            {label}
            <input 
            type={type} 
            name={name} 
            value={value}
            max={max} 
            min={min}
            maxlength={maxlength}
            placeholder={placeholder}
            required={required ? true : false} 
            onChange={onChange}
            />
        </label>
    );

}

export default Input;