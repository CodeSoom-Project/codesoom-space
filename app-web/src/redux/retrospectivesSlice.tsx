import { createSlice } from '@reduxjs/toolkit';

interface RetrospectivesState {
  isOpenRetrospectModal: boolean,
  retrospectives: string,
  id: number,
}

const initialState: RetrospectivesState = {
  isOpenRetrospectModal: false,
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
    writeRetrospectives: (state, { payload }) => {
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
  },
});

export const {
  toggleRetrospectModal,
  writeRetrospectives,
  resetRetrospectives,
  selectResetRetrospectiveId,
} = actions;

export default reducer;
