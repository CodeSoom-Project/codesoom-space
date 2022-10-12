import { createSlice } from '@reduxjs/toolkit';

export const initialState = {
  isOpenReservationsModal: false,
};

const reservationsSlice = createSlice({
  name: 'reservations',
  initialState,
  reducers: {
    toggleReservationsModal: (state) => ({
      ...state,
      isOpenReservationsModal: !state.isOpenReservationsModal,
    }),
  },
});

export const { toggleReservationsModal } = reservationsSlice.actions;

export default reservationsSlice.reducer;
