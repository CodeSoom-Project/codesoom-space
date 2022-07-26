import axios from 'axios';

interface SeatType {
  seatNumber: string | number;
  userName: string;
}

interface deleteReservationVariables {
  seatNumber: number | string,
  userName: string,
}

const api = axios.create({
  baseURL: 'https://api.codesoom-myseat.site',
});

export const apis = {
  getReservation: () => api.get('/seat-reservations'),
};

export const deleteReservationFn = async ({seatNumber, userName}: { seatNumber: any, userName: string }) => {
  return await api.delete(`/seat-reservation/${seatNumber}`, {data: {userName}})
}

export const loginUserFn = async ({email, password}: { email: any, password: any }) => {
  return await api
    .post("/user/signin", {
      email,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    })
}

export const signUpUserFn = async ({
                                     email,
                                     password,
                                     passwordCheck,
                                     name
                                   }: { email: any, password: any, passwordCheck: any, name: string }) => {
  return await api
    .post("/user/signup", {
      email,
      password,
      name,
      passwordCheck
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    })
}

export const logoutUser = () => {
  localStorage.removeItem("user");
}
