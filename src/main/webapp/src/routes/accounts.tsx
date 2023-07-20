import axios from "axios";
import { useEffect, useState } from "react";
import CreateAccount from "../components/Account/CreateAccount";

export interface Account {
  id: number;
  name: string;
  dollar: number;
  cents: number;
  onBudget: boolean;
  orderPosition: number;
  createdDate: Date;
  updatedDate: Date;
}

export default function Accounts({
  params,
}: {
  params: { accountId: string };
}) {
  console.log(params.accountId);

  const [accountList, setAccountList] = useState<any[]>([]);

  // useEffect(() => {
  //   const cancelToken = axios.CancelToken.source();

  //   axios
  //     .get("/api/v1/account/all", { cancelToken: cancelToken.token })
  //     .then((res) => {
  //       console.log(res.data);
  //       setAccountList(res.data);
  //     })
  //     .catch((err) => {
  //       if (axios.isCancel(err)) {
  //         console.log("canceled");
  //       } else {
  //         console.log(err);
  //       }
  //     });

  //   return () => {
  //     cancelToken.cancel();
  //   };
  // }, []);


  const createAccount = () => {
    const cancelToken = axios.CancelToken.source();

    axios
      .post(
        "/api/v1/account/create",
        { name: "accountName" },
        { cancelToken: cancelToken.token }
      )
      .then((res) => {
        console.log(res.data);
        setAccountList((prev) => [...prev, res.data]);
      })
      .catch((err) => {
        if (axios.isCancel(err)) {
          console.log("canceled");
        } else {
          console.log(err);
        }
      });

    return () => {
      cancelToken.cancel();
    };
  };

  if (accountList.length === 0) {
    return (
      <div>
        No accounts found! Click here to create one!
        <button
          className="btn"
          onClick={() => {
            const modal = document.getElementById(
              "createAccountModal"
            ) as HTMLFormElement;
            if (modal) {
              modal.showModal();
            }
          }}
        >
          Create Account
        </button>
        <dialog id="createAccountModal" className={`modal bg-transparent`}>
          <form method="dialog" className={`modal-box modal-bottom sm:modal-middle`}>
            <CreateAccount />
            <div className="modal-action">
              {/* if there is a button in form, it will close the modal */}
              <button className="btn">
                Close
              </button>
            </div>
          </form>
        </dialog>
      </div>
    );
  }

  return (
    <div>
      <button className="btn" onClick={createAccount}>
        Create New Account
      </button>
      Account {JSON.stringify(accountList)} works!
    </div>
  );
}
