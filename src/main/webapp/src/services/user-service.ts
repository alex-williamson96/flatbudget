import { AxiosResponse } from "axios";
import { UserProfile } from "../routes/profile";
import { RequestHelper } from "./requests";

const baseURL = "http://localhost:8080/api/v1/user";

const requestHelper = new RequestHelper(baseURL);

const getCurrentUser = async (): Promise<AxiosResponse<UserProfile>> => {
    return  await requestHelper.get('');
}

const updateUser = async (user: UserProfile, userId: string): Promise<AxiosResponse<UserProfile>> => {
    return await requestHelper.post(`/update/${userId}`, user)
}


const UserService = {
    getCurrentUser,
    updateUser
}

export default UserService;
