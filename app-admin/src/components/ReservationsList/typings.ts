interface User {
  id: number;
  name: string;
}

export interface Reservation {
  id: number;
  date: string;
  content: string;
  status: string;
  user: User;
}

interface Column {
  id: number;
  label: string;
}

export interface Pagination {
  page: number;
  size: number;
  totalPages: number;
}

export interface Props {
  reservations: Reservation[];
  columns: Column[];
  pagination: Pagination;
  onChange: (e: React.ChangeEvent<unknown>, value: number) => void;
  onClick: React.ReactEventHandler
}
