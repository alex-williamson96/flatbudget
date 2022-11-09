export default function TopNavBarDropDown() {
  return (
    <div className='dropdown dropdown-hover dropdown-bottom'>
      <label
        tabIndex={0}
        className='btn m-1'
      >
        Accounts
      </label>
      <ul
        tabIndex={0}
        className='dropdown-content menu p-2 bg-base-100 rounded-box w-52'
      >
        <li>
          <a>Item 1</a>
        </li>
        <li>
          <a>Item 2</a>
        </li>
      </ul>
    </div>
  );
}
