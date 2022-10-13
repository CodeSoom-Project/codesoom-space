import { configureStore } from '@reduxjs/toolkit';

import reservationReducer from './ReservationSlice';
import authSlice from './authSlice';
import retrospectionsReducer from './redux/retrospectionSlice';
import reservationsSlice from './redux/reservationsSlice';

export const store = configureStore({
  reducer: {
    reservation: reservationReducer,
    auth: authSlice,
    retrospections: retrospectionsReducer,
    reservations: reservationsSlice,
  },

});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
