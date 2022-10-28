import { createSlice } from '@reduxjs/toolkit';

interface MypageState {
  emailVerification: string;
}

export const initialState: MypageState = {
  emailVerification: 'fail',
};

const mypageSlice = createSlice({
  name: 'mypage',
  initialState,
  reducers: {
    sendEmail: (state, { payload }) => ({
      ...state,
      emailVerification: payload,
    }),
  },
});

export const {
  sendEmail,
} = mypageSlice.actions;

export default mypageSlice.reducer;