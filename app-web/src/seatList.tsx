import { useQuery } from 'react-query';
import { getSeatList } from './services/api';
import { useState } from 'react';
import { useAppDispatch } from './hooks';
import { changeReservationDetailsSeatNumber } from './ReservationSlice';
import Button from './components/Button';
import styled from '@emotion/styled';
import SeatDetailsModalContainer from './seatDetailsModalContainer';

const Div = styled.div `  
  display: grid;
  gap: 1em;
  grid-template-columns: 1fr 1fr 1fr 1fr;
`;

export default function SeatList() {
  const dispatch = useAppDispatch();

  const [isOpen, setIsOpen] = useState(false);

  const { data : seatList } = useQuery(['getSeatList'], getSeatList);

  const handleChange = () => {
    dispatch(changeReservationDetailsSeatNumber);
  };

  const handleOpen = () => {
    setIsOpen(true);
  };

  const handleClose = () => {
    setIsOpen(false);
  };

  const handleClick = () => {
    handleChange();
    handleOpen();
  };

  return (
    <div>
      <Div>
        {seatList?.data?.map(seat => (
            <Button onClick={handleClick}>
              <div key={seat.seatNumber}>
                <p>{seat.seatNumber}</p>
                <p>{seat.userName}</p>
              </div>
            </Button>
        ))}
      </Div>

      <SeatDetailsModalContainer
        open={isOpen}
        onClose={handleClose}
      />

    </div>
  );
}
