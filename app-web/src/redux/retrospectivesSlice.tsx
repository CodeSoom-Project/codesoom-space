import { createSlice } from '@reduxjs/toolkit';

interface RetrospectivesState {
  isOpenRetrospectModal: boolean,
  retrospectives: string,
}

const initialState: RetrospectivesState = {
  isOpenRetrospectModal: false,
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
    writeRetrospectives: (state, { payload }) => {
      return {
        ...state,
        retrospectives: payload,
      };
    },
  },
});

export const {
  toggleRetrospectModal,
  writeRetrospectives,
} = actions;

export default reducer;
