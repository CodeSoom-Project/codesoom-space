import { configureStore } from '@reduxjs/toolkit';
import reservationReducer from './ReservationSlice';
import authSlice from './authSlice';

export const store = configureStore({
  reducer: {
    reservation: reservationReducer,
    auth: authSlice,
  },
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
