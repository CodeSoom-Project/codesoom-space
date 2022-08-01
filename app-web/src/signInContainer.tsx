import {useForm} from "react-hook-form";
import {useMutation} from "react-query";
import {loginUserFn} from "./api";
import SignIn from "./signIn";
import Header from "./components/Header";
import {setAccessToken} from "./authSlice";
import {useAppDispatch} from "./hooks";

export default function SignInContainer() {
  const dispatch = useAppDispatch();

  const {register, formState: {errors}, handleSubmit} = useForm();

  const loginMutate = async ({email, password}: { email: any, password: string }) => {
    const accessToken = await loginUserFn({email, password})
    return accessToken
    // accessToken말고 지금 data를 넘기고 있을 수 있음 accessToken을 꺼내오자.아마도?
  }

  const {isLoading, error, isError, mutate, data} = useMutation('login', loginMutate, {
    onSuccess: async () => {
      dispatch(setAccessToken(loginMutate))
      console.log("login 성공")
    }
  });

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
