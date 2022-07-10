import axios from "axios";
import {useAppDispatch, useAppSelector} from "./hooks";
import ReservationForm from "./ReservationForm";
import {changeReservationFields} from "./ReservationSlice";

interface ReservationFields {
  seatNumber: number;
  name: string,
  checkIn: string,
  checkOut: string,
}

interface Form {
  name: string;
  value: string | number;
}

function ReservationFormContainer() {

  const dispatch = useAppDispatch()

  const reservationFields = useAppSelector((state) => state.reservation.reservationFields)
  const {
    seatNumber,
    name,
    checkIn,
    checkOut
  }:ReservationFields = reservationFields

  const handleChange = ({ name, value }:any) => {
    dispatch(changeReservationFields({ name, value }));
  }

  const handleSubmit = async(e:any) => {
      e.preventDefault();

      await axios.post('http://localhost:8080/seat/' + seatNumber,  {
          name,
          checkIn,
          checkOut
      })
          .then((response) => {
              if(response.status === 201) {
                  alert('예약 완료');
              }
          })
          .catch((error) => {
              console.log(error.response);

              if(error.response.status === 400) {
                  alert('이미 예약된 좌석입니다');
              } else if(error.response.status === 404) {
                  alert('존재하지 않는 좌석입니다');
              }
          });
  console.log('함수동작완료')
  }

  return (
    <ReservationForm
      fields={reservationFields}
      onChange={handleChange}
      onSubmit={handleSubmit}
    />
  );
}

export default ReservationFormContainer;
