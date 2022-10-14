import { useNavigate } from 'react-router-dom';

import { useForm } from 'react-hook-form';

import { useMutation } from 'react-query';

import { signUp } from './services/api';

import SignUp from './signUp';

import HeaderContainer from './HeaderContainer';

export default function SignUpContainer() {
  const { register, formState: { errors }, handleSubmit } = useForm();

  const navigate = useNavigate();

  const signUpMutate = async ({
    name,
    email,
    password,
  }: { email: any, password: string, name: any }) => {
    const signUpResult = await signUp({ name, email, password });
    return signUpResult;
  };

  const { mutate } = useMutation('signup', signUpMutate, {
    onSuccess: async () => {
      console.log('회원가입 성공');
      alert('회원가입 되었습니다');
      navigate('/login', { replace: true });
    },
    onError: async (e) => {
      console.error(e);
    },
  });

  return (
    <>
      <HeaderContainer/>
      <SignUp
        register={register}
        errors={errors}
        handleSubmit={handleSubmit}
        signUp={mutate}
      />
    </>
  );
}
