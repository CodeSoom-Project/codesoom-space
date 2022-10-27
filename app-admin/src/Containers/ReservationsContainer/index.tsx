import ReservationsList from '../../components/ReservationsList';

import { reservations } from '../../fixtures/reservations';

import { column } from '../../data/column';

export default function ReserVationsContainer() {
  return <ReservationsList reservations={reservations} column={column}/>;
}
