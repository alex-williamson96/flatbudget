import TopNavBarDropDown from './TopNavBarDropDown';
import LeftArrow from '../../UI/Icons/LeftArrow'
import RightArrow from '../../UI/Icons/RightArrow'

export default function TopNavBar() {
  return (
    <div className='w-full navbar bg-base-300 rounded-md'>
      <div className='flex-none lg:hidden'>
        <label
          htmlFor='my-drawer-3'
          className='btn btn-square btn-ghost'>
          <svg
            xmlns='http://www.w3.org/2000/svg'
            fill='none'
            viewBox='0 0 24 24'
            className='inline-block w-6 h-6 stroke-current'>
            <path
              strokeLinecap='round'
              strokeLinejoin='round'
              strokeWidth='2'
              d='M4 6h16M4 12h16M4 18h16'
            ></path>
          </svg>
        </label>
      </div>
      <div className='flex-1 px-2 m-1'><label
        tabIndex={0}
        className='btn m-1'>
        Flat Budget
      </label>
        <label
          tabIndex={0}
          className='card-body'
        ><span>
            <span>
              <LeftArrow /> Hello <RightArrow />
            </span>
          </span>
        </label>
      </div>
      <div className='flex-none hidden lg:block'>
        <ul className='menu menu-horizontal'>
          <label
            tabIndex={0}
            className='btn m-1'>
            Budget
          </label>
          <TopNavBarDropDown />
          <label
            tabIndex={0}
            className='btn m-1'>
            Profile
          </label>
        </ul>
      </div>
    </div>
  );
}
