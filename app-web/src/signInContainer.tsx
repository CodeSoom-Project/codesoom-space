import {useForm} from "react-hook-form";
import {useMutation} from "react-query";
import {loginUserFn} from "./api";
import SignIn from "./signIn";
import Header from "./components/Header";

export default function SignInContainer() {
  const {register, formState: {errors}, handleSubmit} = useForm();

  const loginMutate = async ({email, password}: { email: any, password: string }) => {
    const loginResult = await loginUserFn({email, password})
    return loginResult
  }

  const {isLoading, error, isError, mutate, data} = useMutation('login', loginMutate);
  console.log("data", data);
  console.log(error);

  return (
    <>
      <Header/>
      <SignIn
        register={register}
        errors={errors}
        handleSubmit={handleSubmit}
        error={error}
        mutate={mutate}
      />
    </>
  )
}
