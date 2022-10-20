import { createSlice } from '@reduxjs/toolkit';

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    isTokenExpired: false,

    accessToken: '',
  },
  reducers: {
    toggleIsTokenExpired(state) {
      return {
        ...state,
        isTokenExpired: !state.isTokenExpired,
      };
    },

    setAccessToken(state, { payload: accessToken }) {
      return {
        ...state,
        accessToken,
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
  toggleIsTokenExpired,
  setAccessToken,
  logout,
} = authSlice.actions;

export default authSlice.reducer;
