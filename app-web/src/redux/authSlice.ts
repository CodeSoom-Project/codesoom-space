import { createSlice } from '@reduxjs/toolkit';

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    isTokenExpired: false,

    accessToken: '',
  },
  reducers: {
    setAccessToken(state, { payload: accessToken }) {
      return {
        ...state,
        accessToken,
      };
    },

    setIsTokenExpired(state, { payload }) {
      return {
        ...state,
        isTokenExpired: payload,
      };
    },


    logout(state) {
      return {
        ...state,
        accessToken: '',
      };
    },
  },
});

export const {
  setAccessToken,
  setIsTokenExpired,
  logout,
} = authSlice.actions;

export default authSlice.reducer;
