import React from 'react';
import ReactDOM from 'react-dom/client';

import {store} from './store';
import {Provider} from 'react-redux';

import {QueryClient, QueryClientProvider} from 'react-query';
import {ReactQueryDevtools} from 'react-query/devtools';

import {BrowserRouter, Route, Routes,} from 'react-router-dom'


import App from './App';
import './index.css';

import SignUp from "./signUp";
import NotFound from "./NotFound";
import seatReservationInfo from "./seatReservationInfo";
import SignInContainer from "./signInContainer";

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <Provider store={store}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<App/>}/>
            <Route path=":seatId" element={<seatReservationInfo/>}/>
            <Route path="signup" element={<SignUp/>}/>
            <Route path="signin" element={<SignInContainer/>}/>
            <Route path="*" element={<NotFound/>}/>
          </Routes>
        </BrowserRouter>

        <ReactQueryDevtools initialIsOpen/>
      </Provider>
    </QueryClientProvider>
  </React.StrictMode>,
);
