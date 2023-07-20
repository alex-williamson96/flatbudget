import axios from "axios";

export class RequestHelper {
  private baseURL = "";

  constructor(baseUrl: string) {
    this.baseURL = baseUrl;
  }

  apiClient = axios.create({
    baseURL: this.baseURL,
    headers: {
      "Content-Type": "application/json",
    },
  });

  newAbortSignal(timeoutMs: number) {
    const abortController = new AbortController();
    setTimeout(() => abortController.abort, timeoutMs || 0);
  
    return abortController.signal;
}

  get = async (url: string): Promise<any> => {
    return this.apiClient
      .get<any>(this.baseURL + url, {
        signal: this.newAbortSignal(5000),
      })
      .then((res) => res.data)
      .catch((err) => {
        if (axios.isCancel(err)) {
          console.log("canceled");
        } else {
          console.log(err);
          return err;
        }
      });
  };

  post = (url: string, body: any) => {
    const cancelToken = axios.CancelToken.source();

    return this.apiClient
      .post<any>(this.baseURL + url, body, { signal: this.newAbortSignal(5000) })
      .then((res) => res.data)
      .catch((err) => {
        if (axios.isCancel(err)) {
          console.log("canceled");
        } else {
          console.log(err);
          return err;
        }
      });
  };
}
