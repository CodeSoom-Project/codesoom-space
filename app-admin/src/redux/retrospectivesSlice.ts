import { createSlice } from '@reduxjs/toolkit';

interface RetrospectivesState {
  retrospectives: string;
  isOpenRetrospectivesModal: boolean;
}

export const initialState: RetrospectivesState = {
  retrospectives: '',
  isOpenRetrospectivesModal: false,
};

const { actions, reducer } = createSlice({
  name: 'retrospectives',
  initialState,
  reducers: {
    viewRetrospectives: (state, { payload }) => ({
      ...state,
      retrospectives: payload,
    }),
    toggleRetrospectivesModal: (state) => ({
      ...state,
      isOpenRetrospectivesModal: !state.isOpenRetrospectivesModal,
    }),
  },
});

export const {
  viewRetrospectives,
  toggleRetrospectivesModal,
} = actions;

export default reducer;
