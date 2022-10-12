
import styled from '@emotion/styled';

import { Button } from '@mui/material';

import ReservationsTable from '../components/reservations/ReservationsTable';

const Container = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',
  width: '100%',
  padding: '0 4rem',
});

const Header = styled.div({
  display: 'flex',
  justifyContent: 'space-between',
  width: '100%',
  marginBottom: '1rem',
});

const Title = styled.h1({
  fontSize: '2rem',
  margin: '0',
});

export default function Reservations() {
  return (
    <Container>
      <Header>
        <Title>예약내역</Title>
        <Button style={{ fontSize: '2rem' }}>예약하기</Button>
      </Header>
      <ReservationsTable/>
    </Container>
  );
}
