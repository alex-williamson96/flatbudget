import { AxiosError, AxiosResponse } from "axios";
import { UseQueryResult, useQuery, useQueryClient } from "react-query";
import UserService from "../services/user-service";


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

const useUser = (): UseQueryResult<AxiosResponse<UserProfile>> => {
  return useQuery(
    "user",
    UserService.getCurrentUser,
    { staleTime: 300000 }
  );
};

const Profile = () => {
  console.log(UserService.getCurrentUser)

  const { status, data } = useUser();

  return (
    <div>
      {status === "loading" ? (
        "Loading..."
      ) : (
        <span>
          Profile works! {status} {JSON.stringify(data)}
        </span>
      )}
    </div>
  );
};

export default Profile;
