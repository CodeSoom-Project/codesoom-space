import { useForm } from 'react-hook-form';
import Button from './components/Button';

interface FieldsProps {
  fields: {
    seatNumber: number;
    userName: string;
    checkIn: string;
    checkOut: string;
  }
}

export default function ReservationForm({ fields, onChange, onSubmit }: any) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  console.log(errors);

  const {
    seatNumber,
    userName,
    checkIn,
    checkOut,
  } = fields;

  function handleChange(event: any) {
    const { target: { name, value } } = event;
    onChange({ name, value });
  }

  return (
    <section>
      <form
        onSubmit={handleSubmit((data) => {
          console.log(data);
        })}
      >
        <div>
          <label htmlFor="register-seatNumber">
            좌석 번호
          </label>
        </div>
        <input
          {...register('seatNumber', { required: '좌석 번호를 입력해 주세요' })}
          placeholder="좌석 번호"
          name="seatNumber"
          value={seatNumber}
          onChange={handleChange}
        />

        <div>
          <label htmlFor="register-name">
            이름
          </label>
        </div>
        <input
          {...register('userName', { required: '이름을 입력해 주세요' })}
          placeholder="이름"
          name="userName"
          value={userName}
          onChange={handleChange}
        />

        {/*<div>*/}
        {/*  <label htmlFor="register-checkIn">*/}
        {/*    시작 시간*/}
        {/*  </label>*/}
        {/*</div>*/}
        {/*<input*/}
        {/*  {...register('checkIn', { required: '예약 시작 시간을 입력해 주세요' })}*/}
        {/*  placeholder="예약 시작 시간(hh:mm)"*/}
        {/*  name="checkIn"*/}
        {/*  value={checkIn}*/}
        {/*  onChange={handleChange}*/}
        {/*/>*/}

        {/*<div>*/}
        {/*  <label htmlFor="register-checkOut">*/}
        {/*    종료 시간*/}
        {/*  </label>*/}
        {/*</div>*/}
        {/*<input*/}
        {/*  {...register('checkOut', { required: '이용 종료 시간을 입력해 주세요' })}*/}
        {/*  placeholder="이용 종료 시간(hh:mm)"*/}
        {/*  name="checkOut"*/}
        {/*  value={checkOut}*/}
        {/*  onChange={handleChange}*/}
        {/*/>*/}

        <Button
          onClick={onSubmit}
        >
          예약하기
        </Button>
      </form>
    </section>
  );
}
