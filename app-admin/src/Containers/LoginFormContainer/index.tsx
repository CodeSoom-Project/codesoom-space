import { useEffect } from 'react';

import { useNavigate } from 'react-router-dom';

import { useSelector } from 'react-redux';

import { useAppDispatch } from '../../hooks';

import { RootState } from '../../store';

import {
  requestLogin,
  changeLoginField,
  setErrorMessage,
} from '../../redux/authSlice';

import LoginForm from '../../components/LoginForm';

const LoginFormContainer = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const {
    loginFields: { email, password },
    errorMessage,
    accessToken,
  } = useSelector((state: RootState) => state.auth);

  const onChange = ({ name, value }: { name: string, value: string }) => {
    dispatch(changeLoginField({ name, value }));
  };

  const handleSubmit: React.MouseEventHandler<HTMLButtonElement> = () => {
    dispatch(requestLogin());
  };

  useEffect(() => {
    if (errorMessage) {
      alert(errorMessage);
    }

    if (accessToken) {
      navigate('reservations');
    }

    return () => {
      dispatch(setErrorMessage(''));
    };
  }, [errorMessage, accessToken]);

  return (
    <LoginForm
      fields={{ email, password }}
      handleSubmit={handleSubmit}
      onChange={onChange}
    />
  );
};

export default LoginFormContainer;
