import axios from "axios";
import {useAppDispatch, useAppSelector} from "./hooks";
import ReservationForm from "./ReservationForm";
import {changeReservationFields} from "./ReservationSlice";

function ReservationFormContainer() {

  const reservationFields = useAppSelector((state) => state.reservation.reservationFields)
  const {
    seatNumber,
    name,
    checkIn,
    checkOut
  } = reservationFields;

  const dispatch = useAppDispatch()

  const handleChange = ({ name, value }) => {
    dispatch(changeReservationFields({ name, value }));
  }

  const handleSubmit = () => {
    // Todo: axios + react-query
  }
  // const handleSubmit = async(e:any) => {
  //     e.preventDefault();
  //
  //     const body = {
  //         name: userName,
  //         checkIn: checkIn,
  //         checkOut: checkOut
  //     };
  //
  //     await axios.post('http://localhost:8080/seat/' + seatNumber,  {
  //         body,
  //     })
  //         .then((response) => {
  //             if(response.status === 201) {
  //                 alert('예약 완료');
  //             }
  //         })
  //         .catch((error) => {
  //             console.log(error.response);
  //
  //             if(error.response.status === 400) {
  //                 alert('이미 예약된 좌석입니다');
  //             } else if(error.response.status === 404) {
  //                 alert('존재하지 않는 좌석입니다');
  //             }
  //         });
  //
  // }

  return (
    <ReservationForm
      fields={reservationFields}
      onChange={handleChange}
      onSubmit={handleSubmit}
    />
  );
}

export default ReservationFormContainer;
