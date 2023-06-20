import { Outlet } from 'react-router-dom';
import BudgetTable from '../BudgetTable/BudgetTable';
import TopNavBar from './TopNavBar/TopNavBar';

export default function SideDrawer() {
  return (
    <div className='drawer'>
      <input
        id='my-drawer-3'
        type='checkbox'
        className='drawer-toggle'
      />
      <div className='drawer-content flex flex-col'>
        <TopNavBar />
        <Outlet />
      </div>
      <div className='drawer-side'>
        <label
          htmlFor='my-drawer-3'
          className='drawer-overlay'
        ></label>
        <ul className='menu p-4 w-40 bg-base-100'>
          <li>
            <a>Sidebar Item 1</a>
          </li>
          <li>
            <a>Sidebar Item 2</a>
          </li>
        </ul>
      </div>
    </div>
  );
}
