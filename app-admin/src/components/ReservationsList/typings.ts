interface User {
  id: number,
  name: string
}

interface Reservation {
  id: number,
  date: string,
  content: string,
  status: string,
  user: User
}

interface Column {
  id: number,
  label: string,
}

export interface Props {
  reservations: Reservation[],
  column: Column[]
}
