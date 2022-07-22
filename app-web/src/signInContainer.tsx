import {useForm} from "react-hook-form";
import {useMutation} from "react-query";
import {loginUser} from "./api";
import SignIn from "./signIn";

export default function SignInContainer() {
  const {register, formState: {errors}, handleSubmit} = useForm();

  const {isLoading, error, isError, mutateAsync, data} = useMutation('login', loginUser);
  console.log("data", data);
  console.log(error);

  return (
    <SignIn
      register={register}
      errors={errors}
      handleSubmit={handleSubmit}
      error={error}
      mutateAsync={mutateAsync}
    />
  )
}
