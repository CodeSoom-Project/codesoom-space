import { useNavigate } from 'react-router-dom';

import { useForm } from 'react-hook-form';

import { useMutation } from 'react-query';

import { AxiosError } from 'axios';

import { signUp } from './services/api';

import SignUp from './signUp';
import { SignUpFormData } from './typings/auth';

export default function SignUpContainer() {
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm<SignUpFormData>();

  const navigate = useNavigate();

  const { mutate } = useMutation('signup', signUp, {
    onSuccess: () => {
      alert('회원가입 되었습니다');

      navigate('/login', { replace: true });
    },
    onError: (error) => {
      const response = (error as AxiosError).response;
      alert(response!.data);
    },
  });

  return (
    <>
      <SignUp
        register={register}
        errors={errors}
        handleSubmit={handleSubmit}
        signUp={mutate}
      />
    </>
  );
}
