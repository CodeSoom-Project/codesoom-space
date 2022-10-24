import { createSlice } from '@reduxjs/toolkit';

interface ReservationState {
  isOpenReservationsModal: boolean;
  isDetail: boolean;
  isUpdate: boolean;

  date: string | null;
  id: number;
  content: string;
  status: string;
}

export const initialState: ReservationState = {
  isOpenReservationsModal: false,
  isDetail: false,
  isUpdate: false,

  date: null,
  id: 0,
  content: '',
  status: '',
};

const reservationsSlice = createSlice({
  name: 'reservations',
  initialState,
  reducers: {
    toggleReservationsModal: (state) => ({
      ...state,
      isOpenReservationsModal: !state.isOpenReservationsModal,
    }),
    saveDate: (state, { payload }) => ({
      ...state,
      date: payload,
    }),
    saveContent: (state, { payload }) => ({
      ...state,
      content: payload,
    }),
    saveIsDetail: (state, { payload }) => ({
      ...state,
      isDetail: payload,
    }),
    saveIsUpdate: (state, { payload }) => ({
      ...state,
      isUpdate: payload,
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
    reservationStatus: (state, { payload }) => ({
      ...state,
      status: payload,
    }),
  },
});

export const {
  toggleReservationsModal,
  saveDate,
  saveContent,
  saveIsDetail,
  saveIsUpdate,
  resetReservations,
  selectReservationId,
  reservationStatus,
} = reservationsSlice.actions;

export default reservationsSlice.reducer;
