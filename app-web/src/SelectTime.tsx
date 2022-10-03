import 'react-datepicker/dist/react-datepicker.css';

import Button from './components/Button';

const SelectTime = ({ seatNumber, onClick, onDelete }:any) => {
  // const [startDate, setStartDate] = useState(new Date());
  // const [endDate, setEndDate] = useState(new Date());

  return (
    <>
      {/*예약 시작 시간*/}
      {/*{' : '}*/}
      {/*<DatePicker*/}
      {/*  selected={startDate}*/}
      {/*  onChange={(date) => setStartDate(date)}*/}
      {/*  showTimeSelect*/}
      {/*  showTimeSelectOnly*/}
      {/*  timeIntervals={30}*/}
      {/*  timeCaption="Time"*/}
      {/*  dateFormat="h:mm aa"*/}
      {/*/>*/}

      {/*예약 종료 시간*/}
      {/*{' : '}*/}
      {/*<DatePicker*/}
      {/*  selected={endDate}*/}
      {/*  onChange={(date) => setEndDate(date)}*/}
      {/*  showTimeSelect*/}
      {/*  showTimeSelectOnly*/}
      {/*  timeIntervals={30}*/}
      {/*  timeCaption="Time"*/}
      {/*  dateFormat="h:mm aa"*/}
      {/*/>*/}

      {/*<Button onClick={()=>onClick({ seatNumber, checkIn:startDate, checkOut:endDate })}>예약하기</Button>*/}
      <Button onClick={()=>onClick({ seatNumber })}>예약하기</Button>
      <Button onClick={()=>onDelete({ seatNumber })}>예약취소</Button>
    </>


  );
};

export default SelectTime;
