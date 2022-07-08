import { useFrom } from 'react-hook-form';

function ReservationForm() {
    const { register } = useFrom();
    return (
        <section>
          <form>
            <input {...register("seatNumber")} placeholder="좌석 번호" />
            <input {...register("name")} placeholder="이름" />
            <input {...register("checkIn")} placeholder="예약 시작 시간(hh:mm)" />
            <input {...register("name")} placeholder="이용 종료 시간(hh:mm)" />
          </form>
        </section>
    );
}

export default ReservationForm;
