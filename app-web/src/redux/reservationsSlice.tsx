import { createSlice } from '@reduxjs/toolkit';

export const initialState = {
  isOpenReservationsModal: false,
  isOpenRetrospectModal: false,
};

const reservationsSlice = createSlice({
  name: 'reservations',
  initialState,
  reducers: {
    toggleReservationsModal: (state) => ({
      ...state,
      isOpenReservationsModal: !state.isOpenReservationsModal,
    }),
    toggleRetrospectModal: (state) => ({
      ...state,
      isOpenRetrospectModal: !state.isOpenRetrospectModal,
    }),
  },
});

export const { toggleReservationsModal, toggleRetrospectModal } = reservationsSlice.actions;

export default reservationsSlice.reducer;
