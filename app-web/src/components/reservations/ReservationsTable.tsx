import * as React from 'react';

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

const rows = [];

interface Props {
  onOpenReservationModal : React.ReactEventHandler,
  onOpenRetrospectModal : React.ReactEventHandler
}

export default function ReservationsTable({ onOpenReservationModal, onOpenRetrospectModal }:Props) {
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
