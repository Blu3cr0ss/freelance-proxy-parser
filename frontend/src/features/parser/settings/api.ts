import { MaybePromise } from "@reduxjs/toolkit/dist/query/tsHelpers";
import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

// Define a service using a base URL and expected endpoints
export type BlackListType = {
  name: string;
  ipString: string;
  matchType: string;
};

export type ParseRequest = {
  proxy: string;
  apiKeys: string;
  blackList: string[];
  maxFraudScore: number;
};

export type ParseResponse = {
  ip: string;
  fraudScore: number;
  fakeIp: string;
};

export const settingsApi = createApi({
  tagTypes: ["blacklists", "parseResult"],
  reducerPath: "settings",
  baseQuery: fetchBaseQuery({ baseUrl: process.env.BASE_URL }),
  endpoints: (builder) => ({
    getBlackLists: builder.query<BlackListType[], string>({
      query: () => `blacklist/getAll`,
      providesTags: ["blacklists"],
    }),
    getBlackList: builder.query<unknown, string>({
      query: (name) => ({
        url: `blacklist/get`,
        body: name,
      }),
    }),
    deleteBlackList: builder.mutation<unknown, string>({
      query: (name) => ({
        url: `blacklist/delete`,
        method: "DELETE",
        body: name,
      }),
      invalidatesTags: ["blacklists"],
    }),
    updatePost: builder.mutation<unknown, BlackListType>({
      // note: an optional `queryFn` may be used in place of `query`
      query: (blackList) => ({
        url: `blacklist/save`,
        method: "POST",
        body: blackList,
      }),
      invalidatesTags: ["blacklists"],
    }),
  }),
});

// Export hooks for usage in functional components, which are
// auto-generated based on the defined endpoints
export const {
  useGetBlackListsQuery,
  useUpdatePostMutation,
  useDeleteBlackListMutation,
} = settingsApi;
