import { Link } from "react-router-dom";

export default function TopNavBarDropDown() {
  return (
    <label className="dropdown dropdown-hover border-solid">
      <Link to={`accounts/all`}>
        <label tabIndex={0} className="btn m-1 btn-sm">
          Accounts
        </label>
      </Link>
      <ul
        tabIndex={0}
        className="dropdown-content menu p-2 bg-stone-100 rounded-box border-2 border-gray-300 w-max text-left"
      >
        <li className="w-full">
          <Link className="block w-full" to={`accounts/1`}>Amex Gold: -$1,205.97</Link>
        </li>
        <li className="w-full">
          <Link className="block w-full" to={`accounts/2`}>Item 2</Link>
        </li>
      </ul>
    </label>
  );
}
