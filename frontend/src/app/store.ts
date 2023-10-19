import { Action, ThunkAction, configureStore } from "@reduxjs/toolkit";
import { settingsApi } from "../features/parser/settings/api";
import { settingsData, enabledBlackLists } from "./settingsData/settingsData";
import { resultData } from "./resultData/resultData";

export const store = configureStore({
  reducer: {
    [settingsApi.reducerPath]: settingsApi.reducer,
    enabledBlackLists: enabledBlackLists.reducer,
    [settingsData.name]: settingsData.reducer,
    [resultData.name]: resultData.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(settingsApi.middleware),
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;
