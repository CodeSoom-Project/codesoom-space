import {useForm} from "react-hook-form";
import {useMutation} from "react-query";
import {loginUserFn} from "./api";
import SignIn from "./signIn";
import Header from "./components/Header";
import {setAccessToken} from "./authSlice";
import {useAppDispatch} from "./hooks";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";

export default function SignInContainer() {
  const dispatch = useAppDispatch();
  const accessToken = useSelector((state: any) => state.accessToken)

  const {register, formState: {errors}, handleSubmit} = useForm();

  const navigate = useNavigate();

  const loginMutate = async ({email, password}: { email: any, password: string }) => {
    const accessToken = await loginUserFn({email, password})
    return accessToken
  }

  const {isLoading, error, isError, mutate, data} = useMutation('login', loginMutate, {
    onSuccess: async () => {
      //TODO: LocalStorage 에 토큰 저장 ..
      dispatch(setAccessToken(loginMutate))
      localStorage.setItem('accessToken', accessToken)
      console.log("login 성공")
      navigate("/", {replace: true})
    },
    onError: async (e) => {
      console.error(e);
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
