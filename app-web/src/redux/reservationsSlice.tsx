import { createSlice } from '@reduxjs/toolkit';
import dayjs from 'dayjs';

export const initialState = {
  isOpenReservationsModal: false,
  isOpenRetrospectModal: false,

  date: dayjs().add(1, 'day').format('YYYY-MM-DD'),
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
      console.log(payload);
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
  savePlan } = reservationsSlice.actions;

export default reservationsSlice.reducer;
