import { Link } from "wouter";

export default function TopNavBarDropDown() {
  return (
    <label className="dropdown dropdown-hover border-solid">
      <Link href="/accounts/all">
        <label tabIndex={0} className="btn m-1 btn-sm btn-neutral">
          Accounts
        </label>
      </Link>
      <ul
        tabIndex={0}
        className="dropdown-content menu p-2 bg-primary-focus rounded-box border-2 border-primary w-max text-left"
      >
        <li className="w-full">
          <Link className="block w-full" href="/accounts/1">Amex Gold: <span className="float-right"> -$1,205.97</span></Link>
        </li>
        <li className="w-full">
          <Link className="block w-full" href="/accounts/2">Item 2: <span className="float-right">$1,230.34</span></Link>
        </li>
      </ul>
    </label>
  );
}
