import LoginForm from '../../components/LoginForm';

import { useSelector } from 'react-redux';

import {
  requestLogin,
  changeLoginField,
} from '../../redux/authSlice';


import { RootState } from '../../store';
import { useAppDispatch } from '../../hooks';
import { useNavigate } from 'react-router-dom';

const LoginFormContainer = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const { loginFields: { email, password } } = useSelector((state: RootState) => state.auth);

  const onChange = ({ name, value }: { name: string, value: string }) => {
    dispatch(changeLoginField({ name, value }));
  };

  const handleSubmit: React.MouseEventHandler<HTMLButtonElement> = async () => {
    await dispatch(requestLogin());

    navigate('reservations');
  };

  return (
    <LoginForm
      fields={{ email, password }}
      handleSubmit={handleSubmit}
      onChange={onChange}
    />
  );
};

export default LoginFormContainer;
