import { configureStore } from '@reduxjs/toolkit';

import reservationsReudcer from './redux/reservationsSlice';
import authSlice from './redux/authSlice';

export const store = configureStore({
  reducer: {
    reservations: reservationsReudcer,
    auth: authSlice,
  },
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
