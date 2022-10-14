import { createSlice } from '@reduxjs/toolkit';

interface ReservationState {
  isOpenReservationsModal: boolean;

  date: string | null;
  id: number;
  content: string;
}

export const initialState: ReservationState = {
  isOpenReservationsModal: false,

  date: null,
  id: 0,
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
    selectReservationId: (state, { payload }) => ({
      ...state,
      id: payload,
    }),
  },
});

export const {
  toggleReservationsModal,
  saveDate,
  saveContent,
  resetReservations,
  selectReservationId,
} = reservationsSlice.actions;

export default reservationsSlice.reducer;
