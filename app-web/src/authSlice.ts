import {createSlice} from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: 'auth',
  initialState: {accessToken: ''},
  reducers: {
    setAccessToken(state, {payload: accessToken}) {
      return {
        ...state,
        accessToken,
      }
    },

    logout(state) {
      return {
        ...state,
        accessToken: '',
      }
    }
  },
})

export const {setAccessToken, logout} = authSlice.actions

export default authSlice.reducer
