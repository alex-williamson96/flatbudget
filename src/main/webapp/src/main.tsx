import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";
import Footer from "./components/UI/Footer/Footer";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <App />
    <Footer />
  </React.StrictMode>
);
