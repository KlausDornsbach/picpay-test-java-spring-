# picpay-test-java-spring-

this is a short implementation of the basic PicPay backend challange https://github.com/PicPay/picpay-desafio-backend 

to run this use the following commands (should have docker installed)
./gradlew build
docker build -t mini-picpay .
docker run -p 8080 mini-picpay
