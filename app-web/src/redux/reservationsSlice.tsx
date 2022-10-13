import { createSlice } from '@reduxjs/toolkit';
import dayjs from 'dayjs';

interface ReservationState {
  isOpenReservationsModal: boolean,
  isOpenRetrospectModal: boolean,

  date: dayjs.Dayjs | null,
  plan: string,
}

export const initialState: ReservationState = {
  isOpenReservationsModal: false,
  isOpenRetrospectModal: false,

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
    toggleRetrospectModal: (state) => ({
      ...state,
      isOpenRetrospectModal: !state.isOpenRetrospectModal,
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
  toggleRetrospectModal,
  saveDate,
  savePlan,
} = reservationsSlice.actions;

export default reservationsSlice.reducer;
