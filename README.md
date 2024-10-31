# bookmarkManagement
상품 찜 등록 관리

# 실행방법

- 도커 이미지 다운
```shell
$ docker pull hyeyeoncha/bookmark_backend_server:3.0
$ docker pull hyeyeoncha/bookmark_db:1.0
```

- mysql 접속 방법
```shell
$ docker exec -it bookmarkDB bash
$ mysql -u root -p
<asdf1234>
```

- 컨테이너 실행 (프로젝트 루트에서 실행)
```shell
$ docker compose up -d
```


각 API 예시 Curl
```shell

<test jwt>

# 유저 정보 가져오기
curl -X 'GET' \
  'http://localhost:8080/user/v1/1' \
  -H 'Authorization: Bearer <test jwt>' \
  -H 'accept: */*'


# 내 찜서랍 추가하기
curl -X 'POST' \
  'http://localhost:8080/product/bookmark/v1/groups' \
  -H 'Authorization: Bearer <test jwt>' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "groupName": "string"
}'


# 내 찜서랍 삭제하기
curl -X 'DELETE' \
  'http://localhost:8080/product/bookmark/v1/groups/1' \
   -H 'Authorization: Bearer <test jwt>' \
  -H 'accept: */*'


# 내 찜서랍 리스트 가져오기
curl -X 'GET' \
  'http://localhost:8080/product/bookmark/v1/groups?page=1&pageSize=10' \
  -H 'Authorization: Bearer <test jwt>' \
  -H 'accept: */*'  \
  -d '{
  “page”: 1,
  “page”Size: 15
}'


# 내 찜서랍의 찜 추가하기
curl -X 'POST' \
  'http://localhost:8080/product/bookmark/v1/groups/1/bookmarks' \
  -H 'Authorization: Bearer <test jwt>' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "productId": 1
}'


# 내 찜 서랍의 찜 삭제하기
curl -X 'DELETE' \
  'http://localhost:8080/product/bookmark/v1/groups/1/bookmarks/2' \
  -H 'Authorization: Bearer <test jwt>' \
  -H 'accept: */*'


# 내 찜 서랍의 찜 목록 가져오기
curl -X 'GET' \
  'http://localhost:8080/product/bookmark/v1/groups/1/bookmarks?page=1&pageSize=10' \
  -H 'Authorization: Bearer <test jwt>' \
  -H 'accept: */*'

```

--- 
# 회원가입 관련 Sequence Diagram
```mermaid
sequenceDiagram
    actor User
    
    User->>App: 이름, 이메일, 비밀번호  입력
    App->>Server: 이름, 이메일, 비밀번호 입력
    Server->>+DB: 이메일로 유저 조회
    DB-->>-Server: 결과 반환

    alt UserID 존재
        Server-->>App: Error 이미 가입된 유저 반환
        App-->>User: 가입 불가능 및 로그인 유도
    end
        
        Server->>+Server: Access Token 발급
        
        Server->>+Server: password 암호화 (선택적 스펙)
        
        Server->>+DB: 회원 정보 저장 (이름, 이메일, password)
        DB-->>-Server: 저장된 회원 정보 반환

        Server-->>App: 회원 ID, access token 반환
        App-->>User: 메인 페이지 이동
```

# 로그인 관련 Sequence Diagram
```mermaid
sequenceDiagram
    actor User
    
    User->>App: 이메일, 비밀번호 입력
    App->>Server: 이메일, 비밀번호 입력
    Server->>+DB: 이메일 유저 조회
    DB-->>-Server: 결과 반환

    alt UserID 미존재
        Server-->>App: Error 존재하지 않는 유저 반환
        App-->>User: 가입 유도
    end
        
        Server->>+Server: password 복호화 및 입력된 password 와 비교 (선택적 스펙)
        
        alt password 일치하지 않음
            Server-->>App: Error 잘못된 비밀번호 반환
            App-->>User: 비밀번호 재입력 요구
        end
        
        Server->>+Server: 이메일을 이용해 새로운 jwt 토큰 생성
       
        Server-->>App: 회원 ID, access token 반환
        App-->>User: 메인 페이지 이동
```

# Entity 관계도
```mermaid
erDiagram
    User ||--o{ BookmarkGroup : contains
    User {
        long id
        string name
        string password
        string email
        dateTime createdDatetime
        dateTime updatedDatetime
    }

    BookmarkGroup ||--o{ Bookmark : contains
    BookmarkGroup {
		    long id
		    long userId
				string groupName
        dateTime createdDatetime
        dateTime updatedDatetime
		}

    
    Bookmark {
        long id
        long userId
        long bookmarkGroupId
        long productId
        dateTime createdDatetime
        dateTime updatedDatetime
    }
    
    
    Product ||--o{ Bookmark : contains
    Product {
        long id
        string name
        string thumbnail
        long price
        dateTime createdDatetime
        dateTime updatedDatetime
    }
```
