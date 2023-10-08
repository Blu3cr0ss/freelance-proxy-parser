import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
// import type { Pokemon } from './types';

// Define a service using a base URL and expected endpoints
export type BlackListType = {
  name: string;
  ipString: string;
  matchType: string;
};

export const settingsApi = createApi({
  tagTypes: ["blacklists"],
  reducerPath: "settings",
  baseQuery: fetchBaseQuery({ baseUrl: process.env.BASE_URL }),
  endpoints: (builder) => ({
    getBlackLists: builder.query<BlackListType[], string>({
      query: () => `blacklist/getAll`,
      providesTags: ["blacklists"],
    }),
    getBlackList: builder.query<unknown, string>({
      query: (qqq) => `blacklist`,
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
        method: "post",
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
