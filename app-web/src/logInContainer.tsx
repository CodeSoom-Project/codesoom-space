import { useNavigate } from 'react-router-dom';

import { useForm } from 'react-hook-form';

import { useMutation } from 'react-query';

import { useDispatch } from 'react-redux';

import { login } from './services/api';


import LogIn from './logIn';
import { saveItem } from './services/stoage';
import { setAccessToken } from './redux/authSlice';

export default function LogInContainer() {
  const dispatch = useDispatch();

  const { register, formState: { errors }, handleSubmit } = useForm();

  const navigate = useNavigate();

  const { mutate } = useMutation('login', login, {
    onSuccess: async (accessToken) => {
      saveItem('accessToken', accessToken);

      dispatch(setAccessToken(accessToken));

      navigate('/', { replace: true });
    },
    onError: async (e) => {
      console.error(e);
    },
  });

  return (
    <>
      <LogIn
        register={register}
        errors={errors}
        handleSubmit={handleSubmit}
        login={mutate}
      />
    </>
  );
}
