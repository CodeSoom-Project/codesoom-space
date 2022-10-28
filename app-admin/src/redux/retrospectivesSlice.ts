import { createSlice } from '@reduxjs/toolkit';

import { getRetrospectives } from '../service/retrospectives';

import { AppDispatch } from '../store';

import { AxiosError } from 'axios';

interface RetrospectivesState {
  content: string;
  showRetrospectivesModal: boolean;
}

export const initialState: RetrospectivesState = {
  content: '',
  showRetrospectivesModal: false,
};

const { actions, reducer } = createSlice({
  name: 'retrospectives',
  initialState,
  reducers: {
    saveDetailRetrospectives: (state, { payload }) => ({
      ...state,
      content: payload,
      showRetrospectivesModal: true,
    }),
    toggleRetrospectivesModal: (state) => ({
      ...state,
      showRetrospectivesModal: !state.showRetrospectivesModal,
    }),
  },
});

export const {
  saveDetailRetrospectives,
  toggleRetrospectivesModal,
} = actions;

export const loadRetrospectives = (id: number) => {
  return async (dispatch: AppDispatch) => {
    try {
      const { content } = await getRetrospectives(id);

      dispatch(saveDetailRetrospectives(content));
    } catch (error) {
      const response = (error as AxiosError).response;
      alert(response!.data);
    }
  };
};

export default reducer;
