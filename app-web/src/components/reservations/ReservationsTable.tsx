import * as React from 'react';

import { saveIsDetailRetrospectives } from '../../redux/retrospectivesSlice';

import { saveIsDetail, selectReservationId } from '../../redux/reservationsSlice';

import { useAppDispatch } from '../../hooks';

import { styled } from '@mui/material/styles';

import {
  Box,
  Button,
  IconButton,
  Paper,
  Table,
  TableBody,
  TableContainer,
  TableFooter,
  TableHead,
  TablePagination,
  TableRow,
} from '@mui/material';

import TableCell, { tableCellClasses } from '@mui/material/TableCell';

import FirstPageIcon from '@mui/icons-material/FirstPage';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';

import { useMutation } from 'react-query';

import { cancelReservation } from '../../services/reservations';

interface TablePaginationActionsProps {
  count: number;
  page: number;
  rowsPerPage: number;
  onPageChange: (
    event: React.MouseEvent<HTMLButtonElement>,
    newPage: number,
  )=> void;
}

const StyledTableCell = styled(TableCell)({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: '#1976d2',
    color: '#ffff',
  },
});

function TablePaginationActions(props: TablePaginationActionsProps) {
  const { count, page, rowsPerPage, onPageChange } = props;

  const LastPage = Math.ceil(count / rowsPerPage) - 1;

  const handleFirstPageButtonClick = (event: React.MouseEvent<HTMLButtonElement>): void => {
    onPageChange(event, 0);
  };

  const handleBackButtonClick = (event: React.MouseEvent<HTMLButtonElement>): void => {
    onPageChange(event, page - 1);
  };

  const handleNextButtonClick = (event: React.MouseEvent<HTMLButtonElement>): void => {
    onPageChange(event, page + 1);
  };

  const handleLastPageButtonClick = (event: React.MouseEvent<HTMLButtonElement>): void => {
    onPageChange(event, LastPage);
  };

  return (
    <Box sx={{ flexShrink: 0, ml: 5 }}>
      <IconButton
        onClick={handleFirstPageButtonClick}
        disabled={page === 0}
      >
        <FirstPageIcon />
      </IconButton>
      <IconButton
        onClick={handleBackButtonClick}
        disabled={page === 0}
      >
        <KeyboardArrowLeft />
      </IconButton>
      <IconButton
        onClick={handleNextButtonClick}
        disabled={page >= LastPage}
      >
        <KeyboardArrowRight />
      </IconButton>
      <IconButton
        onClick={handleLastPageButtonClick}
        disabled={page >= LastPage}
      >
        <LastPageIcon />
      </IconButton>
    </Box>
  );
}

interface Props {
  onOpenReservationModal: React.ReactEventHandler,
  onOpenRetrospectModal: React.ReactEventHandler
  isLoading: boolean,
  isError: boolean,
  reservations: {
    id: number,
    date: string,
    content: string,
    status: string
  }[]
}

interface Reservations {
  id: number;
  date: string;
  content: string;
  status: string;
}

type StatusType = {
  [key: string]: string;
};

export default function ReservationsTable({
  onOpenReservationModal,
  onOpenRetrospectModal,
  isLoading,
  isError,
  reservations,
}: Props) {
  const dispatch = useAppDispatch();
  const [page, setPage] = React.useState(0);

  const rowsPerPage = 10;

  const startRow = page * rowsPerPage;
  const endRow = page * rowsPerPage + rowsPerPage;

  const { mutate: cancelReservationMutate } = useMutation(cancelReservation, {
    onSuccess: () => {
      alert('예약이 취소되셨습니다.');
    },
    onError: () => {
      alert('예약 취소에 실패하였습니다. 다시 신청해주세요.');
    },
  });

  const onClickCancelReservation = (id: number) => {
    if (confirm('예약을 취소하시겠습니까?')) {
      cancelReservationMutate(id);
    }
  };

  const handleChangePage = (
    event: React.MouseEvent<HTMLButtonElement> | null,
    newPage: number,
  ): void => {
    setPage(newPage);
  };

  const handleClickReservation = (e: React.MouseEvent<HTMLButtonElement>, id: number) => {
    onOpenReservationModal(e);

    dispatch(selectReservationId(id));
  };

  const handleClickRetrospective = (
    e: React.MouseEvent<HTMLButtonElement>,
    id: number,
    status: string,
  ) => {
    onOpenRetrospectModal(e);

    dispatch(selectReservationId(id));

    if (status === 'RETROSPECTIVE_COMPLETE') {
      dispatch(saveIsDetailRetrospectives(true));
    }

    if (status === 'RETROSPECTIVE_WAITING') {
      dispatch(saveIsDetailRetrospectives(false));
    }
  };

  const statusName: StatusType = {
    'RESERVED': '예약완료',
    'CANCELED': '취소',
    'RETROSPECTIVE_COMPLETE': '회고 상세보기',
    'RETROSPECTIVE_WAITING': '회고 작성',
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return <div>Error</div>;
  }

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            <StyledTableCell align="center">계획 일자</StyledTableCell>
            <StyledTableCell align="center">계획 내용</StyledTableCell>
            <StyledTableCell align="center">회고 상세보기</StyledTableCell>
            <StyledTableCell align="center">계획 상세보기</StyledTableCell>
            <StyledTableCell align="center">예약 취소</StyledTableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {reservations.slice(startRow, endRow).map(({ id, date, content, status }: Reservations) => (
            <TableRow key={id}>
              <TableCell align="center">{date}</TableCell>
              <TableCell align="left">{content}</TableCell>
              <TableCell align="center">
                {status === 'CANCELED' ? (
                  <div>
                    {statusName[status]}
                  </div>
                ) : (
                  <Button onClick={(e) => {
                    handleClickRetrospective(e, id, status);
                  }}>
                    {statusName[status]}
                  </Button>
                )}
              </TableCell>
              <TableCell align="center">
                {status === 'CANCELED' ? (
                  <div>
                    상세보기
                  </div>
                ) : (
                  <Button
                    onClick={(e: React.MouseEvent<HTMLButtonElement>) => {
                      handleClickReservation(e, id);
                      dispatch(saveIsDetail(true));
                    }}>
                    상세보기
                  </Button>
                )}
              </TableCell>
              <TableCell align="center">
                {status === 'CANCELED' ? (
                  <div>
                    예약이 취소되었습니다.
                  </div>
                ) : (
                  <Button onClick={() => onClickCancelReservation(id)}>
                    예약취소
                  </Button>
                )}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>

        <TableFooter>
          <TableRow>
            <TablePagination
              labelRowsPerPage=""
              rowsPerPageOptions={[10]}
              count={reservations.length}
              rowsPerPage={rowsPerPage}
              page={page}
              onPageChange={handleChangePage}
              ActionsComponent={TablePaginationActions}
            />
          </TableRow>
        </TableFooter>
      </Table>
    </TableContainer>
  );
}
