# rabbitMQ-test
rabbitMQ 튜토리얼 Repository입니다.


## ✅ preparations
1. Run rabbitMQ
```
docker run -d -it --rm --name my-rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
```
| 옵션 | 설명 |
| --- | --- |
| -d | container를 백그라운드에서 실행 및 container ID 출력 |
| —rm | 컨테이너가 종료되면 자동으로 삭제 |
| —name | 컨테이너 이름 설정 |
| -p 5672:5672 | RabbitMQ가 사용하는 5672번 포트와 호스트의 5672번 포트를 연결 |
| p 5672:5672 | RabbitMQ 관리자 인터페이스에 접근하기 위해 사용하는 15672번 포트와 호스트의 15672번 포트를 연결 |
| rabbitmq:3.11-management | RabbitMQ 3.11 버전과 관리자 인터페이스를 지원하는 이미지를 사용 |

2. 관리자 설정
```
docker exec -it my-rabbitmq bash
```
```
rabbitmqctl add_user myuser password
rabbitmqctl set_user_tags myuser administrator
rabbitmqctl set_permissions -p / myuser ".*" ".*" ".*"
```

3. http://localhost:15672 접속
![스크린샷 2023-04-16 오후 4 06 24](https://user-images.githubusercontent.com/53431518/232280009-861a27ac-f4c9-4d23-b737-dc12c165903e.png)
