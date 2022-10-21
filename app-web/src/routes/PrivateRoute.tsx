import { FC, ReactElement } from 'react';

import { Navigate } from 'react-router-dom';

import { useSelector } from 'react-redux';
import { get } from '../utils';

interface Props {
  children: ReactElement;
}

const PrivateRoute: FC<Props> = ({ children }) => {
  const { accessToken } = useSelector(get('auth'));

  if (!accessToken) {
    return <Navigate to='/login' replace/>;
  }
  return children;
};

export default PrivateRoute;