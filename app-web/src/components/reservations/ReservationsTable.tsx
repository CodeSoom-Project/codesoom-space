import * as React from 'react';
import { styled, useTheme } from '@mui/material/styles';

import { Box, Table, TableBody,  TableContainer, TableFooter, TablePagination, TableRow, Paper, IconButton, Button, TableHead } from '@mui/material';
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

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

function TablePaginationActions(props: TablePaginationActionsProps) {
  const theme = useTheme();
  const { count, page, rowsPerPage, onPageChange } = props;

  const handleFirstPageButtonClick = (
    event: React.MouseEvent<HTMLButtonElement>,
  ) => {
    onPageChange(event, 0);
  };

  const handleBackButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    onPageChange(event, page - 1);
  };

  const handleNextButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    onPageChange(event, page + 1);
  };

  const handleLastPageButtonClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    onPageChange(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
  };

  return (
    <Box sx={{ flexShrink: 0, ml: 2.5 }}>
      <IconButton
        onClick={handleFirstPageButtonClick}
        disabled={page === 0}
        aria-label="first page"
      >
        {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
      </IconButton>
      <IconButton
        onClick={handleBackButtonClick}
        disabled={page === 0}
        aria-label="previous page"
      >
        {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
      </IconButton>
      <IconButton
        onClick={handleNextButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="next page"
      >
        {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
      </IconButton>
      <IconButton
        onClick={handleLastPageButtonClick}
        disabled={page >= Math.ceil(count / rowsPerPage) - 1}
        aria-label="last page"
      >
        {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
      </IconButton>
    </Box>
  );
}

function createData(date: string, plan:string, status: string) {
  return { date, plan, status };
}

const rows = [
  createData('2022-10-11', 'git', 'RETROSPECTIVE_COMPLETE' ),
  createData('2022-10-11', 'react 공부',  'RETROSPECTIVE_COMPLETE'),
  createData('2022-10-11', 'spring 공부', 'RETROSPECTIVE_COMPLETE'),
  createData('2022-10-11', 'db 공부', 'RETROSPECTIVE_WAITING'),
  createData('2022-10-11', 'javascript 공부', 'RETROSPECTIVE_COMPLETE'),
  createData('2022-10-11', 'git', 'RETROSPECTIVE_COMPLETE' ),
  createData('2022-10-11', 'react 공부',  'RETROSPECTIVE_COMPLETE'),
  createData('2022-10-11', 'spring 공부', 'RETROSPECTIVE_COMPLETE'),
  createData('2022-10-11', 'db 공부', 'RETROSPECTIVE_WAITING'),
  createData('2022-10-11', 'javascript 공부', 'RETROSPECTIVE_COMPLETE'),
];

export default function ReservationsTable() {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  // Avoid a layout jump when reaching the last page with empty rows.
  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.length) : 0;

  const handleChangePage = (
    event: React.MouseEvent<HTMLButtonElement> | null,
    newPage: number,
  ) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
  ) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 500 }} aria-label="custom pagination table">
      <TableHead>
          <TableRow>
            <StyledTableCell>계획 일자</StyledTableCell>
            <StyledTableCell align="right">계획 내용</StyledTableCell>
            <StyledTableCell align="right">회고 상세보기</StyledTableCell>
            <StyledTableCell align="right">계획 상세보기</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {(rowsPerPage > 0
            ? rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
            : rows
          ).map(({ date, plan, status }) => (
            <TableRow key={date}>
              <TableCell component="th" scope="row">
                {date}
              </TableCell>
              <TableCell style={{ width: 160 }} align="right">
                {plan}
              </TableCell>
              <TableCell style={{ width: 160 }} align="right">
                <Button>
                  {status === 'RETROSPECTIVE_COMPLETE' ? '회고제출' : '제출됨'}
                </Button>
              </TableCell>
              <TableCell style={{ width: 160 }} align="right">
                <Button>
                  상세보기
                </Button>
              </TableCell>
            </TableRow>
          ))}
          {emptyRows > 0 && (
            <TableRow style={{ height: 53 * emptyRows }}>
              <TableCell colSpan={6} />
            </TableRow>
          )}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TablePagination
              rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
              colSpan={3}
              count={rows.length}
              rowsPerPage={rowsPerPage}
              page={page}
              SelectProps={{
                inputProps: {
                  'aria-label': 'rows per page',
                },
                native: true,
              }}
              onPageChange={handleChangePage}
              onRowsPerPageChange={handleChangeRowsPerPage}
              ActionsComponent={TablePaginationActions}
            />
          </TableRow>
        </TableFooter>
      </Table>
    </TableContainer>
  );
}