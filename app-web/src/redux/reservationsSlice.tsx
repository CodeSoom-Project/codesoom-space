import { createSlice } from '@reduxjs/toolkit';

interface ReservationState {
  isOpenReservationsModal: boolean,

  date: string | null,
  plan: string,
}

export const initialState: ReservationState = {
  isOpenReservationsModal: false,

  date: null,
  plan: '',
};

const reservationsSlice = createSlice({
  name: 'reservations',
  initialState,
  reducers: {
    toggleReservationsModal: (state) => ({
      ...state,
      isOpenReservationsModal: !state.isOpenReservationsModal,
    }),
    saveDate: (state, { payload }) => {
      return ({
        ...state,
        date: payload,
      });
    },
    savePlan: (state, { payload }) => ({
      ...state,
      plan: payload,
    }),
  },
});

export const {
  toggleReservationsModal,
  saveDate,
  savePlan,
} = reservationsSlice.actions;

export default reservationsSlice.reducer;
