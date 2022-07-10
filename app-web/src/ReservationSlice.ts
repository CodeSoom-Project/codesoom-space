import { createSlice } from '@reduxjs/toolkit'

interface ReservationState {
  seatNumber: number;
  name: string,
  checkIn: string,
  checkOut: string,
}

interface ReservationFields {
  reservationFields: ReservationState;
}

const initialState: ReservationFields = {
  reservationFields: {
    seatNumber: 1,
    name: '',
    checkIn: '',
    checkOut: '',
  }
}

export const reservationSlice = createSlice({
  name: 'reservation',
  initialState,
  reducers: {
    changeReservationFields(state, { payload: { name, value }}) {
      return {
        ...state,
        reservationFields: {
          ...state.reservationFields,
          [name]: value,
        },
      };
    },
  },
})

export const { changeReservationFields } = reservationSlice.actions

export default reservationSlice.reducer
