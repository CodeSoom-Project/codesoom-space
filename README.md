# Codesoom Space
https://space.codesoom.com  
코드숨 커뮤니티를 위한 주말 공부방 예약 애플리케이션입니다. 예약과 함께 계획을 세우고, 이용이 종료되면 회고를 할 수 있습니다.

(*실제 이용을 원하는 분들은 공부방 이용권 구매([링크](https://smartstore.naver.com/codesoom/products/7324508599)) 후 예약하실 수 있습니다.)

---

# 기능
## 예약하기
예약 시 이용 날짜와 계획을 입력합니다.

![예약하기](https://user-images.githubusercontent.com/98736689/215739093-a7414cc2-59c2-4e77-8349-182209a5001e.gif)

## 예약 상세보기 및 수정
이용 날짜와 계획을 수정합니다.

![예약상세및수정](https://user-images.githubusercontent.com/98736689/215740794-614e6c23-2da7-46b2-b9e0-32b5f04d31ec.gif)

## 회고 작성 및 수정
이용이 종료된 후 하루를 되돌아보며 회고를 작성합니다.

![회고작성및수정](https://user-images.githubusercontent.com/98736689/215742440-960192e2-15f7-4a28-91f1-efede568167a.gif)

## 예약 취소
예약을 취소합니다. 취소 내역도 예약 목록에서 확인할 수 있습니다.

![예약취소](https://user-images.githubusercontent.com/98736689/215743648-3bd41317-5109-442c-829e-191c0f5f0fbb.gif)

---

# 앱 실행하기
## Docker DB 컨테이너 실행 방법
### 1. mariadb 이미지 pull (이미 mariadb 이미지가 있다면 생략 가능)
```bash
docker pull mariadb:10.8.3
```

### 2. db 컨테이너 실행
```bash
docker run --name mariadb-container -v {볼륨 마운트 경로}:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root1234 -e MYSQL_DATABASE=myseattest -d -p 3306:3306 mariadb:10.8.3 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```
- 볼륨 마운트 경로는 절대 경로로 설정합니다. (ex. /Users/db)
- 이미 MariaDB가 실행 중인 경우, `myseattest` 데이터베이스를 새로 생성해 주세요.

## 백엔드 서버 실행 방법
`/app-server` 경로에서 아래의 과정을 수행합니다.

### 1. 실행 권한 업데이트
```bash
chmod +x gradlew
```

### 2. 서버 백그라운드 실행
```bash
docker-compose up -d
```

### 3. HealthCheck
```bash
curl http://localhost:8080
```
명령어 실행 시 'Hello' 응답이 왔다면 서버가 정상적으로 실행된 것입니다.

### +) 서버 종료하기
```bash
docker-compose down
```

## 프론트엔드 서버 실행 방법
`/app-web` 경로에서 아래의 과정을 수행합니다.

### 1. 의존성 목록 설치
```bash
npm ci
```

### 2. 서버 실행
```bash
npm run dev
```
명령어 실행 후 `http://localhost:5173/` 에 접속합니다.
