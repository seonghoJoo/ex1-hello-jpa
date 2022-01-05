# ex1-hello-jpa

## 프록시

### ```em.find()``` VS ```em.getReference()```
* ```em.find()``` : 데이터베이스를 통해서 실제 엔티티 객체 조회
* ```em.getReference()``` : 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
#### DB에 쿼리가 안나가는데 객체를 불러옴. (Hibernate가 만든 가짜 Proxy 객체 : Entity를 상속받음)
#### 하지만 DB에 있는 값을 진짜 가져올땐 DB에 쿼리를 날려 객체 얻어옴
