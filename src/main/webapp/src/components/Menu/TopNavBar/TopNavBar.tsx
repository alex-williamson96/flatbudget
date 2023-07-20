import TopNavBarDropDown from "./TopNavBarDropDown";
import LeftArrow from "../../UI/Icons/LeftArrow";
import RightArrow from "../../UI/Icons/RightArrow";
import { Link } from "wouter";

export default function TopNavBar() {
  return (
    <div className="w-full navbar z-50 bg-base-300 opacity-100 fixed top-0 left-0">
      <div className="flex-none lg:hidden">
        <label htmlFor="my-drawer-3" className="btn btn-square btn-ghost">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            className="inline-block w-6 h-6 stroke-current"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M4 6h16M4 12h16M4 18h16"
            ></path>
          </svg>
        </label>
      </div>
      <div className="flex-1 px-2 m-1">
        <Link href="/budget">
          <label tabIndex={0} className="btn btn-sm btn-primary">
            Flat Budget
          </label>
        </Link>
        <label tabIndex={0} className="card-compact">
          <span>
            <span>
              <LeftArrow /> Hello <RightArrow />
            </span>
          </span>
        </label>
      </div>
      <div className="flex-none hidden lg:block">
        <ul className="menu menu-horizontal">
          <TopNavBarDropDown />
          <Link href="/reports">
            <label tabIndex={0} className="btn m-1 btn-sm btn-neutral">
              Reports
            </label>
          </Link>
          <Link href="/profile">
            <label tabIndex={0} className="btn m-1 btn-sm btn-neutral">
              Profile
            </label>
          </Link>
        </ul>
      </div>
    </div>
  );
}
