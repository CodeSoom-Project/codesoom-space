import axios from "axios";
import {useState} from "react";

function ReservationForm() {
    const [seatNumber, setSeatNumber] = useState("");
    const [userName, setUserName] = useState("");
    const [checkIn, setCheckIn] = useState("");
    const [checkOut, setCheckOut] = useState("");

    const onChangeSeatNumber = (e:any) => {
        e.preventDefault();
        setSeatNumber(e.target.value);
    };
    const onChangeUserName = (e:any) => {
        e.preventDefault();
        setUserName(e.target.value);
    };
    const onChangeCheckIn = (e:any) => {
        e.preventDefault();
        setCheckIn(e.target.value);
    };
    const onChangeCheckOut = (e:any) => {
        e.preventDefault();
        setCheckOut(e.target.value);
    };

    const handleSubmit = async(e:any) => {
        e.preventDefault();

        const body = {
            name: userName,
            checkIn: checkIn,
            checkOut: checkOut
        };

        await axios.post('http://localhost:8080/seat/' + seatNumber,  {
            body,
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

    }

    return (
        <section>
            <form onSubmit={handleSubmit}>
                <input
                    name="seatNumber"
                    value={seatNumber}
                    onChange={onChangeSeatNumber}
                    type="text"
                    placeholder="좌석 번호"
                />

                <input
                    name="userName"
                    value={userName}
                    onChange={onChangeUserName}
                    type="text"
                    placeholder="이름"
                />

                <input
                    name="checkIn"
                    value={checkIn}
                    onChange={onChangeCheckIn}
                    type="text"
                    placeholder="입실 시간(HH:MM)"
                />

                <input
                    name="checkOut"
                    value={checkOut}
                    onChange={onChangeCheckOut}
                    type="text"
                    placeholder="퇴실 시간(HH:MM)"
                />

                <button type="submit">예약하기</button>
            </form>
        </section>
    );
}

export default ReservationForm;
