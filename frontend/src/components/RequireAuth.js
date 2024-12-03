import { useState, useEffect } from "react";
import { useLocation, Navigate } from "react-router-dom";

function RequireAuth({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(null);
  const location = useLocation();

  useEffect(() => {
    const checkSession = async () => {
      try {
        const response = await fetch("http://localhost:8080/session-info", {
          method: "GET",
          credentials: "include",
        });

        if (response.ok) {
          const sessionData = await response.json();
          if (sessionData.email) {
            setIsAuthenticated(true);
          } else {
            setIsAuthenticated(false);
          }
        } else {
          setIsAuthenticated(false);
        }
      } catch (error) {
        console.error("Erro ao verificar a sessão", error);
        setIsAuthenticated(false);
      }
    };

    checkSession();
  }, []);

  if (isAuthenticated === null) {
    return <div>Loading...</div>;
  }

  return isAuthenticated ? children : <Navigate to="/login" state={{ from: location }} />;
}

export default RequireAuth;

