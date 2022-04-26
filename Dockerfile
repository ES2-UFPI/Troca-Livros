
# Build application

FROM golang:1.17-alpine AS build

WORKDIR /app

COPY go.mod ./
COPY go.sum ./

RUN go mod download

COPY . .

RUN go build -o /rest-server ./Backend


# Deploy appliation

FROM alpine

WORKDIR /

COPY --from=build /rest-server /

EXPOSE 8080

ENTRYPOINT [ "/rest-server" ]