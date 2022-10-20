import { createSlice } from '@reduxjs/toolkit';

interface RetrospectivesState {
  isOpenRetrospectModal: boolean,
  isDetail: boolean,
  isUpdate: boolean,
  retrospectives: string,
}

const initialState: RetrospectivesState = {
  isOpenRetrospectModal: false,
  isDetail: false,
  isUpdate: false,
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
    saveIsUpdateRetrospectives: (state, { payload }) => ({
      ...state,
      isUpdate: payload,
    }),
  },
});

export const {
  resetRetrospectives,
  saveRetrospectives,
  saveIsDetailRetrospectives,
  saveIsUpdateRetrospectives,
  toggleRetrospectModal,
} = actions;

export default reducer;
