import { configureStore } from '@reduxjs/toolkit';

import reservationReducer from './ReservationSlice';
import authSlice from './redux/authSlice';
import retrospectivesReducer from './redux/retrospectivesSlice';
import reservationsSlice from './redux/reservationsSlice';

export const store = configureStore({
  reducer: {
    reservation: reservationReducer,
    auth: authSlice,
    retrospectives: retrospectivesReducer,
    reservations: reservationsSlice,
  },

});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
