import { useNavigate } from 'react-router-dom';

import { useDispatch } from 'react-redux';

import { useForm } from 'react-hook-form';

import { useMutation } from 'react-query';

import { AxiosError } from 'axios';

import { setAccessToken } from './redux/authSlice';

import { saveItem } from './services/stoage';
import { login } from './services/api';

import LogIn from './logIn';

import { User } from './typings/auth';

export default function LogInContainer() {
  const dispatch = useDispatch();

  const { register, formState: { errors }, handleSubmit } = useForm<User>();

  const navigate = useNavigate();

  const { mutate } = useMutation('login', login, {
    onSuccess: (accessToken) => {
      saveItem('accessToken', accessToken);

      dispatch(setAccessToken(accessToken));

      navigate('/', { replace: true });
    },
    onError: (error) => {
      const response = (error as AxiosError).response;
      alert(response!.data);
    },
  });

  return (
    <LogIn
      register={register}
      errors={errors}
      handleSubmit={handleSubmit}
      login={mutate}
    />
  );
}
