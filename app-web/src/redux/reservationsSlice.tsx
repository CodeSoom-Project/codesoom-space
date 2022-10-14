import { createSlice } from '@reduxjs/toolkit';

interface ReservationState {
  isOpenReservationsModal: boolean,
  isDetail : boolean,

  date: string | null,
  plan: string,
}

export const initialState: ReservationState = {
  isOpenReservationsModal: false,
  isDetail: false,

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
    checkIsDetail: (state, { payload }) => ({
      ...state,
      isDetail: payload,
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
  checkIsDetail,
  saveDate,
  savePlan,
  resetReservations,
} = reservationsSlice.actions;

export default reservationsSlice.reducer;
