import React from 'react';
import ReactDOM from 'react-dom/client';

import { Provider } from 'react-redux';
import { store } from './store';

import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';

import { BrowserRouter, Route, Routes } from 'react-router-dom';

import App from './App';

import NotFound from './NotFound';
import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import Reservations from './pages/Reservations';

import './index.css';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <Provider store={store}>
        <BrowserRouter>
          <Routes>
            <Route path="my-seat" element={<App/>}/>
            <Route path="signup" element={<SignUpContainer/>}/>
            <Route path="login" element={<LogInContainer/>}/>
            <Route path="reservations" element={<Reservations/>}/>
            
            <Route path="*" element={<NotFound/>}/>
          </Routes>
        </BrowserRouter>
        <ReactQueryDevtools initialIsOpen/>
      </Provider>
    </QueryClientProvider>
  </React.StrictMode>,
);
