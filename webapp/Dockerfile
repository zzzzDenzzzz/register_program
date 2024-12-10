# 1. Стадия сборки
FROM node:lts

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем package.json и package-lock.json для установки зависимостей
COPY package*.json ./

# Устанавливаем зависимости
RUN npm install

# Копируем весь исходный код в контейнер
COPY . .

# Собираем React-приложение
RUN npm run build

# 2. Стадия запуска
# FROM nginx:stable-alpine

# # Копируем собранные файлы Vite (из папки dist) в nginx
# COPY --from=builder /app/dist /usr/share/nginx/html
# COPY nginx.conf /etc/nginx/conf.d/default.conf

# # Запускаем nginx
# CMD ["nginx", "-g", "daemon off;"]

# Экспонируем порт
EXPOSE 4173

# Используем встроенный сервер Vite для предпросмотра
CMD ["npm", "run", "serve"]