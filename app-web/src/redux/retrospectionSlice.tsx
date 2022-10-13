import { createSlice } from '@reduxjs/toolkit';

interface RetrospectionState {
  retrospections: string,
}

const initialState: RetrospectionState = {
  retrospections: '',
};

const { reducer, actions } = createSlice({
  name: 'retrospections',
  initialState,
  reducers: {
    writeRetrospection: (state, { payload }) => {
      return {
        ...state,
        retrospections: payload,
      };
    },
  },
});

export const {
  writeRetrospection,
} = actions;

export default reducer;
