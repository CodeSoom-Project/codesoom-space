import { createSlice } from '@reduxjs/toolkit';

interface ReservationState {
  isOpenReservationsModal: boolean,
  isOpenReservationDetailsModal: boolean,

  date: string | null,
  plan: string,
}

export const initialState: ReservationState = {
  isOpenReservationsModal: false,
  isOpenReservationDetailsModal: false,

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
    toggleReservationDetailsModal: (state) => ({
      ...state,
      isOpenReservationDetailsModal: !state.isOpenReservationDetailsModal,
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
    resetReservations: (state) => ({
      ...state,
      date: initialState.date,
      plan: initialState.plan,
    }),
  },
});

export const {
  toggleReservationsModal,
  toggleReservationDetailsModal,
  saveDate,
  savePlan,
  resetReservations,
} = reservationsSlice.actions;

export default reservationsSlice.reducer;
