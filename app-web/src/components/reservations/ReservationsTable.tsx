import * as React from 'react';

import { styled } from '@mui/material/styles';

import {
  Box,
  Table,
  TableContainer,
  TablePagination,
  TableRow,
  Paper,
  IconButton,
  Button,
  TableHead,
  TableBody,
  TableFooter,
} from '@mui/material';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';

import { selectResetRetrospectiveId } from '../../redux/retrospectivesSlice';
import { selectReservationId } from '../../redux/reservationsSlice';

import FirstPageIcon from '@mui/icons-material/FirstPage';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';
import { useAppDispatch } from '../../hooks';

interface TablePaginationActionsProps {
  count: number;
  page: number;
  rowsPerPage: number;
  onPageChange: (
    event: React.MouseEvent<HTMLButtonElement>,
    newPage: number,
  ) => void;
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
<<<<<<< HEAD
  onOpenReservationModal : React.ReactEventHandler,
  onOpenRetrospectModal: React.ReactEventHandler
  isLoading: boolean,
  isError: boolean,
  reservations: {
    id: number,
    date: string,
    content: string,
    status:string
  }[]
=======
  onOpenReservationModal: React.ReactEventHandler,
  onOpenRetrospectModal: React.ReactEventHandler
>>>>>>> d5e16c0 (🎨 예약 모달과 회고 모달 디자인 통일 작업)
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

  const handleChangePage = (
    event: React.MouseEvent<HTMLButtonElement> | null,
    newPage: number,
  ): void => {
    setPage(newPage);
  };

  const handleClickReservation = (e: React.MouseEvent<HTMLButtonElement>, id:number) => {
    onOpenReservationModal(e);

    dispatch(selectReservationId(id));
  };

  const handleClickRetrospective = (e: React.MouseEvent<HTMLButtonElement>, id:number) => {
    onOpenRetrospectModal(e);

    dispatch(selectResetRetrospectiveId(id));
  };

  const statusName: StatusType = {
    'RESERVED': '예약완료',
    'CANCELED': '취소',
    'RETROSPECTIVE_COMPLETE': '회고 제출하기',
    'RETROSPECTIVE_WAITING': '회고 작성전',
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
            <StyledTableCell>계획 일자</StyledTableCell>
            <StyledTableCell align="left">계획 내용</StyledTableCell>
            <StyledTableCell align="right">회고 상세보기</StyledTableCell>
            <StyledTableCell align="right">계획 상세보기</StyledTableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {reservations.slice(startRow, endRow).map(({ id, date, content, status }:Reservations) => (
            <TableRow key={id}>
              <TableCell>{date}</TableCell>
              <TableCell align="left">{content}</TableCell>
              <TableCell align="right">
                <Button onClick={(e) => {
                  handleClickRetrospective(e, id);
                }}>
                  {statusName[status]}
                </Button>
              </TableCell>
              <TableCell align="right">
                <Button
                  onClick={(e: React.MouseEvent<HTMLButtonElement>) => {
                    handleClickReservation(e, id);
                  }}>
                  상세보기
                </Button>
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
