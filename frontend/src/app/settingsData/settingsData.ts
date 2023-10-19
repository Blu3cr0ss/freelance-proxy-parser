import {
  PayloadAction,
  SliceCaseReducers,
  createSlice,
} from "@reduxjs/toolkit";
import { RootState } from "../store";
import { initialState } from "./initialState";

export const enabledBlackLists = createSlice<
  string[],
  SliceCaseReducers<string[]>,
  string
>({
  name: "enabledBlackLists",
  initialState: [],
  reducers: {
    add: (state, action: PayloadAction<string>) => {
      state.push(action.payload);
      return state;
    },
    remove: (state, action: PayloadAction<string>) => {
      state.splice(state.indexOf(action.payload), 1);
      return state;
    },
  },
});

export const settingsData = createSlice({
  name: "settingsData",
  initialState,
  reducers: {
    setApiKeysString: (state, action: PayloadAction<string>) => {
      state.apiKeysString = action.payload;
    },
    setIpString: (state, action: PayloadAction<string>) => {
      state.ipString = action.payload;
    },
  },
});

export const apiKeysStringSelector = (state: RootState) =>
  state.settingsData.apiKeysString;
export const ipStringSelector = (state: RootState) =>
  state.settingsData.ipString;
export const enabledBlackListsSelector = (state: RootState) =>
  state.enabledBlackLists;

export const { setApiKeysString, setIpString } = settingsData.actions;
