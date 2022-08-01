import Header from './components/Header';
import {useForm} from "react-hook-form";
import {useMutation} from "react-query";
import SignUp from "./signUp";
import {signUpUserFn} from "./api";

export default function SignUpContainer() {
  const {register, formState: {errors}, handleSubmit} = useForm();

  const signUpMutate = async ({
                                email,
                                password,
                                name
                              }: { email: any, password: string, passwordCheck: any, name: any }) => {
    const signUpResult = await signUpUserFn({email, password, name})
    return signUpResult
  }

  const {isLoading, error, isError, mutate, data} = useMutation('signup', signUpMutate, {
    onSuccess: async () => {
      console.log("회원가입 성공")
    }
  });
  console.log("data", data);
  console.log(error);


  return (
    <>
      <Header/>
      <SignUp
        register={register}
        errors={errors}
        handleSubmit={handleSubmit}
        error={error}
        mutate={mutate}
      />
    </>
  )
}
