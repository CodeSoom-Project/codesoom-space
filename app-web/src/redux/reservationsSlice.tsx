import { createSlice } from '@reduxjs/toolkit';

interface ReservationState {
  isOpenReservationsModal: boolean,

  date: string | null,
  content: string,
}

export const initialState: ReservationState = {
  isOpenReservationsModal: false,

  date: null,
  content: '',
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
    saveContent: (state, { payload }) => ({
      ...state,
      content: payload,
    }),
    resetReservations: (state) => ({
      ...state,
      date: initialState.date,
      content: initialState.content,
    }),
  },
});

export const {
  toggleReservationsModal,
  saveDate,
  saveContent,
  resetReservations,
} = reservationsSlice.actions;

export default reservationsSlice.reducer;
