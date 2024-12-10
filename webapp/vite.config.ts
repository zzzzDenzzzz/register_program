import { defineConfig, loadEnv } from "vite";
import react from "@vitejs/plugin-react";

const SERVER_URL = "http://172.17.0.3:8080";

// https://vitejs.dev/config/
export default ({ mode }) => {
  // eslint-disable-next-line no-undef
  process.env = { ...process.env, ...loadEnv(mode, process.cwd()) };

  // eslint-disable-next-line no-undef
  const BASE_URL = process.env.VITE_SIMPLE_REST_URL ?? "/";

  return defineConfig({
    plugins: [react()],
    define: {
      // eslint-disable-next-line no-undef
      "process.env": process.env,
    },
    server: {
      proxy: {
        [BASE_URL]: SERVER_URL,
      },
    },
    base: "./",
    resolve: {
      alias: {
        src: '/src'
      }
    },
  });
};