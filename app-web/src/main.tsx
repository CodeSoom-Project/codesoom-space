import React from 'react';
import ReactDOM from 'react-dom/client';

import { Provider } from 'react-redux';

import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';

import { store } from './store';
import App from './App';
import './index.css';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <Provider store={store}>
        <App />
        <ReactQueryDevtools initialIsOpen />
      </Provider>
    </QueryClientProvider>
  </React.StrictMode>,
);
