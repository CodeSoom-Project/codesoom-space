import styled from '@emotion/styled';

import {
  TableHead,
  TableRow,
  TableCell,
  Paper,
  TableContainer,
  Table,
  TableBody,
  Button,
} from '@mui/material';

import { Props } from './typings';

const Wrapper = styled.div({
  width: '100%',
});

const StyledPaper = styled(Paper)({
  width: '1200px',
  margin: 'auto',
  marginTop: '4rem',
});

export default function ReservationsList({ reservations, columns }: Props) {
  return (
    <Wrapper>
      <StyledPaper>
        <TableContainer>
          <Table stickyHeader>
            <TableHead>
              <TableRow>
                {columns.map(({ label, id }) => (
                  <TableCell align="center" key={id}>
                    {label}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {reservations.map(({ id, date, status, user: { name } }) => (
                <TableRow key={id}>
                  <TableCell align="center">{name}</TableCell>
                  <TableCell align="center">{date}</TableCell>
                  <TableCell align="center">
                    <Button>상세보기</Button>
                  </TableCell>
                  <TableCell align="center">{status}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </StyledPaper>
    </Wrapper>
  );
}
