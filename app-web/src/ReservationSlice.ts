import { createSlice } from '@reduxjs/toolkit'

interface ReservationState {
  seatNumber: number;
  name: string,
  checkIn: string,
  checkOut: string,

}

const initialState: ReservationState = {
  seatNumber: 0,
  name: '',
  checkIn: '',
  checkOut: '',
}

export const reservationSlice = createSlice({
  name: 'reservation',
  initialState,
  reducers: {
    setSeatNumber(state, { payload: seatNumber }) {
      return {
        ...state,
        seatNumber,
      };
    },
    setName(state, { payload: name }) {
      return {
        ...state,
        name,
      };
    },
    setCheckIn(state, { payload: checkIn }) {
      return {
        ...state,
        checkIn,
      };
    },
    setCheckOut(state, { payload: checkOut }) {
      return {
        ...state,
        checkOut,
      };
    },
  },
})

export const { setSeatNumber, setName, setCheckIn, setCheckOut } = reservationSlice.actions

export default reservationSlice.reducer
