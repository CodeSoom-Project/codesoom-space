import Button from './components/Button';

export default function Seats({ seats, onClick }:any) {
  return (
    <>
      {seats?.data?.map(seat => (
        <div key={seat.seatNumber}>
          <Button onClick={() =>onClick(seat.seatNumber) }>
            <p>{seat.seatNumber}</p>
            <p>{seat.isReserved}</p>
          </Button>
        </div>
      ))}
    </>
  );
}

