import { useEffect } from 'react';

import { styled } from '@mui/material/styles';

import { Box, CircularProgress } from '@mui/material';

import { useLocation, useNavigate } from 'react-router-dom';

import { useMutation } from 'react-query';

import { AxiosError } from 'axios';

import { verification } from '../../../services/verification';

import { useAppDispatch, useAppSelector } from '../../../hooks';

import { get } from '../../../utils';

import { saveError } from '../../../redux/verificationSlice';

import AlertDialog from '../../../components/verification/Dialog';

const StyledBox = styled(Box)({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  height: '50vh',
});

export default function EmailVerificationPage() {
  const dispatch = useAppDispatch();

  const { verificationError } = useAppSelector(get('verification'));

  const navigate = useNavigate();

  const location = useLocation();
  const queryString = location.search;

  const params = new URLSearchParams(queryString);
  const token = (params.get('token'));

  const handleClick = () => {
    navigate('/', { replace: true });
  };

  const { mutate: verificationMutate, isLoading, isSuccess, isError } = useMutation(
    'EmailVerification', verification,
    {
      onError: (error: AxiosError) => {
        dispatch(saveError(error.response?.data));
      },
    });

  useEffect(() => {
      verificationMutate(token);
  }, []);

  if (isLoading) {
    return (
      <StyledBox>
        <CircularProgress/>
      </StyledBox>
    );
  }

  if (isSuccess) {
    return (
      <AlertDialog
        title="이메일 인증 완료"
        message={`이메일 알림 인증을 완료했습니다.
             계획/회고 미작성시 이메일이 발송됩니다.`}
        onClick={handleClick}
      />
    );
  }

  if (isError) {
    return (
      <AlertDialog
        title="이메일 인증 실패"
        message={verificationError}
        onClick={handleClick}
      />
    );
  }

  return <></>;
}
