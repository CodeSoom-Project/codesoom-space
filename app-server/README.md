# 코드숨 공부방 좌석 예약 앱  서버

## 데이터 베이스 실행
### 1. mariadb 이미지 pull
```bash
$ docker pull mariadb:10.8.3
```
*이미 mariadb 이미지가 있다면 이 과정은 생략해도 됩니다.

### 2. db 컨테이너 실행
이때 볼륨을 마운트 할 경로는 절대 경로로 설정합니다. ex. /Users/db

```bash
$ docker run --name mariadb-container -v {볼륨 마운트 경로}:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root1234 -e MYSQL_DATABASE=myseattest -d -p 3306:3306 mariadb:10.8.3 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

*이미 컴퓨터에 MariaDB가 실행 중일 경우에는 `myseattest` 데이터베이스를 생성해 주어야 합니다.

## 서버 실행
App 서버 실행
