import Header from './components/Header';
import {useForm} from "react-hook-form";
import {useMutation} from "react-query";
import {signupUser} from "./api";
import SignUp from "./signUp";

export default function SignUpContainer() {
  const {register, formState: {errors}, handleSubmit} = useForm();

  const {isLoading, error, isError, mutateAsync, data} = useMutation('signup', signupUser);
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
        mutateAsync={mutateAsync}
      />
    </>
  )
}
