import './App.css';
import axios from 'axios'
import React, { useState } from 'react';

function Header() {
  return (
      <header>
        <h2>코드숨 공부방 좌석 예약 앱</h2>
      </header>
  );
}

function Reservation() {
  const [seatNumber, setSeatNumber] = useState("");
  const [userName, setUserName] = useState("");
  const [checkIn, setCheckIn] = useState("");
  const [checkOut, setCheckOut] = useState("");

  const onChangeSeatNumber = (e) => {
    e.preventDefault();
    setSeatNumber(e.target.value);
  };
  const onChangeUserName = (e) => {
    e.preventDefault();
    setUserName(e.target.value);
  };
  const onChangeCheckIn = (e) => {
    e.preventDefault();
    setCheckIn(e.target.value);
  };
  const onChangeCheckOut = (e) => {
    e.preventDefault();
    setCheckOut(e.target.value);
  };

  const handleSubmit = async(e) => {
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

function App() {
  return (
      <div>
        <Header></Header>
        <Reservation></Reservation>
      </div>
  );
}

export default App;
