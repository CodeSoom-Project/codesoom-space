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

    setIsTokenExpired(state) {
      return {
        ...state,
        isTokenExpired: true,
      };
    },

    logout(state) {
      return {
        ...state,
        accessToken: '',
        isTokenExpired: false,
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
