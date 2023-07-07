import axios from "axios";
import { useEffect, useState } from "react";

export interface UserProfile {
  accountList?: any;
  activeBudget: any;
  budgetList: any;
  createdDate: Date;
  currency: string;
  currencyFormat: string;
  dateFormat: string;
  email: string;
  firstName: string;
  id: string;
  lastName: string;
  password?: string;
  updatedDate: string;
}

const Profile = () => {
  const [profile, setProfile] = useState<UserProfile>({} as UserProfile);

  useEffect(() => {
    axios.get("/api/v1/user").then((res) => {
      console.log(res.data);
      setProfile(res.data);
    });
  }, []);

  return <div>Profile works! {JSON.stringify(profile)}</div>;
};

export default Profile;
