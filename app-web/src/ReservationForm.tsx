import { useForm } from 'react-hook-form';

function ReservationForm() {
    const {
      register,
      handleSubmit,
      formState: { errors }
    } = useForm();

    console.log(errors);

    return (
        <section>
          <form
            onSubmit={handleSubmit((data) => {
                console.log(data);
              })}
            >
            <input
              {...register("seatNumber", { required: '좌석 번호를 입력해 주세요'})}
              placeholder="좌석 번호"
            />
            <input
              {...register("name",{ required: '이름을 입력해 주세요'})}
              placeholder="이름"
            />
            <input
              {...register("checkIn",{ required: '예약 시작 시간을 입력해 주세요'})}
              placeholder="예약 시작 시간(hh:mm)"
            />
            <input
              {...register("name",{ required: '이용 종료 시간을 입력해 주세요'})}
              placeholder="이용 종료 시간(hh:mm)"
            />
            <button
              type="button"
            >
              예약하기
            </button>
          </form>
        </section>
    );
}

export default ReservationForm;
