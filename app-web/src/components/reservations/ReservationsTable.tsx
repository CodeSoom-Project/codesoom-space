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

import FirstPageIcon from '@mui/icons-material/FirstPage';
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight';
import LastPageIcon from '@mui/icons-material/LastPage';

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

  const handleFirstPageButtonClick = (event: React.MouseEvent<HTMLButtonElement>):void => {
    onPageChange(event, 0);
  };

  const handleBackButtonClick = (event: React.MouseEvent<HTMLButtonElement>):void => {
    onPageChange(event, page - 1);
  };

  const handleNextButtonClick = (event: React.MouseEvent<HTMLButtonElement>):void => {
    onPageChange(event, page + 1);
  };

  const handleLastPageButtonClick = (event: React.MouseEvent<HTMLButtonElement>):void => {
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

const enum Status {
  RETROSPECTIVE_COMPLETE = 'RETROSPECTIVE_COMPLETE',
  RETROSPECTIVE_WAITING = 'RETROSPECTIVE_WAITING',
}

function createData(id:number, date: string, plan:string, status:Status) {
  return { id, date, plan, status };
}

const rows = [
  createData(1, '2022-10-1', 'git', Status.RETROSPECTIVE_COMPLETE),
  createData(2, '2022-10-2', 'react 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(3, '2022-10-3', 'spring 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(4, '2022-10-4', 'db 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(5, '2022-10-5', 'javascript 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(6, '2022-10-6', 'git', Status.RETROSPECTIVE_COMPLETE),
  createData(7, '2022-10-7', 'react 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(8, '2022-10-8', 'spring 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(9, '2022-10-9', 'db 공부', Status.RETROSPECTIVE_WAITING),
  createData(10, '2022-10-10', 'javascript 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(11, '2022-10-11', 'git', Status.RETROSPECTIVE_COMPLETE),
  createData(12, '2022-10-12', 'react 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(13, '2022-10-13', 'spring 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(14, '2022-10-14', 'db 공부', Status.RETROSPECTIVE_WAITING),
  createData(15, '2022-10-15', 'javascript 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(16, '2022-10-16', 'git', Status.RETROSPECTIVE_WAITING),
  createData(17, '2022-10-17', 'react 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(18, '2022-10-18', 'spring 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(19, '2022-10-19', 'db 공부', Status.RETROSPECTIVE_WAITING),
  createData(20, '2022-10-20', 'javascript 공부', Status.RETROSPECTIVE_WAITING),
  createData(21, '2022-10-21', 'javascript 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(22, '2022-10-22', 'javascript 공부', Status.RETROSPECTIVE_COMPLETE),
  createData(23, '2022-10-23', 'javascript 공부', Status.RETROSPECTIVE_COMPLETE),
];

interface Props {
  onOpenReservationModal : React.ReactEventHandler,
  onOpenRetrospectModal : React.ReactEventHandler
}

export default function ReservationsTable({ onOpenReservationModal, onOpenRetrospectModal }: Props) {
  const [page, setPage] = React.useState(0);

  const rowsPerPage = 10;

  const startRow = page * rowsPerPage;
  const endRow = page * rowsPerPage + rowsPerPage;

  const handleChangePage = (
    event: React.MouseEvent<HTMLButtonElement> | null,
    newPage: number,
  ):void => {
    setPage(newPage);
  };

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
          {rows.slice(startRow, endRow).map(({ id, date, plan, status }) => (
            <TableRow key={id}>
              <TableCell>{date}</TableCell>
              <TableCell align="left">{plan}</TableCell>
              <TableCell align="right">
                <Button onClick={onOpenRetrospectModal}>
                  {status === 'RETROSPECTIVE_COMPLETE' ? '회고제출' : '제출됨'}
                </Button>
              </TableCell>
              <TableCell align="right">
                <Button onClick={onOpenReservationModal}>
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
              count={rows.length}
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
