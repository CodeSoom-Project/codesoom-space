import { createSlice } from '@reduxjs/toolkit';

import { postLogin } from '../service/auth';
import { saveItem } from '../services/storage';

import { AppDispatch, RootState } from '../store';

export interface LoginFields {
  email: string;
  password: string;
}

interface LoginState {
  loginFields: LoginFields;
  accessToken: string;
  errorMessage: string;
}

const initialState: LoginState = {
  loginFields: {
    email: '',
    password: '',
  },
  accessToken: '',
  errorMessage: '',
};

const { reducer, actions } = createSlice({
  name: 'login',
  initialState,
  reducers: {
    changeLoginField: (state, { payload: { name, value } }) => {
      return {
        ...state,
        loginFields: {
          ...state.loginFields,
          [name]: value,
        },
      };
    },
    setAccessToken: (state, { payload }) => {
      return {
        ...state,
        accessToken: payload,
      };
    },
    setErrorMessage: (state, { payload }) => {
      return {
        ...state,
        errorMessage: payload,
      };
    },
  },
});

export const {
  changeLoginField,
  setAccessToken,
  setErrorMessage,
} = actions;

export const requestLogin = () => {
  return async (dispatch: AppDispatch, getState: ()=> RootState) => {
    const { loginFields: { email, password } } = getState().auth;

    try {
      const accessToken = await postLogin({ email, password });

      saveItem('accessToken', accessToken);

      dispatch(setAccessToken(accessToken));
    } catch (error) {
      dispatch(setErrorMessage('아이디와 비밀번호를 확인해주세요.'));
    }
  };
};

export default reducer;
