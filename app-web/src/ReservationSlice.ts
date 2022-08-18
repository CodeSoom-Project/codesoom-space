import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  reservationFields: {
    seatNumber: '',
    userName: '',
    checkIn: '',
    checkOut: '',
  },
  seatNumber: '',
};

export const reservationSlice = createSlice({
  name: 'reservation',
  initialState,
  reducers: {
    changeReservationFields(state, { payload: { name, value } }) {
      return {
        ...state,
        reservationFields: {
          ...state.reservationFields,
          [name]: value,
        },
      };
    },
    changeReservationDetailsSeatNumber(state, { payload: { seatNumber } }) {
      return {
        ...state,
        seatNumber,
      };
    },
  },
});

export const {
  changeReservationFields,
  changeReservationDetailsSeatNumber,
} = reservationSlice.actions;

export default reservationSlice.reducer;
