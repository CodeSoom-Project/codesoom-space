import SelectTime from './SelectTime';

export default function SelectTimeContainer({ seatNumber, onClick, onDelete, isMySeat }:any) {
  return (
    <>
      <SelectTime seatNumber={seatNumber} onClick={onClick} onDelete={onDelete} isMySeat={isMySeat}/>
    </>
  );
}
