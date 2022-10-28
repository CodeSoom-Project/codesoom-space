import { configureStore } from '@reduxjs/toolkit';

import reservationReducer from './ReservationSlice';
import authSlice from './redux/authSlice';
import retrospectivesReducer from './redux/retrospectivesSlice';
import reservationsSlice from './redux/reservationsSlice';
import verificationSlice from './redux/verificationSlice';
import mypageSlice from './redux/mypageSlice';

export const store = configureStore({
  reducer: {
    reservation: reservationReducer,
    auth: authSlice,
    retrospectives: retrospectivesReducer,
    reservations: reservationsSlice,
    verification: verificationSlice,
    mypage: mypageSlice,
  },

});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
