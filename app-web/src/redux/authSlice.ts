import { createSlice } from '@reduxjs/toolkit';

interface State {
  isTokenExpired: boolean;

  accessToken: string;
}

const initialState: State = {
  isTokenExpired: false,

  accessToken: '',
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setAccessToken: (state, { payload: accessToken }) => ({
      ...state,
      accessToken,
    }),

    setTokenExpired: (state) => ({
      ...state,
      isTokenExpired: true,
    }),

    logout: (state) => ({
      ...state,
      accessToken: '',
      isTokenExpired: false,
    }),
  },
});

export const {
  setAccessToken,
  setTokenExpired,
  logout,
} = authSlice.actions;

export default authSlice.reducer;
