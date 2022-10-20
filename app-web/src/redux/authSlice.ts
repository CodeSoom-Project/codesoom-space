import { createSlice } from '@reduxjs/toolkit';

interface State {
  tokenExpired: boolean;

  accessToken: string;
}

const initialState: State = {
  tokenExpired: false,

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
      tokenExpired: true,
    }),

    logout: (state) => ({
      ...state,
      accessToken: '',
      tokenExpired: false,
    }),
  },
});

export const {
  setAccessToken,
  setTokenExpired,
  logout,
} = authSlice.actions;

export default authSlice.reducer;
