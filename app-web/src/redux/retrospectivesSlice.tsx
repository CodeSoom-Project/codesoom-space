import { createSlice } from '@reduxjs/toolkit';

interface RetrospectivesState {
  isOpenRetrospectModal: boolean,
  isDetail: boolean,
  retrospectives: string,
}

const initialState: RetrospectivesState = {
  isOpenRetrospectModal: false,
  isDetail: false,
  retrospectives: '',
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
    saveIsDetailRetrospectives: (state, { payload }) => ({
      ...state,
      isDetail: payload,
    }),
  },
});

export const {
  resetRetrospectives,
  saveRetrospectives,
  saveIsDetailRetrospectives,
  toggleRetrospectModal,
} = actions;

export default reducer;
