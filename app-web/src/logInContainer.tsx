import { useNavigate } from 'react-router-dom';

import { useForm } from 'react-hook-form';

import { useMutation } from 'react-query';

import { login } from './services/api';

import LogIn from './logIn';
import HeaderContainer from './HeaderContainer';


export default function LogInContainer() {
  const { register, formState: { errors }, handleSubmit } = useForm();

  const navigate = useNavigate();

  const loginMutate = async ({ email, password }: { email: any, password: string }) => {
    const accessToken = await login({ email, password });
    return accessToken;
  };

  const { mutate } = useMutation('login', loginMutate, {
    onSuccess: async () => {
      console.log('login 성공');
      navigate('/', { replace: true });
    },
    onError: async (e) => {
      console.error(e);
    },
  });

  return (
    <>
      <HeaderContainer/>
      <LogIn
        register={register}
        errors={errors}
        handleSubmit={handleSubmit}
        login={mutate}
      />
    </>
  );
}
