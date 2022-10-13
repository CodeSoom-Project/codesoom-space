import { configureStore } from '@reduxjs/toolkit';

import reservationReducer from './ReservationSlice';
import authSlice from './authSlice';
import retrospectionsReducer from './redux/retrospectionSlice';
import reservationsSlice from './redux/reservationsSlice';

export const store = configureStore({
  reducer: {
    // TODO: 추후 reservation 미팅후 제거결정
    reservation: reservationReducer,
    auth: authSlice,
    retrospections: retrospectionsReducer,
    reservations: reservationsSlice,
  },

});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
