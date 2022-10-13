import { createSlice } from '@reduxjs/toolkit';

interface RetrospectionState {
  isOpenRetrospectModal:boolean,
  retrospections: string,
}

const initialState: RetrospectionState = {
  isOpenRetrospectModal: false,
  retrospections: '',
};

const { reducer, actions } = createSlice({
  name: 'retrospections',
  initialState,
  reducers: {
    toggleRetrospectModal: (state) => ({
      ...state,
      isOpenRetrospectModal: !state.isOpenRetrospectModal,
    }),
    writeRetrospection: (state, { payload }) => {
      return {
        ...state,
        retrospections: payload,
      };
    },
  },
});

export const {
  toggleRetrospectModal,
  writeRetrospection,
} = actions;

export default reducer;
