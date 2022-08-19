import { useQuery } from 'react-query';
import { getSeatList } from './services/api';
import { useState } from 'react';
import { useAppDispatch } from './hooks';
import { changeReservationDetailsSeatNumber } from './ReservationSlice';
import Button from './components/Button';
import styled from '@emotion/styled';
import SeatDetailModalContainer from './seatDetailModalContainer';

const Div = styled.div `  
  display: grid;
  gap: 1em;
  grid-template-columns: 1fr 1fr 1fr 1fr;
`;

export default function SeatList() {
  const dispatch = useAppDispatch();

  const [isOpen, setIsOpen] = useState(false);

  const { data : seatList } = useQuery(['getSeatList'], getSeatList);

  const handleChange = (seatNumber:any) => {
    dispatch(changeReservationDetailsSeatNumber({ seatNumber }));
  };

  const handleOpen = () => {
    setIsOpen(true);
  };

  const handleClose = () => {
    setIsOpen(false);
  };

  const handleClick = (seatNumber:any) => {
    handleChange(seatNumber);
    handleOpen();
  };

  return (
    <div>
      <Div>
        {seatList?.data?.map(seat => (
            <Button onClick={() =>handleClick(seat.seatNumber) }>
              <div key={seat.seatNumber}>
                <p>{seat.seatNumber}</p>
                <p>{seat.userName}</p>
              </div>
            </Button>
        ))}
      </Div>

      <SeatDetailModalContainer
        open={isOpen}
        onClose={handleClose}
      />
    </div>
  );
}
