import { useNavigate } from 'react-router-dom';

import { useForm } from 'react-hook-form';

import { useMutation } from 'react-query';

import { login } from './services/api';

import LogIn from './logIn';

export default function LogInContainer() {
  const { register, formState: { errors }, handleSubmit } = useForm();

  const navigate = useNavigate();

  const loginMutate = async ({ email, password }: { email: any, password: string }) => {
    const accessToken = await login({ email, password });
    return accessToken;
  };

  const { mutate } = useMutation('login', loginMutate, {
    onSuccess: async () => {
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
