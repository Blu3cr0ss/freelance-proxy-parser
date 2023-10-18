import {
  PayloadAction,
  SliceCaseReducers,
  createAsyncThunk,
  createSlice,
} from "@reduxjs/toolkit";
import { RootState } from "../store";
import { InitialResultData, ResultData, initialState } from "./initialState";

import { createAction } from "@reduxjs/toolkit";
import { fetchEventSource } from "@microsoft/fetch-event-source";
import { Observable } from "rxjs";
import { errorMonitor } from "events";

export const resultData = createSlice({
  name: "resultData",
  initialState,
  reducers: {
    addResultData: (state, action: PayloadAction<ResultData>) => {
      state.data.push(action.payload);
    },
    clearResultData: (state, action: PayloadAction<void>) => {
      state.data = initialState.data;
    },
    setIsLoading: (state, action: PayloadAction<boolean>) => {
      state.isLoading = action.payload;
    },
    setIsLoaded: (state, action: PayloadAction<boolean>) => {
      state.isLoaded = action.payload;
    },
    setError: (state, action: PayloadAction<string>) => {
      state.error = action.payload;
    },
  },
});

export const resultDataSelector = (state: RootState) => state?.resultData;

const { addResultData, clearResultData, setIsLoading, setIsLoaded, setError } =
  resultData.actions;

let url = "localhost:8080/api/parser/parse";

export const fetchData = createAsyncThunk(
  "resultData",
  async (body: string, { dispatch, getState }) => {
    fetchEventSource(url, {
      method: "post",
      headers: {
        Accept: "text/event-stream",
        // Accept: "application/stream+json",
        // "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },

      body: "JSON.stringify(body)",
      onmessage(ev) {
        console.log("data ---> " + ev.data);

        // dispatch(addResultData(JSON.parse(ev.data)));
      },
      onclose() {
        // dispatch(setIsLoading(false));
        // dispatch(setIsLoaded(true));
      },
      onopen(res) {
        console.log("res ---> " + res);
        console.log("body ---> " + body);

        // dispatch(clearResultData());
        // dispatch(setIsLoading(true));
        // dispatch(setIsLoaded(false));
        return new Promise((resolve) => resolve());
      },
      onerror(err) {
        // dispatch(clearResultData());
        // dispatch(setIsLoading(false));
        // dispatch(setIsLoaded(false));
        // dispatch(setError(err));
        throw err;
      },
    }).catch((e) => {
      console.error("error in fetchEventSource ---> " + e);
    });
  }
);
