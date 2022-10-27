import ReservationsList from '../../components/ReservationsList';

import { reservations } from '../../fixtures/reservations';

import columns from '../../data/columns';

export default function ReserVationsContainer() {
  return <ReservationsList reservations={reservations} columns={columns}/>;
}
