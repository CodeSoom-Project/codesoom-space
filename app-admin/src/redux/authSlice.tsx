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
}

const initialState: LoginState = {
  loginFields: {
    email: '',
    password: '',
  },
  accessToken: '',
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
  },
});

export const {
  changeLoginField,
  setAccessToken,
} = actions;

export const requestLogin = () => {
  return async (dispatch: AppDispatch, getState: ()=> RootState) => {
    const { loginFields: { email, password } } = getState().auth;

    await postLogin({ email, password })
      .then(({ accessToken }) => {
        saveItem('accessToken', accessToken);

        dispatch(setAccessToken(accessToken));
      }).catch((error) => {
        alert(error.response.data);
      });
  };
};

export default reducer;
