import { configureStore } from '@reduxjs/toolkit';

import reservationsReudcer from './redux/reservationsSlice';
import retrospectivesReducer from './redux/retrospectivesSlice';
import authSlice from './redux/authSlice';

export const store = configureStore({
  reducer: {
    reservations: reservationsReudcer,
    retrospectives: retrospectivesReducer,
    auth: authSlice,
  },
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
