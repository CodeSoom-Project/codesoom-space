import { styled } from '@mui/material/styles';
import {
  Box,
  CircularProgress,
} from '@mui/material';

const StyledBox = styled(Box)({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
});

export default function Email() {
  return (
    <StyledBox>
      <CircularProgress />
    </StyledBox>
  );
}
