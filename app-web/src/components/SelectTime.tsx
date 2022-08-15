// import DatePicker from 'react-datepicker';
// import 'react-datepicker/dist/react-datepicker.css';
//
// import { useState } from 'react';
//
// const SelectTime = (children:any) => {
//   const [startDate, setStartDate] = useState(new Date());
//   return (
//     <>
//       {children}
//       {' : '}
//       <DatePicker
//         selected={startDate}
//         onChange={(date) => setStartDate(date)}
//         showTimeSelect
//         showTimeSelectOnly
//         timeIntervals={30}
//         timeCaption="Time"
//         dateFormat="h:mm aa"
//       />
//     </>
//   );
// };
//
// export default SelectTime;

import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

const SelectTime = (children:any, check:any, { onChange }:any) => {
  return (
    <>
      {children}
      {' : '}
      <DatePicker
        selected={check}
        onChange={(date) => onChange(date)}
        showTimeSelect
        showTimeSelectOnly
        timeIntervals={30}
        timeCaption="Time"
        dateFormat="h:mm aa"
      />
    </>
  );
};

export default SelectTime;
