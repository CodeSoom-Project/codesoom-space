import { createSlice } from '@reduxjs/toolkit';

interface VerificationState {
  verificationError: string;
}

export const initialState: VerificationState = {
  verificationError: '',
};

const verificationSlice = createSlice({
  name: 'verification',
  initialState,
  reducers: {
    saveError: (state, { payload }) => ({
      ...state,
      verificationError: payload,
    }),
  },
});

export const {
  saveError,
} = verificationSlice.actions;

export default verificationSlice.reducer;
