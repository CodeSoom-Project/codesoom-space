import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import babel from 'vite-plugin-babel';

// https://vitejs.dev/config/
export default defineConfig({
  base: '/my-seat/',
  plugins: [
    react(),
    babel(),
  ],
});
