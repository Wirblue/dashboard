FROM node:9.6.1 as builder
RUN mkdir /app
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json /app/
RUN npm install -g yarn
RUN yarn install
RUN npm install -g @angular/cli@1.7.1 --unsafe
COPY . /app/
RUN npm run build --env=prod
FROM nginx:1.13.9-alpine
COPY --from=builder /app/dist/front /usr/share/nginx/html
COPY nginx/default.conf /etc/nginx/conf.d/
CMD ["nginx", "-g", "daemon off;"]
