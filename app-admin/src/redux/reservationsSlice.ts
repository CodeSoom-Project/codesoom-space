import { createSlice } from '@reduxjs/toolkit';

import { AppDispatch, RootState } from './../store';

import { getReservations } from '../service/reservations';

import { Pagination, Reservation } from '../components/ReservationsList/typings';

interface ReservationsState {
  pagination: Pagination;
  reservations: Reservation[];
}

export const initialState: ReservationsState = {
  pagination: {
    page: 1,
    size: 10,
    totalPages: 1,
  },
  reservations: [],
};

const { actions, reducer } = createSlice({
  name: 'reservations',
  initialState,
  reducers: {
    savePagination: (state, { payload }) => ({
      ...state,
      pagination: payload,
    }),
    savePage: (state, { payload }) => ({
      ...state,
      pagination: {
        ...state.pagination,
        page: payload,
      },
    }),
    saveReservations: (state, { payload }) => ({
      ...state,
      reservations: payload,
    }),
  },
});

export const {
  savePagination,
  savePage,
  saveReservations,
} = actions;

export function loadReservations() {
  return async (dispatch: AppDispatch, getState: ()=> RootState) => {
    const { reservations: { pagination: { page, size } } } = getState();

    try {
      const { pagination, reservations } = await getReservations({ page, size });

      dispatch(savePagination(pagination));
      dispatch(saveReservations(reservations));
    } catch (error) {
      alert('정보가 없습니다');
    }
  };
}

export default reducer;
