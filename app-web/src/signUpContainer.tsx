import Header from './components/Header';
import { useForm } from 'react-hook-form';
import { useMutation } from 'react-query';
import SignUp from './signUp';
import { signUpUserFn } from './api';
import { useNavigate } from 'react-router-dom';
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';

export default function SignUpContainer() {

  const schema = yup.object().shape({
    passwordCheck: yup
      .string()
      .oneOf([yup.ref('password'), '비밀번호가 일치하지않습니다'])
      .required(),
  });

  const { register, formState: { errors }, handleSubmit, watch, setError } = useForm({
    resolver: yupResolver(schema),
  });
  const navigate = useNavigate();


  const signUpMutate = async ({
    email,
    password,
    name,
  }: { email: any, password: string, name: any }) => {
    const signUpResult = await signUpUserFn({ email, password, name });
    return signUpResult;
  };

  const { isLoading, error, isError, mutate, data } = useMutation('signup', signUpMutate, {
    onSuccess: async () => {
      console.log('회원가입 성공');
      alert('회원가입 되었습니다');
      navigate('/signin', { replace: true });
    },
    onError: async (e) => {
      console.error(e);
    },
  });
  
  return (
    <>
      <Header/>
      <SignUp
        register={register}
        errors={errors}
        handleSubmit={handleSubmit}
        error={error}
        mutate={mutate}
        watch={watch}
        setError={setError}
      />
    </>
  );
}
