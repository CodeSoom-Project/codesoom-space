import {createSlice} from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: 'auth',
  initialState: {accessToken: null},
  reducers: {
    setAccessToken: (state, action) => {
      state.accessToken = action.payload
    },
    logout: (state, action) => {
      state.accessToken = null
    }
  },
})

export const {setAccessToken, logout} = authSlice.actions

export default authSlice.reducer
