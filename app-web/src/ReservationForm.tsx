import { useForm } from 'react-hook-form';
import React from "react";

interface FieldsProps {
  fields: {
    seatNumber : number;
    name : string;
    checkIn: string;
    checkOut: string;
  }
}

function ReservationForm({ fields , onChange, onSubmit}: any) {
    const {
      register,
      handleSubmit,
      formState: { errors }
    } = useForm();

    console.log(errors);

    const {
      seatNumber,
      name,
      checkIn,
      checkOut
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

            <label htmlFor="register-seatNumber">
              좌석 번호
            </label>
            <input
              {...register("seatNumber", { required: '좌석 번호를 입력해 주세요'})}
              placeholder="좌석 번호"
              name="seatNumber"
              value={seatNumber}
              onChange={handleChange}
            />

            <label htmlFor="register-name">
              이름
            </label>
            <input
              {...register("name",{ required: '이름을 입력해 주세요'})}
              placeholder="이름"
              name="name"
              value={name}
              onChange={handleChange}
            />

            <label htmlFor="register-checkIn">
              시작 시간
            </label>
            <input
              {...register("checkIn",{ required: '예약 시작 시간을 입력해 주세요'})}
              placeholder="예약 시작 시간(hh:mm)"
              name="checkIn"
              value={checkIn}
              onChange={handleChange}
            />

            <label htmlFor="register-checkOut">
              종료 시간
            </label>
            <input
              {...register("checkOut",{ required: '이용 종료 시간을 입력해 주세요'})}
              placeholder="이용 종료 시간(hh:mm)"
              name="checkOut"
              value={checkOut}
              onChange={handleChange}
            />

            <button
              type="button"
              onClick={onSubmit}
            >
              예약하기
            </button>
          </form>
        </section>
    );
}

export default ReservationForm;
