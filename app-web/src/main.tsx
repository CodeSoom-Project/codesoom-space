import React from 'react';
import ReactDOM from 'react-dom/client';

import { store } from './store';
import { Provider } from 'react-redux';

import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';

import { BrowserRouter, Route, Routes } from 'react-router-dom';


import App from './App';
import './index.css';
import NotFound from './NotFound';
import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <Provider store={store}>
        <BrowserRouter>
          <Routes>
            <Route path="/my-seat" element={<App/>}/>
            <Route path="signup" element={<SignUpContainer/>}/>
            <Route path="login" element={<LogInContainer/>}/>
            <Route path="*" element={<NotFound/>}/>
          </Routes>
        </BrowserRouter>
        <ReactQueryDevtools initialIsOpen/>
      </Provider>
    </QueryClientProvider>
  </React.StrictMode>,
);
