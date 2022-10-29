import { createSlice } from '@reduxjs/toolkit';

import { AxiosError } from 'axios';

import { AppDispatch, RootState } from './../store';

import { getReservations } from '../service/reservations';

import {
  Pagination,
  Reservation,
} from '../components/ReservationsList/typings';

interface ReservationsState {
  pagination: Pagination;
  reservations: Reservation[];
  errorMessage: string;
}

export const initialState: ReservationsState = {
  pagination: {
    page: 1,
    size: 10,
    totalPages: 1,
  },
  reservations: [],
  errorMessage: '',
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
    setErrorMessage: (state, { payload }) => ({
      ...state,
      errorMessage: payload,
    }),
  },
});

export const {
  savePagination,
  savePage,
  saveReservations,
  setErrorMessage,
} = actions;

export function loadReservations() {
  return async (dispatch: AppDispatch, getState: ()=> RootState) => {
    const { reservations: { pagination: { page, size } } } = getState();

    try {
      const { pagination, reservations } = await getReservations({ page, size });

      dispatch(savePagination(pagination));
      dispatch(saveReservations(reservations));
    } catch (error) {
      const status = (error as AxiosError).response?.status;

      if (status === 403) {
        return dispatch(setErrorMessage('권한이 없습니다.'));
      }

      if (status === 401) {
        return dispatch(setErrorMessage('로그인 후 이용해주세요.'));
      }

      dispatch(setErrorMessage('예기치 못한 오류가 발생했습니다. 잠시 후 다시 시도해주세요.'));
    }
  };
}

export default reducer;
