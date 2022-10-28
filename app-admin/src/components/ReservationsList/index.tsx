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
  TableFooter,
  Pagination,
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

const ContentRow = styled(TableRow)(({ backgroundcolor, color }: { backgroundcolor?: string, color?: string }) => ({
  backgroundColor: backgroundcolor ?? '#FFFFFF',

  'td, button': {
    color: color ?? '#000000',
  },
}));

export default function ReservationsList({ reservations, columns, pagination, onChange }: Props) {
  const { totalPages } = pagination;

  const statuses: { [key: string]: { text: string, backgroundColor?: string, color?: string }; } = {
    'RESERVED': { text: '예약 완료' },
    'CANCELED': { text: '예약 취소', backgroundColor: '#D5D5D5' },
    'RETROSPECTIVE_WAITING': { text: '회고작성 전' },
    'RETROSPECTIVE_COMPLETE': { text: '회고작성 완료', backgroundColor: '#10B5E8', color: '#FFFFFF' },
  };

  return (
    <Wrapper>
      <StyledPaper>
        <TableContainer>
          <Table stickyHeader>
            <TableHead>
              <TableRow>
                {columns.map(({ label, id }) => (
                  <TableCell
                    key={id}
                    sx={{ backgroundColor: '#1976d2', color: '#FFFFFF' }}
                    align="center"
                  >
                    {label}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>

            <TableBody>
              {reservations.map(({ id, date, status, user: { name } }) => {
                const { text, backgroundColor, color } = statuses[status];

                const waiting = status === 'RETROSPECTIVE_WAITING';
                const canceled = status === 'CANCELED';

                return (
                  <ContentRow
                    key={id}
                    backgroundcolor={backgroundColor}
                    color={color}
                  >
                    <TableCell align="center">{name}</TableCell>
                    <TableCell align="center">{date}</TableCell>
                    <TableCell align="center">
                      <Button disabled={canceled} >상세보기</Button>
                    </TableCell>
                    <TableCell align="center">
                      <Button disabled={waiting || canceled}>
                        {text}
                      </Button>
                    </TableCell>
                  </ContentRow>
                );
              })}
            </TableBody>

            <TableFooter>
              <TableRow>
                <TableCell align="center">
                  <Pagination count={totalPages} onChange={onChange} color="primary" />
                </TableCell>
              </TableRow>
            </TableFooter>
          </Table>
        </TableContainer>
      </StyledPaper>
    </Wrapper>
  );
}
