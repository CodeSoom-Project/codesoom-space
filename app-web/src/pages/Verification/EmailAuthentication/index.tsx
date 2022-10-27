import { styled } from '@mui/material/styles';

import { Box, CircularProgress } from '@mui/material';

import AlertDialog from '../../../components/verification/Dialog';

const StyledBox = styled(Box)({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  height: '50vh',
});

export default function EmailAuthentication() {
  const isSuccess = false;
  const isError = true;

  if (isSuccess) {
    return (
      <AlertDialog
        title="이메일 인증 완료"
        message={`이메일 알림 인증을 완료했습니다.
             계획/회고 미작성시 이메일이 발송됩니다.`}
      />
    );
  }

  if (isError) {
    return (
      <AlertDialog
        title="이메일 인증 실패"
        message="서버에서 받은 메시지"
      />
    );
  }

  return (
    <StyledBox>
      <CircularProgress />
    </StyledBox>
  );
}
