import styled from '@emotion/styled';

import { useState } from 'react';

import { changeReservationDetailsSeatNumber } from './ReservationSlice';

import { useAppDispatch } from './hooks';

import { useQuery } from 'react-query';

import { getSeats } from './services/api';

import SeatDetailModalContainer from './seatDetailModalContainer';

import Seats from './seats';

const Div = styled.div `  
  display: grid;
  gap: 1em;
  grid-template-columns: 1fr 1fr 1fr 1fr;
`;

export default function SeatsContainer() {
  const dispatch = useAppDispatch();

  const [isOpen, setIsOpen] = useState(false);

  const { data : seats } = useQuery(['getSeat'], getSeats, {
    refetchOnWindowFocus: false,
    retry: 1,
  });

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
        <Seats
          seats={seats}
          onClick={handleClick}
        />
      </Div>

      <SeatDetailModalContainer
        open={isOpen}
        onClose={handleClose}
      />
    </div>
  );
}
