import { createSlice } from '@reduxjs/toolkit';

interface RetrospectivesState {
  isOpenRetrospectModal: boolean,
  isDetail: boolean,
  retrospectives: string,
  id: number,
}

const initialState: RetrospectivesState = {
  isOpenRetrospectModal: false,
  isDetail: false,
  retrospectives: '',
  id: 0,
};

const { reducer, actions } = createSlice({
  name: 'retrospectives',
  initialState,
  reducers: {
    toggleRetrospectModal: (state) => ({
      ...state,
      isOpenRetrospectModal: !state.isOpenRetrospectModal,
    }),
    saveRetrospectives: (state, { payload }) => {
      return {
        ...state,
        retrospectives: payload,
      };
    },
    resetRetrospectives: (state) => ({
      ...state,
      retrospectives: initialState.retrospectives,
    }),
    selectResetRetrospectiveId: (state, { payload }) => ({
      ...state,
      id: payload,
    }),
    saveIsDetail: (state, { payload }) => ({
      ...state,
      isDetail: payload,
    }),
  },
});

export const {
  toggleRetrospectModal,
  saveRetrospectives,
  resetRetrospectives,
  selectResetRetrospectiveId,
  saveIsDetail,
} = actions;

export default reducer;
