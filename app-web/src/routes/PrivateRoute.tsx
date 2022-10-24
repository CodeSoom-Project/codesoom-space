import { FC, ReactElement } from 'react';

import { Navigate } from 'react-router-dom';

interface Props {
  accessToken: string | null;
  children: ReactElement;
}

const PrivateRoute: FC<Props> = ({ accessToken, children }) => {
  if (!accessToken) {
    return <Navigate to='/login' replace/>;
  }

  return children;
};

export default PrivateRoute;
