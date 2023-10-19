export type InitialResultData = {
  isLoading: boolean;
  isLoaded: boolean;
  error: string;
  data: ResultData[];
};

export type ResultData = {
  ip: string;
  fraudScore: number;
  fakeIp: string;
};

export const initialState: InitialResultData = {
  isLoading: false,
  isLoaded: false,
  error: "",
  data: [],
};
