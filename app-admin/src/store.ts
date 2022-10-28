import { configureStore } from '@reduxjs/toolkit';

import reservationsReudcer from './redux/reservationsSlice';

export const store = configureStore({
  reducer: {
    reservations: reservationsReudcer,
  },
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;
