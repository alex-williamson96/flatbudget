import { LoaderFunction, LoaderFunctionArgs, useLoaderData } from "react-router-dom";

export function loader(params: LoaderFunctionArgs) {
  const typedParams = params.params;
  return typedParams;
}

export default function Accounts() {
  const params = useLoaderData() as {accountId:string};


  
  const data = params.accountId ? params.accountId : 'all';

  return <div>Account {data} works!</div>;
}
