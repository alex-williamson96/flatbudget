import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./index.css";
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import Budget from "./routes/budget";
import Profile from "./routes/profile";
import Accounts, {loader as accountLoader} from "./routes/accounts";
import Reports from "./routes/reports";

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      {
        path: '',
        element: <Budget />
      },
      {
        path: '/profile',
        element: <Profile />
      },
      {
        path: 'accounts/all',
        element: <Accounts />,
        loader: accountLoader
      },
      {
        path: 'accounts/:accountId',
        element: <Accounts />,
        loader: accountLoader
      },
      {
        path: 'reports',
        element: <Reports />
      }
    ]
  },
  
])

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>
);
